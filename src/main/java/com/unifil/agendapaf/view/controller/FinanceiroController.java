package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.aux.Categoria;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.aux.Servico;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.service.FinanceiroService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.view.util.enums.EnumServico;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class FinanceiroController {

    @FXML
    public void initialize() {
        try {
            sceneManager = SceneManager.getInstance();
            servicos = Controller.getServicos();
            categorias = Controller.getCategorias();
            setComboBox();
            cbCategoria.getSelectionModel().selectFirst();
            if (sceneManager.getAgendaEncontrada() != null && sceneManager.getEmpresaEncontrada() != null) {
                empresaEncontrada = sceneManager.getEmpresaEncontrada();
                setCampos(empresaEncontrada, sceneManager.getAgendaEncontrada());
            }
            if (sceneManager.getFinanceiroEncontrada() != null) {
                financeiroEncontrada = sceneManager.getFinanceiroEncontrada();
                sceneManager.setFinanceiroEncontrada(null);
                System.out.println("PASSO1");
                setCampos();
            }
            sceneManager.setAgendaEncontrada(null);
            sceneManager.setEmpresaEncontrada(null);
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar financeiro", e, "Exception:");
        }
    }

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

    private Stage stage;
    private Empresa empresaEncontrada;
    private Financeiro financeiroEncontrada;
    private ObservableList<Servico> servicos;
    private ObservableList<Categoria> categorias;
    private boolean isUpdate = false;
    private boolean isParametro = false;
    private double valorServico = 0;
    private double porCategoria = 0;
    private SceneManager sceneManager;

    @FXML
    private void actionBuscarEmpresa() {
        stage.close();
        sceneManager.showTabelaEmpresa(false, false, false, false, false, true);
    }

    @FXML
    private void iniciarCadastroParametro() {
        stage.close();
        sceneManager.showParametro();
    }

    @FXML
    private void iniciarTabelaFinanceiro() {
        stage.close();
        sceneManager.showTabelaFinanceiro();
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
                    if (((Categoria) cbCategoria.getSelectionModel().getSelectedItem()).getNome().equals(categoria.getNome())) {
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
                        UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Não é um número inteiro");
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
                        UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Não é um número inteiro");
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
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
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
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
                }
                JPA.em(false).close();
                actionBtnLimpar();
                StaticLista.setListaGlobalFinanceiro(Controller.getFinanceiros());
            } catch (Exception e) {
                UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e, "Exception");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void actionBtnDeletar() {
        if (financeiroEncontrada != null) {
            try {
                Optional<ButtonType> result = UtilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.CertezaDeletar.getMensagem());
                if (result.get() == ButtonType.OK) {
                    FinanceiroService fs = new FinanceiroService();
                    fs.deletar(financeiroEncontrada);
                    JPA.em(false).close();
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Deletado.getMensagem());
                    StaticLista.setListaGlobalFinanceiro(Controller.getFinanceiros());
                }
            } catch (Exception ex) {
                JPA.em(false).close();
                Logger.getLogger(FinanceiroController.class.getName()).log(Level.SEVERE, null, ex);
                UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroDeletar.getMensagem(), ex, "Exception");
            }
            limpar();
        } else {
            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.FinanceiroErroNaoSelecionado.getMensagem());
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
        removerStyle();
        if (txtBuscarEmpresa.getText().equals("")) {
            preencher += EnumMensagem.InformeEmpresa.getMensagem() + "\n";
            validationSupport.registerValidator(txtBuscarEmpresa, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            txtBuscarEmpresa.requestFocus();
            ok = false;
        }
        if (cbTipoServico.getSelectionModel().getSelectedItem() == null) {
            preencher += EnumMensagem.InformeComboBox.getMensagem() + "\n";
            validationSupport.registerValidator(cbTipoServico, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            cbTipoServico.requestFocus();
            ok = false;
        } else if (cbTipoServico.getSelectionModel().getSelectedItem().equals("")) {
            preencher += EnumMensagem.InformeComboBox.getMensagem() + "\n";
            validationSupport.registerValidator(cbTipoServico, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            cbTipoServico.requestFocus();
            ok = false;
        }

        if (cbCategoria.getSelectionModel().getSelectedItem() == null) {
            preencher += EnumMensagem.InformeComboBox.getMensagem() + "\n";
            validationSupport.registerValidator(cbCategoria, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            cbCategoria.requestFocus();
            ok = false;
        } else if (((Categoria) cbCategoria.getSelectionModel().getSelectedItem()).getNome().equals("")) {
            preencher += EnumMensagem.InformeComboBox.getMensagem() + "\n";
            validationSupport.registerValidator(cbCategoria, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            cbCategoria.requestFocus();
            ok = false;
        }

        if (txtValorPago.getText().equals("") || txtValorPago.getText().contains(".")) {
            preencher += EnumMensagem.FinanceiroTxtValorInvalido.getMensagem() + "\n";
            validationSupport.registerValidator(txtValorPago, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
            txtValorPago.requestFocus();
            ok = false;
        }

        if (!txtHoraAdicional.getText().equals("")) {
            try {
                int n = Integer.parseInt(txtHoraAdicional.getText());
            } catch (Exception e) {
                preencher += EnumMensagem.FinanceiroHoraAdicionalInvalido.getMensagem() + "\n";
                validationSupport.registerValidator(txtHoraAdicional, Validator.createEmptyValidator(EnumMensagem.RequerComboBox.getMensagem()));
                txtHoraAdicional.requestFocus();
                ok = false;
            }
        }
        if (dtInicial.getValue() == null) {
            preencher += EnumMensagem.FinanceiroDataInicialInvalido.getMensagem() + "\n";
            validationSupport.registerValidator(dtInicial, Validator.createEmptyValidator(EnumMensagem.RequerDtInicial.getMensagem()));
            dtInicial.requestFocus();
            ok = false;
        }

        if (dtFinal.getValue() == null) {
            preencher += EnumMensagem.FinanceiroDataFinalInvalido.getMensagem() + "\n";
            validationSupport.registerValidator(dtFinal, Validator.createEmptyValidator(EnumMensagem.RequerDtFinal.getMensagem()));
            dtInicial.requestFocus();
            ok = false;
        }

        if (!ok) {
            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), "Validando campos", preencher);
        }
        return ok;
    }

    private void removerStyle() {
        txtBuscarEmpresa.setStyle(null);
        txtHoraAdicional.setStyle(null);
        txtValorPago.setStyle(null);
        cbCategoria.setStyle(null);
        cbTipoServico.setStyle(null);
        dtInicial.setStyle(null);
        dtFinal.setStyle(null);

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
        empresaEncontrada = empresa;
        txtBuscarEmpresa.setText(empresa.getDescricao());
        dtInicial.setValue(agenda.getDataInicial());
        dtFinal.setValue(agenda.getDataFinal());
        for (int i = 0; i < cbCategoria.getItems().size(); i++) {
            if (((Categoria) cbCategoria.getItems().get(i)).getNome().equals(empresa.getCategoria())) {
                cbCategoria.getItems().get(i);
            }
        }
        cbTipoServico.getSelectionModel().selectFirst();
        calcularServico();
    }

    public void setCampos(Empresa empresa/*java.sql.Date dtInicial, java.sql.Date dtFinal*/) {
        System.out.println("EMPRESA CATEGORIA " + empresa.getCategoria());
        empresaEncontrada = empresa;
        txtBuscarEmpresa.setText(empresa.getDescricao());
        for (int i = 0; i < cbCategoria.getItems().size(); i++) {
            if (((Categoria) cbCategoria.getItems().get(i)).getNome().equals(empresa.getCategoria())) {
                cbCategoria.getSelectionModel().select(i);
                break;
            }
        }
        cbTipoServico.getSelectionModel().selectFirst();
        calcularServico();
    }

    public void setCampos() {
        cbTipoServico.getSelectionModel().selectFirst();
        cbCategoria.getSelectionModel().selectFirst();

        empresaEncontrada = financeiroEncontrada.getIdEmpresa();
        txtBuscarEmpresa.setText(financeiroEncontrada.getIdEmpresa().getDescricao());

        while (!cbTipoServico.getSelectionModel().getSelectedItem().equals(financeiroEncontrada.getTipoServico())) {
            cbTipoServico.getSelectionModel().selectNext();
        }
        for (int i = 0; i < cbCategoria.getItems().size(); i++) {
            if (((Categoria) cbCategoria.getItems().get(i)).getNome().equals(financeiroEncontrada.getCategoria())) {
                cbCategoria.getSelectionModel().select(i);
                break;
            }
        }
        if (financeiroEncontrada.getTipoServico().equals(EnumServico.HoraAdicional.getServico())) {
            txtHoraAdicional.setText(financeiroEncontrada.getHoraAdicional() + "");
        }
        String n = financeiroEncontrada.getValorPago() + "";
        txtValorPago.setText(n.replace(".", ","));
        txtLaudo.setText(financeiroEncontrada.getNumeroLaudo());
        dtInicial.setValue(financeiroEncontrada.getDataInicial());
        dtFinal.setValue(financeiroEncontrada.getDataFinal());
        isUpdate = true;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainFinanceiro(VBox mainFinanceiro) {
        this.mainFinanceiro = mainFinanceiro;
    }

//    public void setCampos(Empresa empresa) {
//        empresaEncontrada = empresa;
//        setCampos(empresa);
//    }
}
