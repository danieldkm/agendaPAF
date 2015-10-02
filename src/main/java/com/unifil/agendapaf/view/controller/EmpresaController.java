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
import javafx.scene.control.Menu;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class EmpresaController {

    @FXML
    public void initialize() {
        try {
            Image image = new Image("image/disk.png");
            ImageView iv = new ImageView(image);
            iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("mouse click disk ");
                }
            });
            mAdd.setGraphic(iv);

            image = new Image("image/delete.png");
            iv = new ImageView(image);
            iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("mouse click delete ");
                }
            });
            mRemove.setGraphic(iv);

            image = new Image("image/archive1.png");
            iv = new ImageView(image);
            iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("mouse click archive ");
                }
            });
            mOpenFile.setGraphic(iv);

            tcCidade.setCellFactory(new Callback<TableColumn<Endereco, Cidade>, TableCell<Endereco, Cidade>>() {
                @Override
                public TableCell<Endereco, Cidade> call(TableColumn<Endereco, Cidade> param) {
                    final TableCell<Endereco, Cidade> cell = new TableCell<Endereco, Cidade>() {
                        @Override
                        public void updateItem(final Cidade item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                this.setText("");
                            } else {
                                this.setText(item.getNome());
                            }
                        }
                    };
                    return cell;
                }

            });
            tcEstado.setCellFactory(new Callback<TableColumn<Endereco, Cidade>, TableCell<Endereco, Cidade>>() {
                @Override
                public TableCell<Endereco, Cidade> call(TableColumn<Endereco, Cidade> param) {
                    final TableCell<Endereco, Cidade> cell = new TableCell<Endereco, Cidade>() {
                        @Override
                        public void updateItem(final Cidade item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                this.setText("");
                            } else {
                                this.setText(item.getIdEstado().getNome());
                            }
                        }
                    };
                    return cell;
                }
            });

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
//                isTabelaEmpresa = StaticBoolean.isTabelaEmpresa();
                isUpdate = true;
                setCampos();
                sceneManager.setEmpresaEncontrada(null);
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
    @FXML
    private Menu mAdd;
    @FXML
    private Menu mRemove;
    @FXML
    private Menu mOpenFile;
    @FXML
    private Tab tabContato;
    @FXML
    private Tab tabTelefone;
    @FXML
    private Tab tabEndereco;
    @FXML
    private TableView<Endereco> tabelaEndereco;
    @FXML
    private TableView<Contato> tabelaContato;
    @FXML
    private TableView<Telefone> tabelaTelefone;
    @FXML
    private TableColumn tcEstado;
    @FXML
    private TableColumn tcCidade;

//    @FXMLmRemove
//    private Tab tabRemove;
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

                    if (txtaObs.getText().equals("")) {
                        e.setObservacao("");
//                    e.setTelefone(txtaObs.getText());
                    } else {
                        e.setObservacao(txtaObs.getText());
                    }
                    e.setDataCadastro(LocalDate.now());

                    e.setNomeFantasia(txtNomeFantasia.getText());
                    e.setCnpj(txtCnpj.getText());
                    e.setInscricaoEstadual(txtIE.getText());
                    e.setInscricaoMunicipal(txtIM.getText());
                    e.setCategoria(((Categoria) cbCategoria.getSelectionModel().getSelectedItem()).getNome());
                    EmpresaService es = new EmpresaService();
                    es.salvar(e);
                    empresaEncontrada = es.findLast();
                    JPA.em(false).close();
                    resetarCampos();
                    StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
                    if (isTabelaEmpresa) {
                        stage.close();
                    }

                    ContatoService cs = new ContatoService();
                    Contato contato = new Contato();
                    contato.setIdEmpresa(empresaEncontrada);
                    contato.setCpf(txtCpf.getText());
                    contato.setEmail(txtEmail.getText());
                    contato.setNome(txtContato.getText());
                    contato.setResponsavelTeste(txtResponsavel.getText());
                    contato.setRg(txtRg.getText());
                    contato.setSelecionado(true);
                    cs.salvar(contato);
                    JPA.em(false).close();

                    TelefoneService ts = new TelefoneService();
                    Telefone tel = new Telefone();
                    tel.setIdEmpresa(empresaEncontrada);
                    tel.setFixo(txtTelefone.getText());
                    tel.setCelular(txtCelular.getText());
                    tel.setFax(txtFax.getText());
                    tel.setSelecionado(true);
                    ts.salvar(tel);
                    JPA.em(false).close();
