package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.Cidade;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.statics.StaticLista;
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
            tcCidade.setCellFactory(new Callback<TableColumn<Empresa, Cidade>, TableCell<Empresa, Cidade>>() {
                @Override
                public TableCell<Empresa, Cidade> call(TableColumn<Empresa, Cidade> param) {
                    final TableCell<Empresa, Cidade> cell = new TableCell<Empresa, Cidade>() {
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
            tcEstado.setCellFactory(new Callback<TableColumn<Empresa, Cidade>, TableCell<Empresa, Cidade>>() {
                @Override
                public TableCell<Empresa, Cidade> call(TableColumn<Empresa, Cidade> param) {
                    final TableCell<Empresa, Cidade> cell = new TableCell<Empresa, Cidade>() {
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

//        tcCodEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, Integer>("codEmpresa"));
//        tcEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("descricaoEmpresa"));
//        tcContato.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nomeContato"));
//        tcTelefone.setCellValueFactory(new PropertyValueFactory<Empresa, String>("telefone"));
//        tcObs.setCellValueFactory(new PropertyValueFactory<Empresa, String>("obs"));
//        tcDataCadastro.setCellValueFactory(new PropertyValueFactory<Empresa, Date>("dataCadastro"));
//        tcEmail.setCellValueFactory(new PropertyValueFactory<Empresa, String>("email"));
//        tcCidade.setCellValueFactory(new PropertyValueFactory<Empresa, String>("cidade"));
            popularTabela();
            txtBuscar.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela empresa", e, "Exception:");
        }
    }

    @FXML
    private TableView<Empresa> tvEmpresa;
    @FXML
    private TableColumn tcEstado;
    @FXML
    private TableColumn tcCidade;
    @FXML
    private BorderPane mainTbEmpresa;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnCadastrar;

    private Stage stage;
    private boolean isFinanceiro = false;
    private boolean isEmpresa = false;
    private boolean isAgenda = false;
    private boolean isTabelaHistorico = false;
    private boolean isTabelaAgenda = false;
    private boolean isRelatorio = false;
    private boolean isConsulta = false;
    private ObservableList<Empresa> listaTbECF = FXCollections.observableArrayList();
    private String contatTextBusca = "";
    private SceneManager sceneManager;

    @FXML
    private void onKeyPressdTxtBuscar(KeyEvent e) {
        listaTbECF.clear();
        for (Empresa empresa : StaticLista.getListaGlobalEmpresa()) {
            if (empresa.toString2().toLowerCase().contains(txtBuscar.getText().toLowerCase() + e.getText().toLowerCase())) {
                listaTbECF.add(empresa);
            }
        }
        tvEmpresa.setItems(listaTbECF);
    }

    private void popularTabela() {
        tvEmpresa.setItems(StaticLista.getListaGlobalEmpresa());
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
                String txt = tvEmpresa.getSelectionModel().getSelectedItem().getObservacao();
                txt += "";
                if (!txt.equals("")) {
                    sceneManager.showVisualizadorMotivo(tvEmpresa.getSelectionModel().getSelectedItem().getObservacao());
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
                        if (e.getId().equals(tvEmpresa.getSelectionModel().getSelectedItem().getId())) {
                            emp = e;
                            System.out.println("Selecionando empresa");
                            break;
                        }
                    }
                    sceneManager.showFinanceiro(true, emp, null);
                    stage.close();
                } else {
                    if (!isConsulta) {
                        if (isEmpresa) {
                            sceneManager.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
                            sceneManager.showEmpresa(false);
                        } else if (isAgenda) {
                            sceneManager.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
                            sceneManager.showAgenda(null);
                        } else if (isTabelaAgenda) {
                            sceneManager.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
                            sceneManager.showTabelaAgenda();
                        } else if (isTabelaHistorico) {
                            sceneManager.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
                            sceneManager.showTabelaHistorico();
                        } else if (isRelatorio) {
                            sceneManager.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
                            sceneManager.getRelatorioController().setCampos(stage);
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

    public void setBooleans(boolean isEmpresa, boolean isAgenda, boolean isTabelaAgenda, boolean isRelatorio, boolean isConsulta, boolean isFinanceiro) {
        this.isEmpresa = isEmpresa;
        this.isAgenda = isAgenda;
        this.isTabelaAgenda = isTabelaAgenda;
        this.isRelatorio = isRelatorio;
        this.isConsulta = isConsulta;
        this.isFinanceiro = isFinanceiro;

    }

}
