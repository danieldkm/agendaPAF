package com.unifil.agendapaf.view.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.unifil.agendapaf.model.aux.Categoria;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.aux.Servico;
import com.unifil.agendapaf.util.MaskFieldUtil;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.view.util.enums.EnumServico;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class ParametroController {

    @FXML
    public void initialize() {
        try {
            MaskFieldUtil.monetaryField(txtValor);
            servicos = Controller.getServicos();
            categorias = Controller.getCategorias();
            tvServico.setItems(servicos);
            tvCategoria.setItems(categorias);
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar parametro", e, "Exception:");
        }
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        try {
//            stage = primaryStage;
//            principal = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.Parametro.getCaminho()));
//            Scene scene = new Scene(principal);
//            stage.setScene(scene);
//            stage.setTitle("Parâmetro");
//            stage.setResizable(false);
////        stage.initOwner(this.myParent);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.show();
//            stage.toFront();
////            stage.getIcons().add(Controller.icoPAF);
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent t) {
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start parametro", e, "Exception:");
//        }
//    }
    @FXML
    private BorderPane principal;
    @FXML
    private TextField txtNomeServico;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtNomeCategoria;
    @FXML
    private TextField txtPorcentagem;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Tab tabCategoria;
    @FXML
    private Tab tabServico;
    @FXML
    private TableView<Servico> tvServico;
    @FXML
    private TableView<Categoria> tvCategoria;

    private Stage stage;
    private ObservableList<Servico> servicos;
    private ObservableList<Categoria> categorias;
    private Servico servicoEncontrado;
    private Categoria categoriaEncontrado;
    private boolean atualizar = false;
    private XStream xStream = new XStream(new DomDriver());

    private void salvarServico() {
        xStream.alias("servico", Servico.class);
        String xml = xStream.toXML(servicos);
        UtilFile.salvarArquivoXML(xml, "/servico.xml");
        btnLimpar.arm();
        btnLimpar.fire();
    }

    private void salvarCategoria() {
        xStream.alias("categoria", Categoria.class);
        String xml = xStream.toXML(categorias);
        UtilFile.salvarArquivoXML(xml, "/categoria.xml");
        btnLimpar.arm();
        btnLimpar.fire();
    }

    @FXML
    private void actionBtnSalvar() {
        try {
            if (validarCampos()) {
                if (tabServico.isSelected()) {
                    if (atualizar) {
                        servicos.clear();
                        for (Servico servico : Controller.getServicos()) {
                            if (servico.getId() == servicoEncontrado.getId()) {
                                servico.setNome(txtNomeServico.getText());
                                String auxT = txtValor.getText().replace(".", "");
                                auxT = auxT.replace(",", ".");
                                auxT = auxT.replace("R$", "");
                                System.out.println("auxT " + auxT);
                                servico.setValor(Double.parseDouble(auxT));
                            }
                            servicos.add(servico);
                        }
                        tvServico.setItems(servicos);
                        salvarServico();
                        atualizar = false;
                    } else {
                        Servico s = new Servico();
                        if (servicos.size() == 0) {
                            s.setId(1);
                        } else {
                            s.setId(servicos.get(servicos.size() - 1).getId() + 1);
                        }
                        s.setNome(txtNomeServico.getText());
                        String auxT = txtValor.getText().replace(".", "");
                        auxT = auxT.replace(",", ".");
                        auxT = auxT.replace("R$", "");
                        s.setValor(Double.parseDouble(auxT));
                        servicos.add(s);
                        tvServico.getItems().setAll(servicos);
                        salvarServico();
                    }
                } else if (tabCategoria.isSelected()) {
                    if (atualizar) {
                        categorias.clear();
                        for (Categoria categoria : Controller.getCategorias()) {
                            if (categoria.getId() == categoriaEncontrado.getId()) {
                                categoria.setNome(txtNomeCategoria.getText());
                                categoria.setPorcento(Integer.parseInt(txtPorcentagem.getText()));
                            }
                            categorias.add(categoria);
                        }
                        tvCategoria.getItems().clear();
                        tvCategoria.getItems().setAll(categorias);
                        salvarCategoria();
                        atualizar = false;
                    } else {
                        Categoria c = new Categoria();
                        if (categorias.size() == 0) {
                            c.setId(1);
                        } else {
                            c.setId(categorias.get(categorias.size() - 1).getId() + 1);
                        }
                        c.setNome(txtNomeCategoria.getText());
                        c.setPorcento(Integer.parseInt(txtPorcentagem.getText()));
                        categorias.add(c);
                        tvCategoria.getItems().setAll(categorias);
                        salvarCategoria();
                    }
                }
                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
            }
        } catch (Exception e) {
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e, "Exception:");
            e.printStackTrace();
            System.out.println("Erro: ao tentar salvar. Metodo: actionBtnSalvar. Classe: ParametroController");
        }
    }

    @FXML
    private void onMouseClickedTabelaServico(MouseEvent event
    ) {
        if (event.getClickCount() == 2) {
            servicoEncontrado = tvServico.getSelectionModel().getSelectedItem();
            txtNomeServico.setText(servicoEncontrado.getNome());
            String n = servicoEncontrado.getValor() + "";
            txtValor.setText(n);
            atualizar = true;
        }
    }

    @FXML
    private void onMouseClickedTabelaCategoria(MouseEvent event
    ) {
        if (event.getClickCount() == 2) {
            categoriaEncontrado = tvCategoria.getSelectionModel().getSelectedItem();
            txtNomeCategoria.setText(categoriaEncontrado.getNome());
            txtPorcentagem.setText(categoriaEncontrado.getPorcento() + "");
            atualizar = true;
        }
    }

    @FXML
    private void actionBtnDeletar() {
        if (tabServico.isSelected()) {
            if (servicoEncontrado != null) {
                try {
                    ObservableList<Servico> novaLista = FXCollections.observableArrayList();
                    for (Servico servico : servicos) {
                        if (servicoEncontrado.getId() != servico.getId()) {
                            novaLista.add(servico);
                        }
                    }
                    servicos = novaLista;
                    salvarServico();
                    tvServico.getItems().setAll(servicos);
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Deletado.getMensagem());
                } catch (Exception e) {
                    UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroDeletar.getMensagem(), e, "Exception");
                    e.printStackTrace();
                }
            } else {
                UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ParametroErroServicoNaoSelecionado.getMensagem());
            }
        } else if (tabCategoria.isSelected()) {
            if (categoriaEncontrado != null) {
                try {
                    ObservableList<Categoria> novaLista = FXCollections.observableArrayList();
                    for (Categoria categoria : categorias) {
                        if (categoriaEncontrado.getId() != categoria.getId()) {
                            novaLista.add(categoria);
                        }
                    }
                    categorias = novaLista;
                    salvarCategoria();
                    tvCategoria.getItems().setAll(categorias);
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Deletado.getMensagem());
                } catch (Exception e) {
                    UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroDeletar.getMensagem(), e, "Exception");
                    e.printStackTrace();
                }
            } else {
                UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ParametroErroCategoriaNaoSelecionado.getMensagem());
            }
        }
    }

    @FXML
    private void actionBtnLimpar() {
        limpar();
        atualizar = false;
        servicoEncontrado = null;
        categoriaEncontrado = null;
    }

    private void limpar() {
        txtNomeCategoria.setText("");
        txtNomeServico.setText("");
        txtPorcentagem.setText("");
        txtValor.setText("");
    }

    @FXML
    private void actionBtnPadrao() {

        Optional<ButtonType> result = UtilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.ParametroConfirmarGerarParametroPadrao.getMensagem(), EnumMensagem.ParametroConfirmandoGerarParametroPadrao.getMensagem());
        if (result.get() == ButtonType.OK) {
            try {
                xStream.alias("servico", Servico.class
                );

                ObservableList<Servico> tipos = FXCollections.observableArrayList();
                Servico servico = new Servico();

                servico.setId(
                        1);
                servico.setNome(
                        EnumServico.Avaliacao.getServico()
                );

                servico.setValor(
                        2106);

                Servico servico2 = new Servico();

                servico2.setId(
                        2);
                servico2.setNome(
                        EnumServico.AvaliacaoIntinerante.getServico()
                );
                servico2.setValor(
                        2538);

                Servico servico3 = new Servico();

                servico3.setId(
                        3);
                servico3.setNome(
                        EnumServico.PreAvaliacao.getServico());
                servico3.setValor(
                        1058);

                Servico servico4 = new Servico();

                servico4.setId(
                        4);
                servico4.setNome(
                        EnumServico.PreAvaliacaoRemoto.getServico()
                );
                servico4.setValor(
                        1058);

                Servico servico6 = new Servico();

                servico6.setId(
                        5);
                servico6.setNome(
                        EnumServico.HoraAdicional.getServico()
                );
                servico6.setValor(
                        151);

                tipos.add(servico);

                tipos.add(servico2);

                tipos.add(servico3);

                tipos.add(servico4);

                tipos.add(servico6);

                String xml = xStream.toXML(tipos);

                UtilFile.salvarArquivoXML(xml,
                        "/servico.xml");

                xStream.alias(
                        "categoria", Categoria.class
                );

                ObservableList<Categoria> categorias = FXCollections.observableArrayList();
                Categoria categoria = new Categoria();
                categoria.setId(0);
                categoria.setNome(
                        "Normal");
                categoria.setPorcento(
                        0);

                Categoria categoria2 = new Categoria();
                categoria2.setId(1);
                categoria2.setNome(
                        "Empresas APL");
                categoria2.setPorcento(
                        10);

                Categoria categoria3 = new Categoria();
                categoria3.setId(2);
                categoria3.setNome(
                        "Cintec");
                categoria3.setPorcento(
                        20);

                Categoria categoria4 = new Categoria();
                categoria4.setId(3);
                categoria4.setNome(
                        "Governança da APL");
                categoria4.setPorcento(
                        30);

                Categoria categoria5 = new Categoria();
                categoria5.setId(3);
                categoria5.setNome(
                        "Reavaliação");
                categoria5.setPorcento(
                        10);

                categorias.add(categoria);
                categorias.add(categoria2);
                categorias.add(categoria3);
                categorias.add(categoria4);
                categorias.add(categoria5);

                String xml2 = xStream.toXML(categorias);

                UtilFile.salvarArquivoXML(xml2,
                        "/categoria.xml");

                servicos = Controller.getServicos();
                categorias = Controller.getCategorias();

                tvServico.getItems()
                        .setAll(servicos);
                tvCategoria.getItems()
                        .setAll(categorias);
                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Gerado.getMensagem());
            } catch (Exception e) {
                UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroGerar.getMensagem(), e, "Exception");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void setOnSelectionChangedTbCategoria() {
        if (tabCategoria.isSelected()) {
            btnDeletar.setDisable(true);
        } else {
            btnDeletar.setDisable(false);
        }
    }

    private boolean validarCampos() {
        ValidationSupport validationSupport = new ValidationSupport();
        boolean ok = true;
        String preencher = "";
        if (tabServico.isSelected()) {
            removerStyle();
            if (txtNomeServico.getText().equals("")) {
                preencher += EnumMensagem.ParametroInformeNome + "\n";
                validationSupport.registerValidator(txtNomeServico, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                txtNomeServico.requestFocus();
                ok = false;
            }
            if (txtValor.getText().equals("")) {
                removerStyle();
                preencher += EnumMensagem.ParametroInformeValor + "\n";
                validationSupport.registerValidator(txtValor, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                txtValor.requestFocus();
                ok = false;
            } else {
//                try {
//                    removerStyle();
//                    if (txtValor.getText().contains(".")) {
//                        preencher += EnumMensagem.ParametroRemoverponto + "\n";
//                        validationSupport.registerValidator(txtValor, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
//                        txtValor.requestFocus();
//                        ok = false;
//                    } else if (txtValor.getText().contains(",")) {
////                        String n = txtValor.getText().replace(",", ".");
//                        String auxT = txtValor.getText().replace(".", "");
//                        auxT = txtValor.getText().replace(",", ".");
//                        double n2 = Double.parseDouble(auxT);
//                    } else {
//                        double n = Double.parseDouble(txtValor.getText());
//                    }
//                } catch (Exception e) {
//                    preencher += EnumMensagem.ParametroIncorretoValor + "\n";
//                    validationSupport.registerValidator(txtValor, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
//                    txtValor.requestFocus();
//                    ok = false;
//                }
            }

        } else if (tabCategoria.isSelected()) {
            removerStyle();
            if (txtNomeCategoria.getText().equals("")) {
                preencher += EnumMensagem.ParametroInformeNome + "\n";
                validationSupport.registerValidator(txtNomeCategoria, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                txtNomeCategoria.requestFocus();
                ok = false;
            }
            if (txtPorcentagem.getText().equals("")) {
                preencher += EnumMensagem.ParametroInformePorcentagem.getMensagem() + "\n";
                validationSupport.registerValidator(txtPorcentagem, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                txtPorcentagem.requestFocus();
                ok = false;
            } else {
                try {
                    int n = Integer.parseInt(txtPorcentagem.getText());
                    if (n > 100) {
                        preencher += EnumMensagem.ParametroIncorretoPorcentagemAcima.getMensagem() + "\n";
                        validationSupport.registerValidator(txtPorcentagem, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                        txtPorcentagem.requestFocus();
                        ok = false;
                    }
                } catch (Exception e) {
                    preencher += EnumMensagem.ParametroIncorretoPorcentagem.getMensagem() + "\n";
                    validationSupport.registerValidator(txtPorcentagem, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                    txtPorcentagem.requestFocus();
                    ok = false;
                }
            }
        }
        if (!ok) {
            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), "Validando campos", preencher);
        }
        return ok;
    }

    private void removerStyle() {
        txtNomeCategoria.setStyle(null);
        txtNomeServico.setStyle(null);
        txtPorcentagem.setStyle(null);
        txtValor.setStyle(null);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPrincipal(BorderPane principal) {
        this.principal = principal;
    }

}