//                    e.setIdTelefone(ts.findLast());

                    EnderecoService enS = new EnderecoService();
                    Endereco endereco = new Endereco();
                    endereco.setIdEmpresa(empresaEncontrada);
                    endereco.setLogradouro(txtEndereco.getText());
                    endereco.setBairro(txtBairro.getText());
                    endereco.setCep(txtCep.getText());
                    endereco.setComplemento(txtComplemento.getText());
                    endereco.setNumero(txtNumero.getText());
                    endereco.setSelecionado(true);
                    if (cbEstado.getValue() == null || cbEstado.getValue().equals("")) {
                        endereco.setIdCidade(null);
//                        e.setIdCidade(null);
                    } else {
                        endereco.setIdCidade(cbCidade.getValue());
                    }
                    enS.salvar(endereco);
                    JPA.em(false).close();
//                    e.setIdEndereco(enS.findLast());

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

                e.setCnpj(txtCnpj.getText());
                e.setInscricaoEstadual(txtIE.getText());
                e.setInscricaoMunicipal(txtIM.getText());
                e.setDataCadastro(empresaEncontrada.getDataCadastro());
                e.setCategoria(((Categoria) cbCategoria.getSelectionModel().getSelectedItem()).getNome());
                EmpresaService es = new EmpresaService();
                es.editar(e);

                JPA.em(false).close();
                resetarCampos();
                StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());

                ContatoService cs = new ContatoService();
                Contato contato = null;
                for (Contato c : cs.findByIdEmpresa(empresaEncontrada)) {
                    if (c.getSelecionado()) {
                        contato = c;
                        break;
                    }
                }
                contato.setCpf(txtCpf.getText());
                contato.setEmail(txtEmail.getText());
                contato.setNome(txtContato.getText());
                contato.setResponsavelTeste(txtResponsavel.getText());
                contato.setRg(txtRg.getText());
                cs.editar(contato);
                JPA.em(false).close();

                TelefoneService ts = new TelefoneService();
                Telefone tel = null;
                for (Telefone t : ts.findByIdEmpresa(empresaEncontrada)) {
                    if (t.getSelecionado()) {
                        tel = t;
                        break;
                    }
                }
                tel.setFixo(txtTelefone.getText());
                tel.setCelular(txtCelular.getText());
                tel.setFax(txtFax.getText());
                ts.editar(tel);
//                e.setIdTelefone(ts.findById(tel.getId()));
                JPA.em(false).close();

                EnderecoService enS = new EnderecoService();
                Endereco endereco = null;
                for (Endereco e : enS.findByIdEmpresa(empresaEncontrada)) {
                    if (e.getSelecionado()) {
                        endereco = e;
                        break;
                    }
                }
                endereco.setLogradouro(txtEndereco.getText());
                endereco.setBairro(txtBairro.getText());
                endereco.setCep(txtCep.getText());
                endereco.setComplemento(txtComplemento.getText());
                endereco.setNumero(txtNumero.getText());
                if (cbEstado.getValue() == null || cbEstado.getValue().equals("")) {
                    endereco.setIdCidade(null);
                } else {
                    endereco.setIdCidade(cbCidade.getValue());
                }
                enS.editar(endereco);
