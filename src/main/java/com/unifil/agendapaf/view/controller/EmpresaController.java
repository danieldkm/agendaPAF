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
import com.unifil.agendapaf.util.UtilTexto;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class EmpresaController {

    @FXML
    public void initialize() {
        try {
            tgContato = new ToggleGroup();
            tgTelefone = new ToggleGroup();
            tgEndereco = new ToggleGroup();
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
                                this.setText(item.getUf());
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
//            MaskFieldUtil.cepField(txtCep);
            MaskFieldUtil.numericField(txtNumero);

            sceneManager = SceneManager.getInstance();
            ObservableList<Categoria> listaCategoria = Controller.getCategorias();
            cbCategoria.setItems(listaCategoria);
            cbCategoria.getSelectionModel().selectFirst();

            addEstado();

            if (sceneManager.getEmpresaEncontrada() != null) {
                empresaEncontrada = sceneManager.getEmpresaEncontrada().clone();
                isUpdate = true;
                setCampos();
                sceneManager.setEmpresaEncontrada(null);
            }
            defaultBind();
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar empresa", e, "Exception:");
        }
    }

    private void defaultBind() {
        setBindComponentsWithEmpresa();
        if (enderecoSel == null) {
            enderecoSel = new Endereco();
        }
        if (telefoneSel == null) {
            telefoneSel = new Telefone();
        }
        if (contatoSel == null) {
            contatoSel = new Contato();
        }
        setBindComponentsWithEndereco();
        setBindComponentsWithContato();
        setBindComponentsWithTelefone();
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
//    private Empresa empresa;
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

    private ValidationSupport validationSupport = new ValidationSupport();

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
        try {
            if (isUpdate) {
                isUpdate = false;
                empresaEncontrada.setCategoria(cbCategoria.getSelectionModel().getSelectedItem().getNome());
                EmpresaService es = new EmpresaService();
                es.editar(empresaEncontrada);
                JPA.em(false).close();
                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
                resetarCampos();
                StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
            } else {
                if (validarCampos()) {
                    empresaEncontrada.setDataCadastro(LocalDate.now());
                    empresaEncontrada.setCategoria(((Categoria) cbCategoria.getSelectionModel().getSelectedItem()).getNome());

                    EmpresaService es = new EmpresaService();
                    es.salvar(empresaEncontrada);
                    empresaEncontrada = es.findLast();
                    JPA.em(false).close();

                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
                    if (isTabelaEmpresa) {
                        stage.close();
                    }

                    EnderecoService enS = new EnderecoService();
                    enderecoSel.setIdEmpresa(empresaEncontrada);
                    enderecoSel.setSelecionado(new Integer(1));
                    enS.salvar(enderecoSel);
                    JPA.em(false).close();

                    ContatoService cs = new ContatoService();
                    contatoSel.setIdEmpresa(empresaEncontrada);
                    contatoSel.setSelecionado(1);
                    cs.salvar(contatoSel);
                    JPA.em(false).close();

                    TelefoneService ts = new TelefoneService();
                    telefoneSel.setIdEmpresa(empresaEncontrada);
                    telefoneSel.setSelecionado(1);
                    ts.salvar(telefoneSel);
                    JPA.em(false).close();
                    resetarCampos();
                    StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
                }
            }
        } catch (Exception e) {
            if (JPA.em(false).isOpen()) {
                JPA.em(false).close();
            }
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e, "Exception:");
            e.printStackTrace();
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

//    private void actionBtnAlterar() {
//        try {
//            if (validarCampos()) {
//                empresa = new Empresa();
//                empresa.setId(empresaEncontrada.getId());
//                empresa.setDescricao(txtEmpresa.getText());
//                empresa.setNomeFantasia(txtNomeFantasia.getText());
//                empresa.setObservacao(txtaObs.getText());
//
//                empresa.setCnpj(txtCnpj.getText());
//                empresa.setInscricaoEstadual(txtIE.getText());
//                empresa.setInscricaoMunicipal(txtIM.getText());
//                empresa.setDataCadastro(empresaEncontrada.getDataCadastro());
//                empresa.setCategoria(cbCategoria.getSelectionModel().getSelectedItem().getNome());
//                EmpresaService es = new EmpresaService();
//                es.editar(empresa);
//
//                JPA.em(false).close();
//                StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
//
//                resetarCampos();
//                empresaEncontrada = null;
//                UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
//                isUpdate = false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JPA.em(false).close();
//            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroAtualizar.getMensagem(), e, "Exception:");
//        }
//    }
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
//            System.out.println("ADD ESTADO");
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
    private void onActionMenuLixo() {
        if (tabEndereco.isSelected()) {
            try {
                if (enderecoSel != null) {
                    if (enderecoSel.selecionadoBoolean()) {
                        UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.EmpresaErroEnderecoSelecionado.getMensagem());
                    } else {
                        EnderecoService es = new EnderecoService();
                        es.deletar(enderecoSel);
                        JPA.em(false).close();
                        preencherTabelaEndereco();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tabContato.isSelected()) {
            try {
                if (contatoSel != null) {
                    if (contatoSel.selecionadoBoolean()) {
                        UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.EmpresaErroContatoSelecionado.getMensagem());
                    } else {
                        ContatoService cs = new ContatoService();
                        cs.deletar(contatoSel);
                        JPA.em(false).close();
                        preencherTabelaContato();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tabTelefone.isSelected()) {
            try {
                if (telefoneSel != null) {
                    if (telefoneSel.selecionadoBoolean()) {
                        UtilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.EmpresaErroTelefoneSelecionado.getMensagem());
                    } else {
                        TelefoneService ts = new TelefoneService();
                        ts.deletar(telefoneSel);
                        JPA.em(false).close();
                        preencherTabelaTelefone();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void editarEndereco(EnderecoService es) {
        enderecoSel.setSelecionado(new Integer(1));
        enderecoAtual = enderecoSel.clone();
        boolean nenhum = true;
        for (Endereco end : tabelaEndereco.getItems()) {
            if (!end.getId().equals(enderecoSel.getId()) && end.selecionadoBoolean()) {
                end.setSelecionado(0);
                es.editar(end);
                nenhum = false;
            }
        }
        if (nenhum) {
            enderecoSel.setSelecionado(new Integer(0));
            ckbEnderecoSelecionado.setSelected(nenhum);
        }
    }

    private void editarContato(ContatoService cs) {
        contatoSel.setSelecionado(new Integer(1));
        contatoAtual = contatoSel.clone();
        boolean nenhum = true;
        for (Contato cont : tabelaContato.getItems()) {
            if (!cont.getId().equals(contatoSel.getId()) && cont.selecionadoBoolean()) {
                cont.setSelecionado(0);
                cs.editar(cont);
                nenhum = false;
            }
        }
        if (nenhum) {
            contatoSel.setSelecionado(new Integer(0));
            ckbEnderecoSelecionado.setSelected(nenhum);
        }
    }

    private void editarTelefone(TelefoneService ts) {
        telefoneSel.setSelecionado(new Integer(1));
        telefoneAtual = telefoneSel.clone();
        boolean nenhum = true;
        for (Telefone tel : tabelaTelefone.getItems()) {
            if (!tel.getId().equals(telefoneSel.getId()) && tel.selecionadoBoolean()) {
                tel.setSelecionado(0);
                ts.editar(tel);
                nenhum = false;
            }
        }
        if (nenhum) {
            telefoneSel.setSelecionado(new Integer(0));
            ckbTelefoneSelecionado.setSelected(nenhum);
        }
    }

    @FXML
    private void onActionMenuSalvar() {
        if (tabEndereco.isSelected()) {
            try {
                EnderecoService es = new EnderecoService();
                if (enderecoSel.getId() == 0) {
                    if (ckbEnderecoSelecionado.isSelected()) {
                        editarEndereco(es);
                    }
                    es.salvar(enderecoSel);
                } else {
                    if (ckbEnderecoSelecionado.isSelected()) {
                        if (!enderecoSel.selecionadoBoolean()) {
                            editarEndereco(es);
                        }
                    }
                    es.editar(enderecoSel);
                }

                StaticLista.setListaGlobalEndereco(es.findAll());
                JPA.em(false).close();
                enderecoAtual = preencherTabelaEndereco();
                setBindComponentsWithEndereco();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tabContato.isSelected()) {
            try {
                ContatoService cs = new ContatoService();
                if (contatoSel.getId() == 0) {
                    if (ckbContatoSelecionado.isSelected()) {
                        editarContato(cs);
                    }
                    cs.salvar(contatoSel);
                } else {
                    if (ckbContatoSelecionado.isSelected()) {
                        if (!contatoSel.selecionadoBoolean()) {
                            editarContato(cs);
                        }
                    }
                    cs.editar(contatoSel);
                }
                StaticLista.setListaGlobalContato(cs.findAll());
                JPA.em(false).close();
                contatoAtual = preencherTabelaContato();
                setBindComponentsWithContato();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (tabTelefone.isSelected()) {
            try {
                TelefoneService ts = new TelefoneService();
                if (telefoneSel.getId() == 0) {
                    if (ckbTelefoneSelecionado.isSelected()) {
                        editarTelefone(ts);
                    }
                    ts.salvar(telefoneSel);
                } else {
                    if (ckbTelefoneSelecionado.isSelected()) {
                        if (!telefoneSel.selecionadoBoolean()) {
                            editarTelefone(ts);
                        }
                    }
                    ts.editar(telefoneSel);
                }
                StaticLista.setListaGlobalTelefone(ts.findAll());
                JPA.em(false).close();
                telefoneAtual = preencherTabelaTelefone();
                setBindComponentsWithTelefone();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        btnMenuLixo.setDisable(false);
    }

    @FXML
    private void onActionMenuNovo() {
        if (empresaEncontrada != null) {
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
                enderecoSel.setSelecionado(0);
                enderecoSel.setId(new Long(0));
            } else if (tabContato.isSelected()) {
                txtContato.setText("");
                txtCpf.setText("");
                txtRg.setText("");
                txtEmail.setText("");
                txtResponsavel.setText("");
                txtContato.requestFocus();
                ckbContatoSelecionado.setSelected(false);
                contatoSel.setSelecionado(0);
                contatoSel.setId(new Long(0));
            } else if (tabTelefone.isSelected()) {
                txtTelefone.setText("");
                txtFax.setText("");
                txtCelular.setText("");
                ckbTelefoneSelecionado.setSelected(false);
                telefoneSel.setSelecionado(0);
                telefoneSel.setId(new Long(0));
            }
        }
    }

    private void defaultValuesOnTabs() {
        if (enderecoAtual != null) {
            preencherTabelaContato();
            preencherTabelaEndereco();
            preencherTabelaTelefone();
        }
    }

    private void defaultValue() {
        if (enderecoAtual != null) {
            preencherEndereco(enderecoAtual);
            preencherContato(contatoAtual);
            preencherTelefone(telefoneAtual);
        }
    }

    private void confMenu() {
        if (tabEmpresa.isSelected()) {
            desativarMenu();
        } else {
            if (empresaEncontrada.getId() != null && empresaEncontrada.getId() != 0 && !empresaEncontrada.getId().equals(new Long(0))) {
                ativarMenu();
            } else {
                desativarMenu();
            }
        }
    }

    @FXML
    private void onSelectionChangedTabContato() {
        confMenu();
        defaultValue();
    }

    @FXML
    private void onSelectionChangedTabTelefone() {
        confMenu();
        defaultValue();
    }

    @FXML
    private void onSelectionChangedTabEndereco() {
        confMenu();
        defaultValue();
    }

    @FXML
    private void onSelectionChangedTabEmpresa() {
        confMenu();
    }

    private void avisoSelecaoTabela() {
        UtilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), "Selecione uma linha da tabela!!");
    }

    @FXML
    private void onActionBtnBuscar() {
        Map<String, String> query = new HashMap<String, String>();
        query.put("cepEntrada", UtilTexto.removeAllSimbolsExceptNumber(txtCep.getText()));
        query.put("tipoCep", "");
        query.put("cepTemp", "");
        query.put("metodo", "buscarCep");

        try {
            Document doc = Jsoup.connect("http://m.correios.com.br/movel/buscaCepConfirma.do")
                    .data(query)
                    .post();
            Elements elemetos = doc.select(".respostadestaque");
            if (elemetos.size() == 4) {
                txtEndereco.setText(elemetos.get(0).text());
                txtBairro.setText(elemetos.get(1).text());
                setWithCepCidadeUF(elemetos.get(2).text());
            } else if (elemetos.size() == 2) {
                txtEndereco.setText("");
                txtBairro.setText("");
                setWithCepCidadeUF(elemetos.get(0).text());
            } else {
                txtEndereco.setText("");
                txtBairro.setText("");
                cbEstado.getSelectionModel().clearSelection();
                cbCidade.getSelectionModel().clearSelection();
//                System.out.println("Dados não encontrado");
            }
        } catch (IOException ex) {
            Logger.getLogger(EmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setWithCepCidadeUF(String cUf) {
        String cidadeUf = UtilTexto.removerAcentos(cUf);
        cbEstado.setItems(StaticLista.getListaGlobalEstado());
        cbCidade.setItems(StaticLista.getListaGlobalCidade());
        for (Cidade c : StaticLista.getListaGlobalCidade()) {
            if (c.getNome().toLowerCase().equals(cidadeUf.substring(0, cidadeUf.indexOf("/")).toLowerCase().trim())
                    && c.getUf().toLowerCase().equals(cidadeUf.substring(cidadeUf.indexOf("/") + 1).toLowerCase().trim())) {
                cbEstado.setValue(c.getIdEstado());
                cbCidade.setValue(c);
                break;
            }
        }
    }

    private void desativarMenu() {
        btnMenuLixo.setDisable(true);
        btnMenuNovo.setDisable(true);
        btnMenuSalvar.setDisable(true);
        if (ckbEnderecoSelecionado != null) {
            ckbEnderecoSelecionado.setDisable(true);
            ckbTelefoneSelecionado.setDisable(true);
            ckbContatoSelecionado.setDisable(true);
        }
    }

    private void ativarMenu() {
        btnMenuLixo.setDisable(false);
        btnMenuNovo.setDisable(false);
        btnMenuSalvar.setDisable(false);
        ckbEnderecoSelecionado.setDisable(false);
        ckbTelefoneSelecionado.setDisable(false);
        ckbContatoSelecionado.setDisable(false);
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
        validationSupport.redecorate();
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
        validationSupport.redecorate();

        tabelaContato.setItems(null);
        tabelaEndereco.setItems(null);
        tabelaTelefone.setItems(null);

        txtEmpresa.setText("");
        txtaObs.setText("");
        txtCnpj.setText("");
        txtFax.setText("");
        txtIE.setText("");
        txtIM.setText("");
        txtNomeFantasia.setText("");

        enderecoSel = null;
        enderecoAtual = null;
        contatoSel = null;
        telefoneSel = null;
        contatoAtual = null;
        telefoneAtual = null;
        ckbEnderecoSelecionado.setSelected(true);
        ckbContatoSelecionado.setSelected(true);
        ckbTelefoneSelecionado.setSelected(true);
        desativarMenu();

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
        empresaEncontrada.setId(new Long(0));
        isUpdate = false;
        sceneManager.setEmpresaEncontrada(null);
        defaultBind();
    }

    private Endereco preencherTabelaEndereco() {
        EnderecoService es = new EnderecoService();
        Endereco endereco = null;
        tabelaEndereco.getItems().clear();
        tabelaEndereco.setItems(es.findByIdEmpresa(empresaEncontrada));
        for (Endereco e : tabelaEndereco.getItems()) {
            if (e.selecionadoBoolean()) {
                endereco = e;
                break;
            }
        }
        JPA.em(false).close();
        return endereco.clone();
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
        return contato.clone();
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
        return tel.clone();
    }

    private void preencherEndereco(Endereco e) {
        txtComplemento.setText(e.getComplemento());
        txtNumero.setText(e.getNumero());
        txtBairro.setText(e.getBairro());
        txtEndereco.setText(e.getLogradouro());
        txtCep.setText(e.getCep());
        ckbEnderecoSelecionado.setSelected(e.selecionadoBoolean());
        addEstado();
        cbEstado.getSelectionModel().select(e.getIdCidade().getIdEstado());
        addCidade(cbEstado.getValue().toString());
        cbCidade.getSelectionModel().select(e.getIdCidade());
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

        enderecoSel = preencherTabelaEndereco();
        enderecoAtual = enderecoSel.clone();
        tabelaEndereco.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        enderecoSel.setId(newValue.getId());
                        if (newValue.selecionadoBoolean()) {
                            enderecoSel.setSelecionado(new Integer(1));
                        } else {
                            enderecoSel.setSelecionado(new Integer(0));
                        }
                        txtEndereco.setText(newValue.getLogradouro());
                        txtNumero.setText(newValue.getNumero());
                        txtBairro.setText(newValue.getBairro());
                        txtComplemento.setText(newValue.getComplemento());
                        txtCep.setText(newValue.getCep());
                        cbEstado.setValue(newValue.getIdCidade().getIdEstado());
                        cbCidade.setValue(newValue.getIdCidade());
                        ckbEnderecoSelecionado.setSelected(newValue.selecionadoBoolean());
                    }
                }
        );
        telefoneSel = preencherTabelaTelefone();
        telefoneAtual = telefoneSel.clone();
        tabelaTelefone.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        telefoneSel.setId(newValue.getId());
                        if (newValue.selecionadoBoolean()) {
                            telefoneSel.setSelecionado(new Integer(1));
                        } else {
                            telefoneSel.setSelecionado(new Integer(0));
                        }
                        txtCelular.setText(newValue.getCelular());
                        txtFax.setText(newValue.getFax());
                        txtTelefone.setText(newValue.getFixo());
                        ckbTelefoneSelecionado.setSelected(newValue.selecionadoBoolean());
                    }
                }
        );
        contatoSel = preencherTabelaContato();
        contatoAtual = contatoSel.clone();
        tabelaContato.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        contatoSel.setId(newValue.getId());
                        if (newValue.selecionadoBoolean()) {
                            contatoSel.setSelecionado(new Integer(1));
                        } else {
                            contatoSel.setSelecionado(new Integer(0));
                        }
                        txtContato.setText(newValue.getNome());
                        txtCpf.setText(newValue.getCpf());
                        txtRg.setText(newValue.getRg());
                        txtEmail.setText(newValue.getEmail());
                        txtResponsavel.setText(newValue.getResponsavelTeste());
                        ckbContatoSelecionado.setSelected(newValue.selecionadoBoolean());
                    }
                }
        );

        defaultBind();

//        txtEmpresa.setText(empresaEncontrada.getDescricao());
//        txtaObs.setText(empresaEncontrada.getObservacao());
//        txtNomeFantasia.setText(empresaEncontrada.getNomeFantasia());
//        txtCnpj.setText(empresaEncontrada.getCnpj());
//        txtIE.setText(empresaEncontrada.getInscricaoEstadual());
//        txtIM.setText(empresaEncontrada.getInscricaoMunicipal());
        cbCategoria.getSelectionModel().selectFirst();
        for (int i = 0; i < cbCategoria.getItems().size(); i++) {
            if (((Categoria) cbCategoria.getItems().get(i)).getNome().equals(empresaEncontrada.getCategoria())) {
                cbCategoria.getSelectionModel().select(i);
            }
        }
        confMenu();
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

    private void setBindComponentsWithEmpresa() {
        if (empresaEncontrada == null) {
            empresaEncontrada = new Empresa();
        }
        txtEmpresa.textProperty().bindBidirectional(empresaEncontrada.descricaoProperty());
        txtNomeFantasia.textProperty().bindBidirectional(empresaEncontrada.nomeFantasiaProperty());
        txtCnpj.textProperty().bindBidirectional(empresaEncontrada.cnpjProperty());
        txtIE.textProperty().bindBidirectional(empresaEncontrada.inscricaoEstadualProperty());
        txtIM.textProperty().bindBidirectional(empresaEncontrada.inscricaoMunicipalProperty());
        txtaObs.textProperty().bindBidirectional(empresaEncontrada.observacaoProperty());
    }

    private void setBindComponentsWithEndereco() {
        enderecoSel.logradouroProperty().bind(txtEndereco.textProperty());
        enderecoSel.numeroProperty().bind(txtNumero.textProperty());
        enderecoSel.bairroProperty().bind(txtBairro.textProperty());
        enderecoSel.cepProperty().bind(txtCep.textProperty());
        enderecoSel.complementoProperty().bind(txtComplemento.textProperty());
        enderecoSel.idCidadeProperty().bind(cbCidade.valueProperty());
    }

    private void setBindComponentsWithTelefone() {
        telefoneSel.celularProperty().bind(txtCelular.textProperty());
        telefoneSel.fixoProperty().bind(txtTelefone.textProperty());
        telefoneSel.faxProperty().bind(txtFax.textProperty());
    }

    private void setBindComponentsWithContato() {
        contatoSel.nomeProperty().bind(txtContato.textProperty());
        contatoSel.cpfProperty().bind(txtCpf.textProperty());
        contatoSel.rgProperty().bind(txtRg.textProperty());
        contatoSel.emailProperty().bind(txtEmail.textProperty());
        contatoSel.responsavelTesteProperty().bind(txtResponsavel.textProperty());
    }
}
