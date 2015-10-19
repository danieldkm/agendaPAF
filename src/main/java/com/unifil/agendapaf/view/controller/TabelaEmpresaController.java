package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Endereco;
import com.unifil.agendapaf.model.aux.TabelaEmpresa;
import com.unifil.agendapaf.model.Telefone;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TabelaEmpresaController {

    @FXML
    public void initialize() {
        try {
            sceneManager = SceneManager.getInstance();
            popularTabela();

            tcEmpresa.setCellFactory(new Callback<TableColumn<TabelaEmpresa, Empresa>, TableCell<TabelaEmpresa, Empresa>>() {
                @Override
                public TableCell<TabelaEmpresa, Empresa> call(TableColumn<TabelaEmpresa, Empresa> param) {
                    final TableCell<TabelaEmpresa, Empresa> cell = new TableCell<TabelaEmpresa, Empresa>() {
                        @Override
                        public void updateItem(final Empresa item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                this.setText("");
                            } else {
                                this.setText(item.getDescricao());
                            }
                        }
                    };
                    return cell;
                }
            });

            tcObs.setCellFactory(new Callback<TableColumn<TabelaEmpresa, Empresa>, TableCell<TabelaEmpresa, Empresa>>() {
                @Override
                public TableCell<TabelaEmpresa, Empresa> call(TableColumn<TabelaEmpresa, Empresa> param) {
                    final TableCell<TabelaEmpresa, Empresa> cell = new TableCell<TabelaEmpresa, Empresa>() {
                        @Override
                        public void updateItem(final Empresa item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                this.setText("");
                            } else {
                                this.setText(item.getObservacao());
                            }
                        }
                    };
                    return cell;
                }
            });
            tcCategoria.setCellFactory(new Callback<TableColumn<TabelaEmpresa, Empresa>, TableCell<TabelaEmpresa, Empresa>>() {
                @Override
                public TableCell<TabelaEmpresa, Empresa> call(TableColumn<TabelaEmpresa, Empresa> param) {
                    final TableCell<TabelaEmpresa, Empresa> cell = new TableCell<TabelaEmpresa, Empresa>() {
                        @Override
                        public void updateItem(final Empresa item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                this.setText("");
                            } else {
                                this.setText(item.getCategoria());
                            }
                        }
                    };
                    return cell;
                }
            });

            tcDataCadastro.setCellFactory(column -> {
                return new TableCell<TabelaEmpresa, Empresa>() {
                    @Override
                    protected void updateItem(Empresa item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item.getDataCadastro()), "dd/MM/yyyy"));
                        }
                    }
                };
            });

