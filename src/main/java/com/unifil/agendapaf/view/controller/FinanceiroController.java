package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.model.aux.Categoria;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.aux.Servico;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.service.FinanceiroService;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumServico;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class FinanceiroController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            if (utilDialog == null) {
                utilDialog = new UtilDialog();
            }
            mainFinanceiro.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            System.out.println("Iniciar initialize: Tela Financeiro");
            servicos = Controller.getServicos();
            categorias = Controller.getCategorias();
            setComboBox();
            if (StaticObject.getAgendaEncontrada() != null && StaticObject.getEmpresaEncontrada() != null) {
                empresaEncontrada = StaticObject.getEmpresaEncontrada();
                setCampos(empresaEncontrada, StaticObject.getAgendaEncontrada());
            }
            if (StaticBoolean.isTabelaEmpresaToFinanceiro()) {
                empresaEncontrada = StaticObject.getEmpresaEncontrada();
                StaticObject.setEmpresaEncontrada(null);
                StaticBoolean.setTabelaEmpresaToFinanceiro(false);
                setCampos(empresaEncontrada);

            }
            if (StaticObject.getFinanceiroEncontrada() != null) {
                financeiroEncontrada = StaticObject.getFinanceiroEncontrada();
                StaticObject.setFinanceiroEncontrada(null);
                setCampos();
            }
            StaticObject.setAgendaEncontrada(null);
            StaticObject.setEmpresaEncontrada(null);
            System.out.println("Finalizar initialize: Tela Financeiro");
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar financeiro", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("Iniciar Start: Tela Financeiro");
            stage = primaryStage;
            mainFinanceiro = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.Financeiro.getCaminho()));
            Scene scene = new Scene(mainFinanceiro);
            scene.getStylesheets().add(EnumCaminho.CSS.getCaminho());
            stage.setScene(scene);
            stage.setTitle("Financeiro");
            stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnHidden(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent t) {
                }
            });
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                    if (txtBuscarEmpresa != null) {
                        limpar();
                    }
                }
            });
            System.out.println("Finalizar Start: Tela Financeiro");
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start financeiro", e, "Exception:");
        }
    }

    private static Stage stage;
    @FXML
    private ComboBox cbTipoServico;
    @FXML
    private TextField txtBuscarEmpresa;
    @FXML
    private TextField txtHoraAdicional;
    @FXML
    private TextField txtValorPago;
    @FXML
    private TextField txtLaudo;
    @FXML
    private ComboBox cbCategoria;
    @FXML
    private VBox mainFinanceiro;
    @FXML
    private DatePicker dtInicial;
    @FXML
    private DatePicker dtFinal;

    private Empresa empresaEncontrada;
    private Financeiro financeiroEncontrada;
    private ObservableList<Servico> servicos;
    private ObservableList<Categoria> categorias;
