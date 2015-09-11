package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.Cidade;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import static com.unifil.agendapaf.statics.StaticString.setTxtVisualizadorMotivo;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class TabelaEmpresaController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

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
//                                System.out.println("item.getNome() " + item.getNome());
                                this.setText(item.getNome());
                            }
                        }
                    };
//                    System.out.println(cell.getIndex());
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
//                                System.out.println("item.getNome() " + item.getIdEstado().getNome());
                                this.setText(item.getIdEstado().getNome());
                            }
                        }
                    };
//                    System.out.println(cell.getIndex());
                    return cell;
                }

            });

            mainTbEmpresa.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            if (StaticBoolean.isTabelaEmpresaToFinanceiro()) {
                isFinanceiro = true;
                StaticBoolean.setTabelaEmpresaToFinanceiro(false);
            }

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
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela empresa", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainTbEmpresa = FXMLLoader.load(EmpresaController.class.getResource(EnumCaminho.TabelaEmpresa.getCaminho()));
            Scene scene = new Scene(mainTbEmpresa);
            stage.setScene(scene);
            stage.setTitle("Buscar Empresa");
//        stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    StaticBoolean.setConsulta(false);
//                isConsulta = false;
                }
            });
//            stage.getIcons().add(Controller.icoPAF);
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela empresa", e, "Exception:");
        }
    }

    @FXML
    private TableView<Empresa> tvEmpresa;
//    @FXML
//    private TableColumn<Empresa, Integer> tcCodEmpresa;
//    @FXML
//    private TableColumn<Empresa, String> tcEmpresa;
//    @FXML
//    private TableColumn<Empresa, String> tcContato;
//    @FXML
//    private TableColumn<Empresa, String> tcTelefone;
//    @FXML
//    private TableColumn<Empresa, String> tcObs;
//    @FXML
//    private TableColumn<Empresa, Date> tcDataCadastro;
//    @FXML
//    private TableColumn<Empresa, String> tcEmail;
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

    private static Stage stage;
    public boolean isFinanceiro = false;
    private ObservableList<Empresa> listaTbECF = FXCollections.observableArrayList();
    private UtilDialog utilDialog = new UtilDialog();
    private String contatTextBusca = "";

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
            if (StaticBoolean.isConsulta()) {
                if (VisualizadorMotivoController.stage != null) {
                    VisualizadorMotivoController.stage.close();
                }
                if (tvEmpresa.getSelectionModel().getSelectedItem() != null) {
                    String txt = tvEmpresa.getSelectionModel().getSelectedItem().getObservacao();
                    if (!txt.equals("")) {
                        setTxtVisualizadorMotivo(tvEmpresa.getSelectionModel().getSelectedItem().getObservacao());
                        RunAnotherApp.runAnotherApp(VisualizadorMotivoController.class
                        );
                        VisualizadorMotivoController.stage.setTitle(
                                "Observação!!!");
                    }
                } else {
                    VisualizadorMotivoController.stage.close();
                }
            }
        } else {
            if (VisualizadorMotivoController.stage != null) {
                VisualizadorMotivoController.stage.close();
            }
            if (event.getClickCount() == 2) {
                if (isFinanceiro) {
                    for (Empresa e : Controller.getEmpresas()) {
                        if (e.getId().equals(tvEmpresa.getSelectionModel().getSelectedItem().getId())) {
                            StaticObject.setEmpresaEncontrada(e);
                            break;
                        }
                    }
                    StaticBoolean.setTabelaEmpresaToFinanceiro(true);
//                    setDtInicial(tvEmpresa.getSelectionModel().getSelectedItem().getDataInicial2());
//                    setDtFinal(tvEmpresa.getSelectionModel().getSelectedItem().getDataFinal2());
                    RunAnotherApp.runAnotherApp(FinanceiroController.class
                    );
                    stage.close();
                } else {
                    if (!StaticBoolean.isConsulta()) {
                        if (StaticBoolean.isEmpresa()) {
                            StaticObject.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
                            RunAnotherApp.runAnotherApp(EmpresaController.class);
                            StaticBoolean.setEmpresa(false);
                        } else if (StaticBoolean.isAgenda()) {
                            StaticObject.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
//                        AgendarController.empresaEncontrada = tvEmpresa.getSelectionModel().getSelectedItem();
//                        AgendarController.setCampos(stage);
                            RunAnotherApp.runAnotherApp(AgendarController.class);
                            StaticBoolean.setAgenda(false);
//                        isAgenda = false;
                        } else if (StaticBoolean.isTabelaAgenda()) {
                            StaticObject.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
//                        TabelaAgendaController.empresaEncontrada = tvEmpresa.getSelectionModel().getSelectedItem();
                            RunAnotherApp.runAnotherApp(TabelaAgendaController.class);
//                        TabelaAgendaController.setCampos(stage);
                            StaticBoolean.setTabelaAgenda(false);
//                        isTabelaAgenda = false;
                        } else if (StaticBoolean.isTabelaHistorico()) {
                            StaticObject.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
                            RunAnotherApp.runAnotherApp(TabelaHistoricoController.class);
//                        TabelaHistoricoController.setCampos(stage);
//                        isTabelaHistorico = false;
                            StaticBoolean.setTabelaHistorico(false);
                        } else if (StaticBoolean.isRelatorio()) {
                            StaticObject.setEmpresaEncontrada(tvEmpresa.getSelectionModel().getSelectedItem());
//                        RelatorioController.empresaEncontrada = tvEmpresa.getSelectionModel().getSelectedItem();
                            RelatorioController.setCampos(stage);
                            StaticBoolean.setRelatorio(false);
//                        isRelatorio = false;
                        }
                        stage.close();
                    } else {
                        if (VisualizadorMotivoController.stage != null) {
                            VisualizadorMotivoController.stage.close();
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void actionBtnCadastrar() {
        StaticBoolean.setTabelaEmpresa(true);
        RunAnotherApp.runAnotherApp(EmpresaController.class);
        stage.close();
    }

}