//            tcCidade.setCellFactory(new Callback<TableColumn<TabelaEmpresa, Endereco>, TableCell<TabelaEmpresa, Endereco>>() {
//                @Override
//                public TableCell<TabelaEmpresa, Endereco> call(TableColumn<TabelaEmpresa, Endereco> param) {
//                    final TableCell<TabelaEmpresa, Endereco> cell = new TableCell<TabelaEmpresa, Endereco>() {
//                        @Override
//                        public void updateItem(final Endereco item, boolean empty) {
//                            super.updateItem(item, empty);
//                            if (empty) {
//                                this.setText("");
//                            } else {
//                                this.setText(item.getIdCidade().getNome());
//                            }
//                        }
//                    };
//                    return cell;
//                }
//
//            });
//            tcEstado.setCellFactory(new Callback<TableColumn<TabelaEmpresa, Endereco>, TableCell<TabelaEmpresa, Endereco>>() {
//                @Override
//                public TableCell<TabelaEmpresa, Endereco> call(TableColumn<TabelaEmpresa, Endereco> param) {
//                    final TableCell<TabelaEmpresa, Endereco> cell = new TableCell<TabelaEmpresa, Endereco>() {
//                        @Override
//                        public void updateItem(final Endereco item, boolean empty) {
//                            super.updateItem(item, empty);
//                            if (empty) {
//                                this.setText("");
//                            } else {
//                                if (item.getSelecionado()) {
//                                    this.setText(item.getIdCidade().getIdEstado().getNome());
//                                }
//                            }
//                        }
//                    };
//                    return cell;
//                }
//
//            });
//            tcTelefone.setCellFactory(new Callback<TableColumn<TabelaEmpresa, Telefone>, TableCell<TabelaEmpresa, Telefone>>() {
//                @Override
//                public TableCell<TabelaEmpresa, Telefone> call(TableColumn<TabelaEmpresa, Telefone> param) {
//                    final TableCell<TabelaEmpresa, Telefone> cell = new TableCell<TabelaEmpresa, Telefone>() {
//                        @Override
//                        public void updateItem(final Telefone item, boolean empty) {
//                            super.updateItem(item, empty);
//                            if (empty) {
//                                this.setText("");
//                            } else {
//                                this.setText(item.getFixo());
//                            }
//                        }
//                    };
//                    return cell;
//                }
//
//            });
//            tcContato.setCellFactory(new Callback<TableColumn<TabelaEmpresa, Contato>, TableCell<TabelaEmpresa, Contato>>() {
//                @Override
//                public TableCell<TabelaEmpresa, Contato> call(TableColumn<TabelaEmpresa, Contato> param) {
//                    final TableCell<TabelaEmpresa, Contato> cell = new TableCell<TabelaEmpresa, Contato>() {
//                        @Override
//                        public void updateItem(final Contato item, boolean empty) {
//                            super.updateItem(item, empty);
//                            if (empty) {
//                                this.setText("");
//                            } else {
//                                this.setText(item.getNome());
//                            }
//                        }
//                    };
//                    return cell;
//                }
//
//            });
//
//            tcEmail.setCellFactory(new Callback<TableColumn<TabelaEmpresa, Contato>, TableCell<TabelaEmpresa, Contato>>() {
//                @Override
//                public TableCell<TabelaEmpresa, Contato> call(TableColumn<TabelaEmpresa, Contato> param) {
//                    final TableCell<TabelaEmpresa, Contato> cell = new TableCell<TabelaEmpresa, Contato>() {
//                        @Override
//                        public void updateItem(final Contato item, boolean empty) {
//                            super.updateItem(item, empty);
//                            if (empty) {
//                                this.setText("");
//                            } else {
//                                this.setText(item.getEmail());
//                            }
//                        }
//                    };
//                    return cell;
//                }
//
//            });
//        tcCodEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, Integer>("codEmpresa"));
//        tcEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("descricaoEmpresa"));
//        tcContato.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nomeContato"));
//        tcTelefone.setCellValueFactory(new PropertyValueFactory<Empresa, String>("telefone"));
//        tcObs.setCellValueFactory(new PropertyValueFactory<Empresa, String>("obs"));
//        tcDataCadastro.setCellValueFactory(new PropertyValueFactory<Empresa, Date>("dataCadastro"));
//        tcEmail.setCellValueFactory(new PropertyValueFactory<Empresa, String>("email"));
//        tcCidade.setCellValueFactory(new PropertyValueFactory<Empresa, String>("cidade"));
            txtBuscar.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela empresa", e, "Exception:");
        }
    }

    @FXML
    private TableView<TabelaEmpresa> tvEmpresa;
    @FXML
    private TableColumn tcEmpresa;
    @FXML
    private TableColumn tcEstado;
    @FXML
    private TableColumn tcObs;
    @FXML
    private TableColumn tcCategoria;
    @FXML
    private TableColumn tcCidade;
    @FXML
    private TableColumn tcTelefone;
    @FXML
    private TableColumn tcDataCadastro;
    @FXML
    private TableColumn tcContato;
    @FXML
    private TableColumn tcEmail;
    @FXML
    private BorderPane mainTbEmpresa;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnCadastrar;

    private Stage stage;
    private boolean isFinanceiro = false;
    private boolean isLaudo = false;
    private boolean isEmpresa = false;
    private boolean isAgenda = false;
    private boolean isTabelaHistorico = false;
    private boolean isTabelaAgenda = false;
    private boolean isRelatorio = false;
    private boolean isConsulta = false;
    private ObservableList<TabelaEmpresa> listaTbECF = FXCollections.observableArrayList();
    private String contatTextBusca = "";
    private SceneManager sceneManager;

    @FXML
    private void onKeyPressdTxtBuscar(KeyEvent e) {
        listaTbECF.clear();
        ObservableList<TabelaEmpresa> tes = FXCollections.observableArrayList();
        TabelaEmpresa te = null;
        for (Empresa empresa : StaticLista.getListaGlobalEmpresa()) {
            te = new TabelaEmpresa();
            te.setEmpresa(empresa);
            for (Contato contato : StaticLista.getListaGlobalContato()) {
                if (contato.getIdEmpresa().getId().equals(empresa.getId()) && contato.selecionadoBoolean()) {
                    te.setNomeContato(contato.getNome());
                    te.setEmail(contato.getEmail());
                    break;
                }
            }

            for (Endereco endereco : StaticLista.getListaGlobalEndereco()) {
                if (endereco.getIdEmpresa().getId().equals(empresa.getId()) && endereco.selecionadoBoolean()) {
                    te.setCidade(endereco.getIdCidade().getNome());
                    te.setEstado(endereco.getIdCidade().getUf());
                    break;
                }
            }

            for (Telefone telefone : StaticLista.getListaGlobalTelefone()) {
                if (telefone.getIdEmpresa().getId().equals(empresa.getId()) && telefone.selecionadoBoolean()) {
                    te.setTelefone(telefone.getFixo());
                    break;
                }
            }
            tes.add(te);
        }
        for (TabelaEmpresa te1 : tes) {

            if (te1.toString().toLowerCase().contains(txtBuscar.getText().toLowerCase() + e.getText().toLowerCase())) {
                listaTbECF.add(te1);
            }
        }
        tvEmpresa.setItems(listaTbECF);
    }

    private void popularTabela() {
        ObservableList<TabelaEmpresa> tes = FXCollections.observableArrayList();
        TabelaEmpresa te = null;
        for (Empresa empresa : StaticLista.getListaGlobalEmpresa()) {
            te = new TabelaEmpresa();
            te.setEmpresa(empresa);
            for (Contato contato : StaticLista.getListaGlobalContato()) {
                if (contato.getIdEmpresa().getId().equals(empresa.getId()) && contato.selecionadoBoolean()) {
                    te.setNomeContato(contato.getNome());
                    te.setEmail(contato.getEmail());
                    break;
                }
            }

            for (Endereco endereco : StaticLista.getListaGlobalEndereco()) {
                if (endereco.getIdEmpresa().getId().equals(empresa.getId()) && endereco.selecionadoBoolean()) {
                    te.setCidade(endereco.getIdCidade().getNome());
                    te.setEstado(endereco.getIdCidade().getUf());
                    break;
                }
            }

            for (Telefone telefone : StaticLista.getListaGlobalTelefone()) {
                if (telefone.getIdEmpresa().getId().equals(empresa.getId()) && telefone.selecionadoBoolean()) {
                    te.setTelefone(telefone.getFixo());
                    break;
                }
            }
            tes.add(te);
        }
        System.out.println("TABELA EMPRESA VALUES " + te);
        tvEmpresa.setItems(tes);
    }

    @FXML
    private void onMouseClickedTabelaEmpresa(MouseEvent event) {
        if (event.isSecondaryButtonDown()) {
            if (isConsulta) {
                if (sceneManager.getVisulizadorMotivoController() != null) {
                    sceneManager.getVisulizadorMotivoController().getStage().close();
                }
            }
            if (tvEmpresa.getSelectionModel().getSelectedItem() != null) {
                String txt = tvEmpresa.getSelectionModel().getSelectedItem().getEmpresa().getObservacao();
                txt += "";
                if (!txt.equals("")) {
                    sceneManager.showVisualizadorMotivo(tvEmpresa.getSelectionModel().getSelectedItem().getEmpresa().getObservacao());
                    sceneManager.getVisulizadorMotivoController().getStage().setTitle("Observação!!!");
                } else {
                    sceneManager.getVisulizadorMotivoController().getStage().close();
                }
            }
        } else {
            if (sceneManager.getVisulizadorMotivoController() != null) {
                sceneManager.getVisulizadorMotivoController().getStage().close();
            }
            if (event.getClickCount() == 2) {
                if (isFinanceiro) {
                    Empresa emp = null;
                    for (Empresa e : Controller.getEmpresas()) {
                        if (e.getId().equals(tvEmpresa.getSelectionModel().getSelectedItem().getEmpresa().getId())) {
                            emp = e;
                            System.out.println("Selecionando empresa");
                            break;
                        }
                    }
                    sceneManager.showFinanceiro(true, emp, null);
                    stage.close();
                } else {
                    if (!isConsulta) {
                        sceneManager.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem().getEmpresa());
                        if (isEmpresa) {
                            sceneManager.showEmpresa(false);
                        } else if (isAgenda) {
                            sceneManager.showAgenda(null);
                        } else if (isTabelaAgenda) {
                            sceneManager.showTabelaAgenda();
                        } else if (isTabelaHistorico) {
                            sceneManager.showTabelaHistorico();
                        } else if (isRelatorio) {
                            sceneManager.getRelatorioController().setCampos(stage);
                        } else if (isLaudo) {
                            sceneManager.getLaudoController().preencherComEmpresaIdentificada(tvEmpresa.getSelectionModel().getSelectedItem().getEmpresa());
                            sceneManager.setEmpresaEncontrada(null);
                        }
                        stage.close();
                    } else {
                        if (sceneManager.getVisulizadorMotivoController() != null) {
                            sceneManager.getVisulizadorMotivoController().getStage().close();
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void actionBtnCadastrar() {
        stage.close();
        sceneManager.showEmpresa(true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainTbEmpresa(BorderPane mainTbEmpresa) {
        this.mainTbEmpresa = mainTbEmpresa;
    }

    public void setBooleans(boolean isEmpresa, boolean isAgenda, boolean isTabelaAgenda, boolean isRelatorio, boolean isConsulta, boolean isFinanceiro, boolean isLaudo) {
        this.isEmpresa = isEmpresa;
        this.isAgenda = isAgenda;
        this.isTabelaAgenda = isTabelaAgenda;
        this.isRelatorio = isRelatorio;
        this.isConsulta = isConsulta;
        this.isFinanceiro = isFinanceiro;
        this.isLaudo = isLaudo;
    }

}
