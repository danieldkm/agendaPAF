package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Historico;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticString;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.util.Util;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class TabelaHistoricoController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tcEmpresa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Historico, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Historico, String> data) {
                    return new SimpleObjectProperty<>(data.getValue().getIdEmpresa().getDescricao());
                }
            });
            tcUsuario.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Historico, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Historico, String> data) {
                    return new SimpleObjectProperty<>(data.getValue().getIdUsuario().getNome());
                }
            });
            tcTipo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Historico, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Historico, String> data) {
                    return new SimpleObjectProperty<>(Util.porAcentuacaoServico(data.getValue().getIdAgenda().getTipo()));
                }
            });

            tcStatusAgenda.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Historico, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Historico, String> data) {
                    return new SimpleObjectProperty<>(Util.porAcentuacaoServico(data.getValue().getStatus()));
                }
            });

            tcDtInicioAnt.setCellFactory(column -> {
                return new TableCell<Historico, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd-MM-yyyy"));
                        }
                    }
                };
            });

            tcDtInicioAtual.setCellFactory(column -> {
                return new TableCell<Historico, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd-MM-yyyy"));
                            // Style all dates in March with a different color.
//                            if (item.getDayOfMonth() < LocalDate.now().getDayOfMonth()) {
                            setTextFill(Color.CHOCOLATE);
                            setStyle("-fx-background-color: yellow");
//                            } else {
//                                setTextFill(Color.BLACK);
//                                setStyle("");
//                            }
                        }
                    }
                };
            });

            tcDtAlteracao.setCellFactory(column -> {
                return new TableCell<Historico, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd-MM-yyyy"));
                        }
                    }
                };
            });

            mainTbHistorico.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            txtBuscar.requestFocus();
//        tcCodEmpresa.setCellValueFactory(new PropertyValueFactory<Historico, Integer>("codEmpresa"));
//        tcEmpresa.setCellValueFactory(new PropertyValueFactory<Historico, String>("nomeEmpresa"));
//        tcDtInicioAnt.setCellValueFactory(new PropertyValueFactory<Historico, Date>("dtInicioAnt"));
//        tcDtInicioAtual.setCellValueFactory(new PropertyValueFactory<Historico, Date>("dtInicioReagendada"));
//        tcMotivo.setCellValueFactory(new PropertyValueFactory<Historico, String>("motivo"));
//        tcStatusAgenda.setCellValueFactory(new PropertyValueFactory<Historico, String>("statusAgenda"));
//        tcTipo.setCellValueFactory(new PropertyValueFactory<Historico, String>("tipo"));
//        tcAlteracao.setCellValueFactory(new PropertyValueFactory<Historico, String>("alteracao"));
//        tcDtAlteracao.setCellValueFactory(new PropertyValueFactory<Historico, Date>("dtAlteracao"));
//        tcUsuario.setCellValueFactory(new PropertyValueFactory<Historico, String>("usuario"));

            tvHistorico.getItems().setAll(StaticLista.getListaGlobalHistorico());
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela historico", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainTbHistorico = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.TabelaHistorico.getCaminho()));
            Scene scene = new Scene(mainTbHistorico);
            stage.setScene(scene);
            stage.setTitle("Tabela Histórico");
//        stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    StaticBoolean.setTabelaHistorico(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela historico", e, "Exception:");
        }
    }

    @FXML
    public BorderPane mainTbHistorico;
    @FXML
    private TableView<Historico> tvHistorico;
    @FXML
    private TableColumn tcDtInicioAnt;
    @FXML
    private TableColumn tcDtInicioAtual;
    @FXML
    private TableColumn tcDtAlteracao;
//    @FXML
//    private TableColumn<Historico, Integer> tcCodEmpresa;
    @FXML
    private TableColumn tcEmpresa;
//    @FXML
//    private TableColumn<Historico, String> tcMotivo;
    @FXML
    private TableColumn<Historico, String> tcStatusAgenda;
    @FXML
    private TableColumn tcTipo;
//    @FXML
//    private TableColumn<Historico, String> tcAlteracao;
    @FXML
    private TableColumn<Historico, String> tcUsuario;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;

    public ObservableList<Historico> listaHistorico = FXCollections.observableArrayList();
    public Empresa empresaEncontrada;
    private static Stage stage;
    private UtilDialog utilDialog;

    @FXML
    private void onKeyPressedTxtBuscar(KeyEvent e) {
        listaHistorico.clear();
        for (Historico h : StaticLista.getListaGlobalHistorico()) {
            if (h.toString().toLowerCase().contains(txtBuscar.getText().toLowerCase() + e.getText().toLowerCase())) {
                listaHistorico.add(h);
            }
        }
        tvHistorico.getItems().setAll(listaHistorico);
    }

    @FXML
    private void onMousePressedTabelaHistorico(MouseEvent me) {
        if (me.isSecondaryButtonDown()) {
            if (VisualizadorMotivoController.stage != null) {
                VisualizadorMotivoController.stage.close();
            }
            if (tvHistorico.getSelectionModel().getSelectedItem() != null) {
                String txt = tvHistorico.getSelectionModel().getSelectedItem().getMotivo();
                if (!txt.equals("")) {
                    StaticString.setTxtVisualizadorMotivo(tvHistorico.getSelectionModel().getSelectedItem().getMotivo());
                    RunAnotherApp.runAnotherApp(VisualizadorMotivoController.class);
                    VisualizadorMotivoController.stage.setTitle("Motivo!!!");
                }
            } else {
                if (VisualizadorMotivoController.stage != null) {
                    VisualizadorMotivoController.stage.close();
                }
            }
        } else {
            if (VisualizadorMotivoController.stage != null) {
                VisualizadorMotivoController.stage.close();
            }
        }
    }

    public void setCampos(Stage stage) {
        txtBuscar.setText(empresaEncontrada.getDescricao());
        txtBuscar.requestFocus();
        stage.close();
    }

}
