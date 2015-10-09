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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
            tgContato = new ToggleGroup();
            tgTelefone = new ToggleGroup();
            tgEndereco = new ToggleGroup();
//            mOpenFile.showingProperty().addListener(new ChangeListener<Boolean>() {
//                @Override
//                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
//                    System.out.println("SHOW MENU");
//                    if (newValue.booleanValue()) {
//                        System.out.println("Showing|Clicked");
//                    } else {
//                        System.out.println("Not showing|Clicked again");
//                    }
//                }
//
//            });
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

            tcEnderecoSelecionado.setCellFactory(new Callback<TableColumn<Endereco, Integer>, TableCell<Endereco, Integer>>() {
                @Override
                public TableCell<Endereco, Integer> call(TableColumn<Endereco, Integer> param) {
                    final TableCell<Endereco, Integer> cell = new TableCell<Endereco, Integer>() {
                        @Override
                        public void updateItem(final Integer item, boolean empty) {
                            super.updateItem(item, empty);
                            RadioButton rb = new RadioButton();
                            if (empty) {
                                this.setText("");
                            } else {
                                if (item == 1) {
//                                    this.setText("true");
                                    rb.setId(item + "");
                                    rb.setToggleGroup(tgEndereco);
                                    rb.setSelected(true);
                                    rb.setDisable(true);
                                    setGraphic(rb);
                                } else {
                                    rb.setId(item + "");
                                    rb.setToggleGroup(tgEndereco);
                                    rb.setSelected(false);
                                    rb.setDisable(true);
                                    setGraphic(rb);
                                }
//                                rb.setOnAction(actionEvent -> {
//                                    rb.isSelected();
//                                    for (int i = 0; i < rbsEndereco.size(); i++) {
//                                        System.out.println("" + rbsEndereco.get(i).isSelected());
//                                    }
//                                    System.out.println("rb.isSelected() " + rb.isSelected());
//                                });
                            }
                        }
                    };
                    return cell;
                }
            });

            tcTelefoneSelecionado.setCellFactory(new Callback<TableColumn<Endereco, Integer>, TableCell<Endereco, Integer>>() {
                @Override
                public TableCell<Endereco, Integer> call(TableColumn<Endereco, Integer> param) {
                    final TableCell<Endereco, Integer> cell = new TableCell<Endereco, Integer>() {
                        @Override
                        public void updateItem(final Integer item, boolean empty) {
                            super.updateItem(item, empty);
                            RadioButton rb = new RadioButton();
                            if (empty) {
                                this.setText("");
                            } else {
                                if (item == 1) {
//                                    this.setText("true");
                                    rb.setToggleGroup(tgTelefone);
                                    rb.setSelected(true);
                                    rb.setDisable(true);
                                    setGraphic(rb);
                                } else {
                                    rb.setToggleGroup(tgTelefone);
                                    rb.setSelected(false);
                                    rb.setDisable(true);
                                    setGraphic(rb);
                                }
                            }
                        }
                    };
                    return cell;
                }
            });

            tcContatoSelecionado.setCellFactory(new Callback<TableColumn<Endereco, Integer>, TableCell<Endereco, Integer>>() {
                @Override
                public TableCell<Endereco, Integer> call(TableColumn<Endereco, Integer> param) {
                    final TableCell<Endereco, Integer> cell = new TableCell<Endereco, Integer>() {
                        @Override
                        public void updateItem(final Integer item, boolean empty) {
                            super.updateItem(item, empty);
                            RadioButton rb = new RadioButton();
                            if (empty) {
                                this.setText("");
                            } else {
                                if (item == 1) {
                                    rb.setToggleGroup(tgContato);
                                    rb.setSelected(true);
                                    rb.setDisable(true);
                                    setGraphic(rb);
                                } else {
                                    rb.setToggleGroup(tgContato);
                                    rb.setSelected(false);
                                    rb.setDisable(true);
                                    setGraphic(rb);
                                }
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
    private ComboBox<Categoria> cbCategoria;
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
    private Tab tabContato;
    @FXML
    private Tab tabTelefone;
    @FXML
    private Tab tabEndereco;
    @FXML
    private Tab tabEmpresa;
    @FXML
    private TableView<Endereco> tabelaEndereco;
    @FXML
    private TableView<Contato> tabelaContato;
    @FXML
    private TableView<Telefone> tabelaTelefone;
    @FXML
    private TableColumn tcEnderecoSelecionado;
    @FXML
    private TableColumn tcTelefoneSelecionado;
    @FXML
    private TableColumn tcContatoSelecionado;
    @FXML
    private TableColumn tcEstado;
    @FXML
    private TableColumn tcCidade;
    @FXML
    private Button btnMenuAbrir;
    @FXML
    private Button btnMenuNovo;
    @FXML
    private Button btnMenuSalvar;
    @FXML
    private Button btnMenuLixo;
    @FXML
    private TabPane tpPrincipal;
    @FXML
    private CheckBox ckbEnderecoSelecionado;
    @FXML
    private CheckBox ckbTelefoneSelecionado;
    @FXML
    private CheckBox ckbContatoSelecionado;

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
    private Endereco enderecoSel;
    private Contato contatoSel;
    private Telefone telefoneSel;

    private Endereco enderecoAtual;
    private Contato contatoAtual;
    private Telefone telefoneAtual;

    private ToggleGroup tgEndereco;
    private ToggleGroup tgTelefone;
    private ToggleGroup tgContato;
//    private ArrayList<RadioButton> rbsEndereco;
//    private ArrayList<RadioButton> rbsTelefone;
//    private ArrayList<RadioButton> rbsContato;;

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

                        EnderecoService ends = new EnderecoService();
                        for (Endereco end : tabelaEndereco.getItems()) {
                            ends.deletar(end);
                        }
                        JPA.em(false).close();

                        ContatoService cs = new ContatoService();
                        for (Contato cont : tabelaContato.getItems()) {
                            cs.deletar(cont);
                        }
                        JPA.em(false).close();

                        TelefoneService ts = new TelefoneService();
                        for (Telefone tel : tabelaTelefone.getItems()) {
                            ts.deletar(tel);
                        }
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
                    contato.setSelecionado(1);
                    cs.salvar(contato);
                    JPA.em(false).close();

                    TelefoneService ts = new TelefoneService();
                    Telefone tel = new Telefone();
                    tel.setIdEmpresa(empresaEncontrada);
                    tel.setFixo(txtTelefone.getText());
                    tel.setCelular(txtCelular.getText());
                    tel.setFax(txtFax.getText());
                    tel.setSelecionado(1);
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
                    endereco.setSelecionado(1);
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
                e.setCategoria(cbCategoria.getSelectionModel().getSelectedItem().getNome());
                EmpresaService es = new EmpresaService();
                es.editar(e);

                JPA.em(false).close();
                StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());

                resetarCampos();
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
    private void onMouseClickedTabela(MouseEvent event) {
        if (event.getClickCount() == 2) {
            onActionMenuAbrir();
        }
    }

    @FXML
    private void onActionMenuLixo() {
        if (tabEndereco.isSelected()) {
            try {
                if (enderecoSel != null) {
                    EnderecoService es = new EnderecoService();
                    es.deletar(enderecoSel);
                    JPA.em(false).close();
                    preencherTabelaEndereco();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tabContato.isSelected()) {
            try {
                if (contatoSel != null) {
                    ContatoService cs = new ContatoService();
                    cs.deletar(contatoSel);
                    JPA.em(false).close();
                    preencherTabelaContato();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tabTelefone.isSelected()) {
            try {
                if (telefoneSel != null) {
                    TelefoneService ts = new TelefoneService();
                    ts.deletar(telefoneSel);
                    JPA.em(false).close();
                    preencherTabelaTelefone();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onActionMenuSalvar() {
        if (tabEndereco.isSelected()) {
            if (validarCampos()) {
                try {
                    Endereco endereco = new Endereco();
                    if (enderecoSel != null) {
                        endereco = enderecoSel;
                    }
                    endereco.setBairro(txtBairro.getText());
                    endereco.setCep(txtCep.getText());
                    endereco.setComplemento(txtComplemento.getText());
                    endereco.setLogradouro(txtEndereco.getText());
                    endereco.setNumero(txtNumero.getText());
                    endereco.setIdEmpresa(empresaEncontrada);
                    endereco.setIdCidade(cbCidade.getValue());
                    endereco.setSelecionado(ckbEnderecoSelecionado.isSelected() ? 1 : 0);

                    EnderecoService es = new EnderecoService();
                    if (enderecoSel != null) {
                        es.editar(endereco);
                        if (endereco.selecionadoBoolean()) {
                            enderecoAtual = endereco;
                            for (Endereco end : tabelaEndereco.getItems()) {
                                if (!end.getId().equals(endereco.getId())) {
                                    end.setSelecionado(0);
                                    es.editar(end);
                                }
                            }
                        }
                    } else {
                        if (ckbEnderecoSelecionado.isSelected()) {
                            for (Endereco end : tabelaEndereco.getItems()) {
                                if (end.selecionadoBoolean()) {
                                    end.setSelecionado(0);
                                    es.editar(end);
                                    break;
                                }
                            }
                            enderecoAtual = endereco;
                        }
                        es.salvar(endereco);
                    }
                    StaticLista.setListaGlobalEndereco(es.findAll());
                    JPA.em(false).close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (tabContato.isSelected()) {
            if (validarCampos()) {
                try {
                    Contato contato = new Contato();
                    if (contatoSel != null) {
                        contato = contatoSel;
                    }
                    contato.setCpf(txtCpf.getText());
                    contato.setEmail(txtEmail.getText());
                    contato.setIdEmpresa(empresaEncontrada);
                    contato.setNome(txtContato.getText());
                    contato.setResponsavelTeste(txtResponsavel.getText());
                    contato.setRg(txtRg.getText());
                    contato.setSelecionado(ckbContatoSelecionado.isSelected() ? 1 : 0);

                    ContatoService cs = new ContatoService();
                    if (contatoSel != null) {
                        cs.editar(contato);
                        if (contato.selecionadoBoolean()) {
                            contatoAtual = contato;
                        }
                    } else {
                        if (ckbContatoSelecionado.isSelected()) {
                            for (Contato cont : tabelaContato.getItems()) {
                                if (cont.selecionadoBoolean()) {
                                    cont.setSelecionado(0);
                                    cs.editar(cont);
                                    break;
                                }
                            }
                            contatoAtual = contato;
                        }
                        cs.salvar(contato);
                    }
                    StaticLista.setListaGlobalContato(cs.findAll());
                    JPA.em(false).close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (tabTelefone.isSelected()) {
            if (validarCampos()) {
                try {
                    Telefone telefone = new Telefone();
                    if (telefoneSel != null) {
                        telefone = telefoneSel;
                    }
                    telefone.setCelular(txtCelular.getText());
                    telefone.setFax(txtFax.getText());
                    telefone.setFixo(txtTelefone.getText());
                    telefone.setIdEmpresa(empresaEncontrada);
                    telefone.setSelecionado(ckbTelefoneSelecionado.isSelected() ? 1 : 0);

                    TelefoneService ts = new TelefoneService();
                    if (telefoneSel != null) {
                        ts.editar(telefone);
                        if (telefone.selecionadoBoolean()) {
                            telefoneAtual = telefone;
                        }
                    } else {
                        if (ckbTelefoneSelecionado.isSelected()) {
                            for (Telefone tel : tabelaTelefone.getItems()) {
                                if (tel.getSelecionado() == 1) {
                                    tel.setSelecionado(0);
                                    ts.editar(tel);
                                    break;
                                }
                            }
                            telefoneAtual = telefone;
                        }
                        ts.salvar(telefone);
                    }
                    StaticLista.setListaGlobalTelefone(ts.findAll());
                    JPA.em(false).close();
                    preencherTabelaTelefone();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        defaultValuesOnTabs();
        desativarMenu();
    }

    @FXML
    private void onActionMenuNovo() {
        if (empresaEncontrada != null) {
            enderecoSel = null;
            contatoSel = null;
            telefoneSel = null;
            btnMenuSalvar.setDisable(false);
            btnMenuLixo.setDisable(true);
            if (tabEndereco.isSelected()) {
                txtEndereco.setText("");
                txtNumero.setText("");
                txtComplemento.setText("");
                txtBairro.setText("");
                txtCep.setText("");
                cbEstado.getSelectionModel().clearSelection();
                cbCidade.getSelectionModel().clearSelection();
                txtEndereco.requestFocus();
                ckbEnderecoSelecionado.setSelected(false);
            } else if (tabContato.isSelected()) {
                txtContato.setText("");
                txtCpf.setText("");
                txtRg.setText("");
                txtEmail.setText("");
                txtResponsavel.setText("");
                txtContato.requestFocus();
                ckbContatoSelecionado.setSelected(false);
            } else if (tabTelefone.isSelected()) {
                txtTelefone.setText("");
                txtFax.setText("");
                txtCelular.setText("");
                ckbTelefoneSelecionado.setSelected(false);
                txtTelefone.requestFocus();
            }
        }
    }

    @FXML
    private void onActionMenuAbrir() {
        if (empresaEncontrada != null) {
            if (tabEndereco.isSelected()) {
                enderecoSel = tabelaEndereco.getSelectionModel().getSelectedItem();
                if (enderecoSel != null) {
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
                    ckbEnderecoSelecionado.setSelected(endereco.selecionadoBoolean());
                    cbCidade.getSelectionModel().select(endereco.getIdCidade());
                } else {
                    avisoSelecaoTabela();
                }
            } else if (tabContato.isSelected()) {
                contatoSel = tabelaContato.getSelectionModel().getSelectedItem();
                if (contatoSel != null) {
                    ativiarMenu();
                    Contato contato = tabelaContato.getSelectionModel().getSelectedItem();
                    txtContato.setText(contato.getNome());
                    txtCpf.setText(contato.getCpf());
                    txtRg.setText(contato.getRg());
                    txtEmail.setText(contato.getEmail());
                    txtResponsavel.setText(contato.getResponsavelTeste());
                    ckbContatoSelecionado.setSelected(contato.selecionadoBoolean());
                } else {
                    avisoSelecaoTabela();
                }
            } else if (tabTelefone.isSelected()) {
                telefoneSel = tabelaTelefone.getSelectionModel().getSelectedItem();
                if (telefoneSel != null) {
                    ativiarMenu();
                    Telefone tel = tabelaTelefone.getSelectionModel().getSelectedItem();
                    txtTelefone.setText(tel.getFixo());
                    txtFax.setText(tel.getFax());
                    txtCelular.setText(tel.getCelular());
                    ckbTelefoneSelecionado.setSelected(tel.selecionadoBoolean());
                } else {
                    avisoSelecaoTabela();
                }
            }
        }
    }

    private void defaultValuesOnTabs() {
        if (tabEmpresa.isSelected()) {
            btnMenuAbrir.setDisable(true);
            btnMenuLixo.setDisable(true);
            btnMenuNovo.setDisable(true);
            btnMenuSalvar.setDisable(true);
        } else {
            btnMenuAbrir.setDisable(false);
            btnMenuNovo.setDisable(false);
        }
        if (enderecoAtual != null) {
            preencherTabelaContato();
            preencherTabelaEndereco();
            preencherTabelaTelefone();

            preencherEndereco(enderecoAtual);
            preencherContato(contatoAtual);
            preencherTelefone(telefoneAtual);
            btnMenuSalvar.setDisable(true);
            btnMenuLixo.setDisable(true);
        }

    }

    @FXML
    private void onSelectionChangedTabContato() {
        defaultValuesOnTabs();
    }

    @FXML
    private void onSelectionChangedTabTelefone() {
        defaultValuesOnTabs();
    }

    @FXML
    private void onSelectionChangedTabEndereco() {
        defaultValuesOnTabs();
    }

    @FXML
    private void onSelectionChangedTabEmpresa() {
        defaultValuesOnTabs();
    }

    private void avisoSelecaoTabela() {
        UtilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), "Selecione uma linha da tabela!!");
    }

    private void ativiarMenu() {
        btnMenuSalvar.setDisable(false);
        btnMenuLixo.setDisable(false);
    }

    private void desativarMenu() {
        btnMenuSalvar.setDisable(true);
        btnMenuLixo.setDisable(true);
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
        tabelaContato.setItems(null);
        tabelaEndereco.setItems(null);
        tabelaTelefone.setItems(null);
        btnMenuAbrir.setDisable(true);
        btnMenuLixo.setDisable(true);
        btnMenuNovo.setDisable(true);
        btnMenuSalvar.setDisable(true);

        txtEmpresa.setText("");
        txtaObs.setText("");
        txtCnpj.setText("");
        txtFax.setText("");
        txtIE.setText("");
        txtIM.setText("");
        txtNomeFantasia.setText("");

        enderecoSel = null;
        contatoSel = null;
        telefoneSel = null;
        enderecoAtual = null;
        contatoAtual = null;
        telefoneAtual = null;
        ckbEnderecoSelecionado.setSelected(false);
        ckbContatoSelecionado.setSelected(false);
        ckbTelefoneSelecionado.setSelected(false);

        txtEndereco.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtCep.setText("");
        cbEstado.getSelectionModel().clearSelection();
        cbCidade.getSelectionModel().clearSelection();

        txtContato.setText("");
        txtCpf.setText("");
        txtRg.setText("");
        txtEmail.setText("");
        txtResponsavel.setText("");

        txtTelefone.setText("");
        txtFax.setText("");
        txtCelular.setText("");

        empresaEncontrada = null;
        sceneManager.setEmpresaEncontrada(null);
    }

    private Endereco preencherTabelaEndereco() {
        EnderecoService es = new EnderecoService();
        Endereco endereco = null;
        tabelaEndereco.setItems(es.findByIdEmpresa(empresaEncontrada));
        for (Endereco e : tabelaEndereco.getItems()) {
            if (e.selecionadoBoolean()) {
                endereco = e;
                break;
            }
        }
        JPA.em(false).close();
        return endereco;
    }

    private Contato preencherTabelaContato() {
        ContatoService cs = new ContatoService();
        Contato contato = null;
        tabelaContato.setItems(cs.findByIdEmpresa(empresaEncontrada));
        for (Contato c : tabelaContato.getItems()) {
            if (c.selecionadoBoolean()) {
                contato = c;
                break;
            }
        }
        JPA.em(false).close();
        return contato;
    }

    private Telefone preencherTabelaTelefone() {
        TelefoneService ts = new TelefoneService();
        Telefone tel = null;
        tabelaTelefone.setItems(ts.findByIdEmpresa(empresaEncontrada));
        for (Telefone t : tabelaTelefone.getItems()) {
            if (t.selecionadoBoolean()) {
                tel = t;
                break;
            }
        }
        JPA.em(false).close();
        return tel;
    }

    private void preencherEndereco(Endereco e) {
        txtComplemento.setText(e.getComplemento());
        txtNumero.setText(e.getNumero());
        txtBairro.setText(e.getBairro());
        txtEndereco.setText(e.getLogradouro());
        txtCep.setText(e.getCep());
        ckbEnderecoSelecionado.setSelected(e.selecionadoBoolean());
        addEstado();
        cbEstado.getSelectionModel().select(enderecoAtual.getIdCidade().getIdEstado());
        addCidade(cbEstado.getValue().toString());
        cbCidade.getSelectionModel().select(enderecoAtual.getIdCidade());
    }

    private void preencherContato(Contato c) {
        txtContato.setText(c.getNome());
        txtEmail.setText(c.getEmail());
        txtCpf.setText(c.getCpf());
        txtResponsavel.setText(c.getResponsavelTeste());
        txtRg.setText(c.getRg());
        ckbContatoSelecionado.setSelected(c.selecionadoBoolean());
    }

    private void preencherTelefone(Telefone t) {
        txtTelefone.setText(t.getFixo());
        txtFax.setText(t.getFax());
        txtCelular.setText(t.getCelular());
        ckbTelefoneSelecionado.setSelected(t.selecionadoBoolean());
    }

    public void setCampos() {
        tgEndereco = new ToggleGroup();
        tgContato = new ToggleGroup();
        tgTelefone = new ToggleGroup();

        enderecoAtual = preencherTabelaEndereco();
        telefoneAtual = preencherTabelaTelefone();
        contatoAtual = preencherTabelaContato();

        if (enderecoAtual != null) {
            preencherEndereco(enderecoAtual);
        }
        if (contatoAtual != null) {
            preencherContato(contatoAtual);
        }
        if (telefoneAtual != null) {
            preencherTelefone(telefoneAtual);
        }

        txtEmpresa.setText(empresaEncontrada.getDescricao());
        txtaObs.setText(empresaEncontrada.getObservacao());
        txtNomeFantasia.setText(empresaEncontrada.getNomeFantasia());
        txtCnpj.setText(empresaEncontrada.getCnpj());
        txtIE.setText(empresaEncontrada.getInscricaoEstadual());
        txtIM.setText(empresaEncontrada.getInscricaoMunicipal());
        cbCategoria.getSelectionModel().selectFirst();
        for (int i = 0; i < cbCategoria.getItems().size(); i++) {
            if (((Categoria) cbCategoria.getItems().get(i)).getNome().equals(empresaEncontrada.getCategoria())) {
                cbCategoria.getSelectionModel().select(i);
            }
        }
        btnMenuAbrir.setDisable(false);
        btnMenuNovo.setDisable(false);
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