//                e.setIdEndereco(enS.findById(endereco.getId()));
                JPA.em(false).close();

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

    @FXML
    private void onActionMenuOpenFile() {
        System.out.println("tabEnder " + tabEndereco.isSelected());
        System.out.println("tabContato " + tabContato.isSelected());
        System.out.println("tabTele " + tabTelefone.isSelected());
        if (tabEndereco.isSelected()) {
            System.out.println("end " + tabelaEndereco.getSelectionModel().getSelectedItem());
            if (tabelaEndereco.getSelectionModel().getSelectedItem() != null) {
                System.out.println("ok endereco");
                ativiarMenu();
                Endereco endereco = tabelaEndereco.getSelectionModel().getSelectedItem();
                txtEndereco.setText(endereco.getLogradouro());
                txtNumero.setText(endereco.getNumero());
                txtComplemento.setText(endereco.getComplemento());
                txtBairro.setText(endereco.getBairro());
                txtCep.setText(endereco.getCep());
                addEstado();
                cbEstado.getSelectionModel().select(endereco.getIdCidade().getIdEstado());
                addCidade(cbEstado.getValue().getUf());
                cbCidade.getSelectionModel().select(endereco.getIdCidade());
            } else {
                avisoSelecaoTabela();
            }
        } else if (tabContato.isSelected()) {
            System.out.println("cont " + tabelaContato.getSelectionModel().getSelectedItem());
            if (tabelaContato.getSelectionModel().getSelectedItem() != null) {
                System.out.println("ok contato");
                ativiarMenu();
                Contato contato = tabelaContato.getSelectionModel().getSelectedItem();
                txtContato.setText(contato.getNome());
                txtCpf.setText(contato.getCpf());
                txtRg.setText(contato.getRg());
                txtEmail.setText(contato.getEmail());
                txtResponsavel.setText(contato.getResponsavelTeste());
            } else {
                avisoSelecaoTabela();
            }
        } else if (tabTelefone.isSelected()) {
            System.out.println("tel " + tabelaTelefone.getSelectionModel().getSelectedItem());
            if (tabelaTelefone.getSelectionModel().getSelectedItem() != null) {
                System.out.println("ok tel ");
                ativiarMenu();
                Telefone tel = tabelaTelefone.getSelectionModel().getSelectedItem();
                txtTelefone.setText(tel.getFixo());
                txtFax.setText(tel.getFax());
                txtCelular.setText(tel.getCelular());
            } else {
                avisoSelecaoTabela();
            }
        }
    }

    private void avisoSelecaoTabela() {
        UtilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), "Selecione uma linha da tabela!!");
    }

    private void ativiarMenu() {
        mAdd.setDisable(false);
        mRemove.setDisable(false);
    }

    private void desativarMenu() {
        mAdd.setDisable(true);
        mRemove.setDisable(true);
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
        EnderecoService es = new EnderecoService();
        Endereco endereco = null;
        System.out.println("empresaEncontrada " + empresaEncontrada);
        System.out.println("empresaEncontrada.getId() " + empresaEncontrada.getId());
        System.out.println("empresaEncontrada.getDescricao() " + empresaEncontrada.getDescricao());
        tabelaEndereco.setItems(es.findByIdEmpresa(empresaEncontrada));
        for (Endereco e : tabelaEndereco.getItems()) {
            if (e.getSelecionado()) {
                endereco = e;
                break;
            }
        }
        JPA.em(false).close();
        ContatoService cs = new ContatoService();
        Contato contato = null;
        tabelaContato.setItems(cs.findByIdEmpresa(empresaEncontrada));
        for (Contato c : tabelaContato.getItems()) {
            if (c.getSelecionado()) {
                contato = c;
                break;
            }
        }
        JPA.em(false).close();
        TelefoneService ts = new TelefoneService();
        Telefone tel = null;
        tabelaTelefone.setItems(ts.findByIdEmpresa(empresaEncontrada));
        for (Telefone t : tabelaTelefone.getItems()) {
            if (t.getSelecionado()) {
                tel = t;
                break;
            }
        }
        JPA.em(false).close();

        txtComplemento.setText(endereco.getComplemento());
        txtNumero.setText(endereco.getNumero());
        txtBairro.setText(endereco.getBairro());
        txtEndereco.setText(endereco.getLogradouro());
        txtCep.setText(endereco.getCep());

        txtContato.setText(contato.getNome());
        txtEmail.setText(contato.getEmail());
        txtCpf.setText(contato.getCpf());
        txtResponsavel.setText(contato.getResponsavelTeste());
        txtRg.setText(contato.getRg());

        txtTelefone.setText(tel.getFixo());
        txtFax.setText(tel.getFax());
        txtCelular.setText(tel.getCelular());

        txtEmpresa.setText(empresaEncontrada.getDescricao());
        txtaObs.setText(empresaEncontrada.getObservacao());
        txtNomeFantasia.setText(empresaEncontrada.getNomeFantasia());
        txtCnpj.setText(empresaEncontrada.getCnpj());
        txtIE.setText(empresaEncontrada.getInscricaoEstadual());
        txtIM.setText(empresaEncontrada.getInscricaoMunicipal());
        addEstado();
        cbEstado.getSelectionModel().select(endereco.getIdCidade().getIdEstado());
        addCidade(cbEstado.getValue().toString());
        cbCidade.getSelectionModel().select(endereco.getIdCidade());
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