//    private FXCalendar dataInicial;
//    private FXCalendar dataFinal;
    private boolean isUpdate = false;
    private boolean isParametro = false;
    private static double valorServico = 0;
    private static double porCategoria = 0;
    private UtilDialog utilDialog;

    @FXML
    private void actionBuscarEmpresa() {
        stage.close();
        StaticBoolean.setTabelaEmpresaToFinanceiro(true);
        RunAnotherApp.runAnotherApp(TabelaEmpresaController.class);
    }

    @FXML
    private void iniciarCadastroParametro() {
        stage.close();
        RunAnotherApp.runAnotherApp(ParametroController.class);
    }

    @FXML
    private void iniciarTabelaFinanceiro() {
        stage.close();
        RunAnotherApp.runAnotherApp(TabelaFinanceiroController.class);
    }

    @FXML
    private void actionTxtHoraAdicional() {
        calcularServico();
    }

    private void calcularServico() {
        if (cbTipoServico.getSelectionModel().getSelectedItem() != null) {
            if (cbTipoServico.getSelectionModel().getSelectedItem().equals(EnumServico.HoraAdicional.getServico())) {
                txtHoraAdicional.setDisable(false);
            } else {
                txtHoraAdicional.setDisable(true);
            }
            for (Servico servico : servicos) {
                if (cbTipoServico.getSelectionModel().getSelectedItem().equals(servico.getNome())) {
                    valorServico = servico.getValor();
                    break;
                }
            }
            if (cbCategoria.getSelectionModel().getSelectedItem() != null) {
                for (Categoria categoria : categorias) {
                    if (cbCategoria.getSelectionModel().getSelectedItem().toString().equals(categoria.getNome())) {
                        porCategoria = categoria.getPorcento();
                        break;
                    }
                }
            }
            String n = "";
            if (porCategoria == 0) {
                if (txtHoraAdicional.getText().equals("") || txtHoraAdicional.getText() == null) {
                    n = valorServico + "";
                } else {
                    try {
                        valorServico = valorServico * Integer.parseInt(txtHoraAdicional.getText());
                        n = valorServico + "";
                    } catch (Exception e) {
                        utilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Não é um número inteiro");
                    }
                }
            } else {
                if (txtHoraAdicional.getText().equals("") || txtHoraAdicional.getText() == null) {
                    n = (valorServico - (valorServico * (porCategoria / 100))) + "";
                } else {
                    try {
                        if (Integer.parseInt(txtHoraAdicional.getText()) > 0) {
                            valorServico = valorServico * Integer.parseInt(txtHoraAdicional.getText());
                            n = (valorServico - (valorServico * (porCategoria / 100))) + "";
                        }
                    } catch (Exception e) {
                        utilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Não é um número inteiro");
                    }
                }
            }
            txtValorPago.setText(n.replace(".", ","));
        }
        porCategoria = 0;
        valorServico = 0;
    }

    @FXML
    private void actionCbTipoServico() {
        calcularServico();
    }

    @FXML
    private void actionCbCategoria() {
        calcularServico();
    }

    @FXML
    private void actionBtnSalvar() {
        if (validarCampos()) {
            try {
                FinanceiroService fs = new FinanceiroService();
                if (isUpdate) {
                    Financeiro f = new Financeiro();
                    if (empresaEncontrada == null) {
                        f.setIdEmpresa(financeiroEncontrada.getIdEmpresa());
                    } else {
                        f.setIdEmpresa(empresaEncontrada);
                    }
                    f.setId(financeiroEncontrada.getId());
                    f.setTipoServico(cbTipoServico.getSelectionModel().getSelectedItem() + "");
                    if (!txtHoraAdicional.getText().equals("")) {
                        f.setHoraAdicional(Integer.parseInt(txtHoraAdicional.getText()));
                    } else {
                        f.setHoraAdicional(0);
                    }
                    calcularServico();
                    f.setValorPago(Double.parseDouble(txtValorPago.getText().replace(",", ".")));
                    if (!txtLaudo.getText().equals("")) {
                        f.setNumeroLaudo(txtLaudo.getText());
                    } else {
                        f.setNumeroLaudo("");
                    }
                    f.setCategoria(cbCategoria.getSelectionModel().getSelectedItem() + "");
                    f.setDataInicial(dtInicial.getValue());
                    f.setDataFinal(dtFinal.getValue());
                    fs.editar(f);
                    utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
                } else {
                    Financeiro f = new Financeiro();
                    f.setIdEmpresa(empresaEncontrada);
                    f.setTipoServico(cbTipoServico.getSelectionModel().getSelectedItem() + "");
                    if (!txtHoraAdicional.getText().equals("")) {
                        f.setHoraAdicional(Integer.parseInt(txtHoraAdicional.getText()));
                    } else {
                        f.setHoraAdicional(0);
                    }
                    f.setValorPago(Double.parseDouble(txtValorPago.getText().replace(",", ".")));
                    if (!txtLaudo.getText().equals("")) {
                        f.setNumeroLaudo(txtLaudo.getText());
                    } else {
                        f.setNumeroLaudo(null);
                    }
                    f.setCategoria(cbCategoria.getSelectionModel().getSelectedItem() + "");
                    f.setDataInicial(dtInicial.getValue());
                    f.setDataFinal(dtFinal.getValue());
                    fs.salvar(f);
                    utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
                }
                JPA.em(false).close();
                actionBtnLimpar();
                StaticLista.setListaGlobalFinanceiro(Controller.getFinanceiros());
            } catch (Exception e) {
                utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e, "Exception");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void actionBtnDeletar() {
        if (financeiroEncontrada != null) {
            try {
                Optional<ButtonType> result = utilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.CertezaDeletar.getMensagem());
                if (result.get() == ButtonType.OK) {
                    FinanceiroService fs = new FinanceiroService();
                    fs.deletar(financeiroEncontrada);
                    JPA.em(false).close();
                    utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Deletado.getMensagem());
                    StaticLista.setListaGlobalFinanceiro(Controller.getFinanceiros());
                }
            } catch (Exception ex) {
                JPA.em(false).close();
                Logger.getLogger(FinanceiroController.class.getName()).log(Level.SEVERE, null, ex);
                utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroDeletar.getMensagem(), ex, "Exception");
            }
            limpar();
        } else {
            utilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.FinanceiroErroNaoSelecionado.getMensagem());
        }
    }

    @FXML
    private void actionBtnLimpar() {
        if (txtBuscarEmpresa != null) {
            limpar();
        }
    }

    private boolean validarCampos() {
        ValidationSupport validationSupport = new ValidationSupport();
        boolean ok = true;
        String preencher = "";
        if (txtBuscarEmpresa.getText().equals("")) {
            removerStyle();
            preencher += EnumMensagem.InformeEmpresa.getMensagem() + "\n";
            validationSupport.registerValidator(txtBuscarEmpresa, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            txtBuscarEmpresa.requestFocus();
            ok = false;
        }
        if (cbTipoServico.getSelectionModel().getSelectedItem() == null) {
            removerStyle();
            preencher += EnumMensagem.InformeComboBox.getMensagem() + "\n";
            validationSupport.registerValidator(cbTipoServico, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            cbTipoServico.requestFocus();
            ok = false;
        } else if (cbTipoServico.getSelectionModel().getSelectedItem().equals("")) {
            removerStyle();
            preencher += EnumMensagem.InformeComboBox.getMensagem() + "\n";
            validationSupport.registerValidator(cbTipoServico, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            cbTipoServico.requestFocus();
            ok = false;
        }

        if (cbCategoria.getSelectionModel().getSelectedItem() == null) {
            removerStyle();
            preencher += EnumMensagem.InformeComboBox.getMensagem() + "\n";
            validationSupport.registerValidator(cbCategoria, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            cbCategoria.requestFocus();
            ok = false;
        } else if (cbCategoria.getSelectionModel().getSelectedItem().equals("")) {
            removerStyle();
            preencher += EnumMensagem.InformeComboBox.getMensagem() + "\n";
            validationSupport.registerValidator(cbCategoria, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            cbCategoria.requestFocus();
            ok = false;
        }

        if (txtValorPago.getText().equals("") || txtValorPago.getText().contains(".")) {
            removerStyle();
            preencher += EnumMensagem.FinanceiroTxtValorInvalido.getMensagem() + "\n";
            validationSupport.registerValidator(txtValorPago, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            txtValorPago.requestFocus();
            ok = false;
        }

        if (!txtHoraAdicional.getText().equals("")) {
            try {
                int n = Integer.parseInt(txtHoraAdicional.getText());
            } catch (Exception e) {
                removerStyle();
                preencher += EnumMensagem.FinanceiroHoraAdicionalInvalido + "\n";
                validationSupport.registerValidator(txtHoraAdicional, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
                txtHoraAdicional.requestFocus();
                ok = false;
            }
        }
        if (!ok) {
            utilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), "Validando campos", preencher);
        }
        return ok;
    }

    private void removerStyle() {
        txtBuscarEmpresa.setStyle(null);
        txtHoraAdicional.setStyle(null);
        txtValorPago.setStyle(null);
        cbCategoria.setStyle(null);
        cbTipoServico.setStyle(null);

    }

    private void limpar() {
        txtBuscarEmpresa.setText("");
        txtHoraAdicional.setText("");
        txtLaudo.setText("");
        txtValorPago.setText("");
        empresaEncontrada = null;
        financeiroEncontrada = null;
        isUpdate = false;
        dtInicial.setValue(null);
        dtFinal.setValue(null);
        cbCategoria.setItems(categorias);
        cbTipoServico.setItems(servicos);
    }

    public void setComboBox() {
        ObservableList<String> lista = FXCollections.observableArrayList();
        for (Servico s : Controller.getServicos()) {
            lista.add(s.getNome());
        }
        cbTipoServico.getItems().setAll(lista);
        cbCategoria.setItems(Controller.getCategorias());

        // re-adicionar as listas
        servicos = Controller.getServicos();
        categorias = Controller.getCategorias();
    }

    public void setCampos(Empresa empresa, Agenda agenda) {
        txtBuscarEmpresa.setText(empresa.getDescricao());
        dtInicial.setValue(agenda.getDataInicial());
        dtFinal.setValue(agenda.getDataFinal());
        cbTipoServico.getSelectionModel().selectFirst();
        cbCategoria.getSelectionModel().select(empresa.getCategoria());
        cbTipoServico.getSelectionModel().selectFirst();
        calcularServico();
    }

    public void setCampos(Empresa empresa/*java.sql.Date dtInicial, java.sql.Date dtFinal*/) {
        txtBuscarEmpresa.setText(empresa.getDescricao());
        cbCategoria.getSelectionModel().select(empresa.getCategoria());
        cbTipoServico.getSelectionModel().selectFirst();
    }

    public void setCampos() {
        cbTipoServico.getSelectionModel().selectFirst();
        cbCategoria.getSelectionModel().selectFirst();
        for (Empresa empresa : StaticLista.getListaGlobalEmpresa()) {
            if (financeiroEncontrada.getIdEmpresa().getId().equals(empresa.getId())) {
                txtBuscarEmpresa.setText(empresa.getDescricao());
                break;
            }
        }
        txtLaudo.setText(financeiroEncontrada.getNumeroLaudo());
        while (!cbTipoServico.getSelectionModel().getSelectedItem().equals(financeiroEncontrada.getTipoServico())) {
            cbTipoServico.getSelectionModel().selectNext();
        }
        Categoria c = null;
        for (int i = 0; i < cbCategoria.getItems().size(); i++) {
            c = (Categoria) cbCategoria.getSelectionModel().getSelectedItem();
            if (!c.getNome().equals(financeiroEncontrada.getCategoria())) {
                cbCategoria.getSelectionModel().selectNext();
            }
        }
        txtHoraAdicional.setText(financeiroEncontrada.getHoraAdicional() + "");
        String n = financeiroEncontrada.getValorPago() + "";
        dtInicial.setValue(financeiroEncontrada.getDataInicial());
        txtValorPago.setText(n.replace(".", ","));
        dtFinal.setValue(financeiroEncontrada.getDataFinal());
        isUpdate = true;
    }
}
