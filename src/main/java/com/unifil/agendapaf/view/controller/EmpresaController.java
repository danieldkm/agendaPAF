package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Cidade;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Endereco;
import com.unifil.agendapaf.model.Estado;
import com.unifil.agendapaf.model.Telefone;
import com.unifil.agendapaf.model.aux.Categoria;
import com.unifil.agendapaf.service.ContatoService;
import com.unifil.agendapaf.service.EmpresaService;
import com.unifil.agendapaf.service.EnderecoService;
import com.unifil.agendapaf.service.TelefoneService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.MaskFieldUtil;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.time.LocalDate;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class EmpresaController {

    @FXML
    public void initialize() {
        try {
            MaskFieldUtil.cnpjField(txtCnpj);
            MaskFieldUtil.cpfField(txtCpf);
            MaskFieldUtil.telefoneField(txtTelefone);
            MaskFieldUtil.telefoneField(txtFax);
            MaskFieldUtil.telefoneField(txtCelular);
            MaskFieldUtil.rgField(txtRg);
            MaskFieldUtil.cepField(txtCep);
            MaskFieldUtil.numericField(txtNumero);

            sceneManager = SceneManager.getInstance();
            ObservableList<Categoria> listaCategoria = Controller.getCategorias();
            cbCategoria.setItems(listaCategoria);
            cbCategoria.getSelectionModel().selectFirst();

            addEstado();

            if (sceneManager.getEmpresaEncontrada() != null) {
                empresaEncontrada = sceneManager.getEmpresaEncontrada();
                sceneManager.setEmpresaEncontrada(null);
//                isTabelaEmpresa = StaticBoolean.isTabelaEmpresa();
                isUpdate = true;
                setCampos();
            }
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar empresa", e, "Exception:");
        }
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        try {
//            stage = primaryStage;
//            mainEmpresa = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.Empresa.getCaminho()));
//            Scene scene = new Scene(mainEmpresa);
//            stage.setScene(scene);
//            stage.setTitle("Cadastro de Empresa");
////        stage.initOwner(this.myParent);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.show();
//            stage.toFront();
////            stage.getIcons().add(Controller.icoPAF);
//            stage.setOnHidden(new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent t) {
//                    StaticBoolean.setEmpresa(false);
//                }
//            });
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//
//                @Override
//                public void handle(WindowEvent event) {
//                    if (txtBairro != null) {
//                        resetarCampos();
//                    }
//                    if (StaticBoolean.isAgenda()) {
//                        StaticBoolean.setAgenda(false);
//                    }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro do start empresa", e, "Exception:");
//        }
//    }
    @FXML
    private TextField txtEmpresa;
    @FXML
    private TextField txtContato;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextArea txtaObs;
    @FXML
    public VBox mainEmpresa;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<Cidade> cbCidade;
    @FXML
    private ComboBox<Estado> cbEstado;
    @FXML
    private ComboBox cbCategoria;
    @FXML
    private TextField txtNomeFantasia;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtFax;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtCnpj;
    @FXML
    private TextField txtIE;
    @FXML
    private TextField txtIM;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtResponsavel;
    @FXML
    private TextField txtRg;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtComplemento;

    private Empresa empresaEncontrada;
    private SceneManager sceneManager;
    private boolean isTabelaEmpresa = false;
    private boolean isUpdate = false;
    private Stage stage;
    private Empresa e;
    private ObservableList<Estado> filtraEstados;
    private ObservableList<Cidade> filtraCidade;
    private String txtCidade = "";
    private String txtEstado = "";

    @FXML
    private void actionBtnDeletar() {
        mainEmpresa.setDisable(true);
        try {
            if (empresaEncontrada != null) {
                Optional<ButtonType> result = UtilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.CertezaDeletar.getMensagem());
                if (result.get() == ButtonType.OK) {
                    try {
                        EmpresaService es = new EmpresaService();
                        es.deletar(empresaEncontrada);
                        JPA.em(false).close();
                        resetarCampos();
                        UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Deletado.getMensagem());
                        StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
                    } catch (Exception e) {
                        JPA.em(false).close();
                        UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.EmpresaErroTentarDeletar.getMensagem() + empresaEncontrada.getId(), e, "Exception");
                        e.printStackTrace();
                    }
                }
            } else {
                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.EmpresaErroNaoExiste.getMensagem());
            }
        } catch (Exception e) {
            JPA.em(false).close();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.EmpresaErroTentarDeletar.getMensagem() + empresaEncontrada.getId(), e, "Exception");
            e.printStackTrace();
        }
        mainEmpresa.setDisable(false);
    }

    @FXML
    private void actionBtnSalvar() throws Exception {
        mainEmpresa.setDisable(true);
        if (isUpdate) {
            actionBtnAlterar();
        } else {
            try {
                if (validarCampos()) {
                    e = new Empresa();
                    e.setDescricao(txtEmpresa.getText());

                    ContatoService cs = new ContatoService();
                    Contato contato = new Contato();
                    contato.setCpf(txtCpf.getText());
                    contato.setEmail(txtEmail.getText());
                    contato.setNome(txtContato.getText());
                    contato.setResponsavelTeste(txtResponsavel.getText());
                    contato.setRg(txtRg.getText());
                    cs.salvar(contato);
                    e.setIdContato(cs.findLast());

                    if (txtaObs.getText().equals("")) {
                        e.setObservacao("");
//                    e.setTelefone(txtaObs.getText());
                    } else {
                        e.setObservacao(txtaObs.getText());
                    }
                    e.setDataCadastro(LocalDate.now());

                    TelefoneService ts = new TelefoneService();
                    Telefone tel = new Telefone();
                    tel.setFixo(txtTelefone.getText());
                    tel.setCelular(txtCelular.getText());
                    tel.setFax(txtFax.getText());
                    ts.salvar(tel);
                    e.setIdTelefone(ts.findLast());

                    EnderecoService enS = new EnderecoService();
                    Endereco endereco = new Endereco();
                    endereco.setLogradouro(txtEndereco.getText());
                    endereco.setBairro(txtBairro.getText());
                    endereco.setCep(txtCep.getText());
                    endereco.setComplemento(txtComplemento.getText());
                    endereco.setNumero(txtNumero.getText());
                    enS.salvar(endereco);

                    e.setIdEndereco(enS.findLast());

                    if (cbEstado.getValue() == null || cbEstado.getValue().equals("")) {
                        endereco.setIdCidade(null);
//                        e.setIdCidade(null);
                    } else {
                        endereco.setIdCidade(cbCidade.getValue());
                    }
                    e.setNomeFantasia(txtNomeFantasia.getText());
                    e.setCnpj(txtCnpj.getText());
                    e.setInscricaoEstadual(txtIE.getText());
                    e.setInscricaoMunicipal(txtIM.getText());
                    e.setCategoria(((Categoria) cbCategoria.getSelectionModel().getSelectedItem()).getNome());
                    EmpresaService es = new EmpresaService();
                    es.salvar(e);
                    JPA.em(false).close();
                    resetarCampos();
                    StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
                    if (isTabelaEmpresa) {
                        stage.close();
                    }
                }
            } catch (Exception e) {
                JPA.em(false).close();
                UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e, "Exception:");
                e.printStackTrace();
            }
        }
        mainEmpresa.setDisable(false);
    }

    @FXML
    private void actionBtnCancelar() {
        resetarCampos();
    }

    @FXML
    protected void iniciarTabelaEmpresa() {
        stage.close();
        sceneManager.showTabelaEmpresa(true, false, false, false, false, false, false);
//        StaticBoolean.setEmpresa(true);
    }

    private void actionBtnAlterar() {
        try {
            if (validarCampos()) {
                e = new Empresa();
                e.setId(empresaEncontrada.getId());
                e.setDescricao(txtEmpresa.getText());
                e.setNomeFantasia(txtNomeFantasia.getText());
                e.setObservacao(txtaObs.getText());

                ContatoService cs = new ContatoService();
                Contato contato = empresaEncontrada.getIdContato();
                contato.setCpf(txtCpf.getText());
                contato.setEmail(txtEmail.getText());
                contato.setNome(txtContato.getText());
                contato.setResponsavelTeste(txtResponsavel.getText());
                contato.setRg(txtRg.getText());
                cs.editar(contato);
                e.setIdContato(cs.findById(contato.getId()));
                JPA.em(false).close();

                TelefoneService ts = new TelefoneService();
                Telefone tel = empresaEncontrada.getIdTelefone();
                tel.setFixo(txtTelefone.getText());
                tel.setCelular(txtCelular.getText());
                tel.setFax(txtFax.getText());
                ts.editar(tel);
                e.setIdTelefone(ts.findById(tel.getId()));
                JPA.em(false).close();

                EnderecoService enS = new EnderecoService();
                Endereco endereco = empresaEncontrada.getIdEndereco();
                endereco.setLogradouro(txtEndereco.getText());
                endereco.setBairro(txtBairro.getText());
                endereco.setCep(txtCep.getText());
                endereco.setComplemento(txtComplemento.getText());
                endereco.setNumero(txtNumero.getText());
                enS.editar(endereco);
                e.setIdEndereco(enS.findById(endereco.getId()));
                JPA.em(false).close();

                e.setCnpj(txtCnpj.getText());
                e.setInscricaoEstadual(txtIE.getText());
                e.setInscricaoMunicipal(txtIM.getText());
                if (cbEstado.getValue() == null || cbEstado.getValue().equals("")) {
                    endereco.setIdCidade(null);
                } else {
                    endereco.setIdCidade(cbCidade.getValue());
                }
                e.setDataCadastro(empresaEncontrada.getDataCadastro());
                e.setCategoria(((Categoria) cbCategoria.getSelectionModel().getSelectedItem()).getNome());
                EmpresaService es = new EmpresaService();
                es.editar(e);

                JPA.em(false).close();
                resetarCampos();
                StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
                empresaEncontrada = null;
                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
                isUpdate = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JPA.em(false).close();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroAtualizar.getMensagem(), e, "Exception:");
        }
    }

    @FXML
    private void onKeyReleasedCbEstado(KeyEvent ke) {
        txtEstado += ke.getText().toLowerCase();
        if (ke.getCode() == KeyCode.ENTER) {
            if (cbEstado.getValue() != null) {
                addCidade(cbEstado.getValue().toString());
            }
        } else if (ke.getCode() != KeyCode.LEFT
                && ke.getCode() != KeyCode.RIGHT && ke.getCode() != KeyCode.UP
                && ke.getCode() != KeyCode.DOWN && ke.getCode() != KeyCode.ENTER
                && ke.getCode() != KeyCode.TAB && ke.getCode() != KeyCode.BACK_SPACE
                && ke.getCode() != KeyCode.DELETE) {
            filtraEstados = FXCollections.observableArrayList();
            cbEstado.getItems().stream().filter(es -> es.getNome().toLowerCase().startsWith(txtEstado.toLowerCase()))
                    .forEach(c -> filtraEstados.add(c));
            cbEstado.setItems(filtraEstados);
        } else if (ke.getCode() == KeyCode.DOWN) {
            if (!cbEstado.isShowing()) {
                cbEstado.show();
                cbEstado.setItems(StaticLista.getListaGlobalEstado());
            }
        } else if (ke.getCode() == KeyCode.TAB || ke.getCode() == KeyCode.BACK_SPACE || ke.getCode() == KeyCode.DELETE) {
            txtEstado = "";
            System.out.println("ADD ESTADO");
            addEstado();
        }
    }

    @FXML
    private void onKeyReleasedCbCidade(KeyEvent ke
    ) {
        try {
            txtCidade += ke.getText().toLowerCase();
            if (ke.getCode() != KeyCode.LEFT
                    && ke.getCode() != KeyCode.RIGHT && ke.getCode() != KeyCode.UP
                    && ke.getCode() != KeyCode.DOWN && ke.getCode() != KeyCode.ENTER
                    && ke.getCode() != KeyCode.BACK_SPACE && ke.getCode() != KeyCode.DELETE
                    && ke.getCode() != KeyCode.TAB) {
                filtraCidade = FXCollections.observableArrayList();
                cbCidade.getItems().stream().filter(s -> s.getNome().toLowerCase().startsWith(txtCidade.toLowerCase()))
                        .forEach(c -> filtraCidade.add(c));
                cbCidade.setItems(filtraCidade);
            } else if (ke.getCode() == KeyCode.TAB || ke.getCode() == KeyCode.BACK_SPACE || ke.getCode() == KeyCode.DELETE) {
                addCidade(cbEstado.getValue().toString());
                txtCidade = "";
            } else if (ke.getCode() == KeyCode.DOWN) {
                cbCidade.show();
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void setOnActionCBEstado() {
        onMouseClickedGRID();
    }

    @FXML
    private void onMouseClickedGRID() {
        if (cbCidade.getValue() == null) {
            addCidade(cbEstado.getValue().toString());
            txtCidade = "";
        }
    }

    private void addEstado() {
        cbEstado.getItems().clear();
        if (filtraEstados == null) {
            filtraEstados = FXCollections.observableArrayList();
        }
        filtraEstados.clear();
        for (Estado estado : StaticLista.getListaGlobalEstado()) {
            filtraEstados.add(estado);
        }
        cbEstado.setItems(filtraEstados);
    }

    private void addCidade(String uf) {
        cbCidade.getItems().clear();
        filtraCidade = FXCollections.observableArrayList();
        StaticLista.getListaGlobalCidade().forEach((cidade) -> {
            if (uf.equals(cidade.getIdEstado().getNome())) {
                filtraCidade.add(cidade);
            }
        });
        cbCidade.setItems(filtraCidade);
    }

    private boolean validarCampos() {
        ValidationSupport validationSupport = new ValidationSupport();
        boolean ok = true;
        String preencher = "";
        if (txtEmpresa.getText().equals("")) {
            preencher += "Preencher Empresa\n";
            txtEmpresa.requestFocus();
            validationSupport.registerValidator(txtEmpresa, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            ok = false;
        }
        if (txtContato.getText().equals("")) {
            txtContato.requestFocus();
            preencher += "Preencher Contato\n";
            validationSupport.registerValidator(txtContato, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            ok = false;
        }
        if (txtEmail.getText().equals("")) {
            txtEmail.requestFocus();
            preencher += "Preencher E-mail\n";
            validationSupport.registerValidator(txtEmail, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            ok = false;
        } else if (!txtEmail.getText().contains("@")) {
            txtEmail.requestFocus();
            preencher += "E-mail inválido\n";
            validationSupport.registerValidator(txtEmail, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            ok = false;
        } else if (txtEmail.getText().split("@").length > 2) {
            txtEmail.requestFocus();
            preencher += "Sistema não aceita dois e-mails no mesmo campo!\n";
            validationSupport.registerValidator(txtEmail, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            ok = false;
        }
        if (txtTelefone.getText().equals("")) {
            txtTelefone.requestFocus();
            preencher += "Preencheer Telefone\n";
            validationSupport.registerValidator(txtTelefone, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            ok = false;
        }
        if (cbEstado.getSelectionModel().getSelectedItem() != null) {
            if (!cbEstado.getSelectionModel().getSelectedItem().equals("")) {
                if (cbCidade.getSelectionModel().getSelectedItem() == null) {
                    cbCidade.requestFocus();
                    preencher += "Selecionar Cidade\n";
                    validationSupport.registerValidator(cbCidade, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                    ok = false;
                } else if (cbCidade.getSelectionModel().getSelectedItem().equals("")) {
                    cbCidade.requestFocus();
                    validationSupport.registerValidator(cbCidade, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                    preencher += "Selecionar Cidade\n";
                    ok = false;
                }
            }
        } else {
            if (cbEstado.getValue() == null) {
                preencher += "Selecionar Estado\n";
                validationSupport.registerValidator(cbEstado, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                ok = false;
            } else if (cbCidade.getValue() == null) {
                preencher += "Selecionar Cidade\n";
                validationSupport.registerValidator(cbCidade, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
                ok = false;
            }
        }
        if (!ok) {
            UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), "Validando campos", preencher);
        }
        return ok;
    }

    private void resetarCampos() {
        txtContato.setText("");
        txtEmpresa.setText("");
        txtTelefone.setText("");
        txtaObs.setText("");
        txtEmail.setText("");
        txtBairro.setText("");
        txtCelular.setText("");
        txtCep.setText("");
        txtCnpj.setText("");
        txtCpf.setText("");
        txtFax.setText("");
        txtIE.setText("");
        txtIM.setText("");
        txtNomeFantasia.setText("");
        txtResponsavel.setText("");
        txtEndereco.setText("");
        txtComplemento.setText("");
        txtNumero.setText("");

        cbEstado.getSelectionModel().selectFirst();
        cbCidade.getSelectionModel().selectFirst();
        empresaEncontrada = null;
        sceneManager.setEmpresaEncontrada(null);

    }

    public void setCampos() {
        txtComplemento.setText(empresaEncontrada.getIdEndereco().getComplemento());
        txtNumero.setText(empresaEncontrada.getIdEndereco().getNumero());
        txtContato.setText(empresaEncontrada.getIdContato().getNome());
        txtEmail.setText(empresaEncontrada.getIdContato().getEmail());
        txtCpf.setText(empresaEncontrada.getIdContato().getCpf());
        txtResponsavel.setText(empresaEncontrada.getIdContato().getResponsavelTeste());
        txtRg.setText(empresaEncontrada.getIdContato().getRg());
        txtEmpresa.setText(empresaEncontrada.getDescricao());
        txtTelefone.setText(empresaEncontrada.getIdTelefone().getFixo());
        txtFax.setText(empresaEncontrada.getIdTelefone().getFax());
        txtCelular.setText(empresaEncontrada.getIdTelefone().getCelular());
        txtaObs.setText(empresaEncontrada.getObservacao());
        txtNomeFantasia.setText(empresaEncontrada.getNomeFantasia());
        txtEndereco.setText(empresaEncontrada.getIdEndereco().getLogradouro());
        txtBairro.setText(empresaEncontrada.getIdEndereco().getBairro());
        txtCep.setText(empresaEncontrada.getIdEndereco().getCep());
        txtCnpj.setText(empresaEncontrada.getCnpj());
        txtIE.setText(empresaEncontrada.getInscricaoEstadual());
        txtIM.setText(empresaEncontrada.getInscricaoMunicipal());
        addEstado();
        cbEstado.getSelectionModel().select(empresaEncontrada.getIdEndereco().getIdCidade().getIdEstado());
        addCidade(cbEstado.getValue().toString());
        cbCidade.getSelectionModel().select(empresaEncontrada.getIdEndereco().getIdCidade());
        cbCategoria.getSelectionModel().selectFirst();
        for (int i = 0; i < cbCategoria.getItems().size(); i++) {
            if (((Categoria) cbCategoria.getItems().get(i)).getNome().equals(empresaEncontrada.getCategoria())) {
                cbCategoria.getSelectionModel().select(i);
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
//        this.stage.setOnHidden(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent t) {
//                StaticBoolean.setEmpresa(false);
//            }
//        });
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                if (txtBairro != null) {
                    resetarCampos();
                }
            }
        });
    }

    public void setMainEmpresa(Parent mainEmpresa) {
        this.mainEmpresa = (VBox) mainEmpresa;
    }

    public void setIsTabelaEmpresa(boolean isTabelaEmpresa) {
        this.isTabelaEmpresa = isTabelaEmpresa;
    }

}
