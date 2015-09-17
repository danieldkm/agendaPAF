package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Historico;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.Util;
import com.unifil.agendapaf.util.UtilConverter;
import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TabelaHistoricoController {

    @FXML
    public void initialize() {
        try {
            sceneManager = SceneManager.getInstance();
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

            txtBuscar.requestFocus();

            tvHistorico.getItems().setAll(StaticLista.getListaGlobalHistorico());
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela historico", e, "Exception:");
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
    @FXML
    private TableColumn tcEmpresa;
    @FXML
    private TableColumn<Historico, String> tcStatusAgenda;
    @FXML
    private TableColumn tcTipo;
    @FXML
    private TableColumn<Historico, String> tcUsuario;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;

    private ObservableList<Historico> listaHistorico = FXCollections.observableArrayList();
    private Empresa empresaEncontrada;
    private Stage stage;
    private SceneManager sceneManager;

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
            if (sceneManager.getVisulizadorMotivoController() != null) {
                sceneManager.getVisulizadorMotivoController().getStage().close();
            }
            if (tvHistorico.getSelectionModel().getSelectedItem() != null) {
                String txt = tvHistorico.getSelectionModel().getSelectedItem().getMotivo();
                if (!txt.equals("")) {
                    sceneManager.showVisualizadorMotivo(tvHistorico.getSelectionModel().getSelectedItem().getMotivo());
                    sceneManager.getVisulizadorMotivoController().getStage().setTitle("Motivo!!!");
                }
            } else {
                if (sceneManager.getVisulizadorMotivoController() != null) {
                    sceneManager.getVisulizadorMotivoController().getStage().close();
                }
            }
        } else {
            if (sceneManager.getVisulizadorMotivoController() != null) {
                sceneManager.getVisulizadorMotivoController().getStage().close();
            }
        }
    }

    public void setCampos(Stage stage) {
        txtBuscar.setText(empresaEncontrada.getDescricao());
        txtBuscar.requestFocus();
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainTbHistorico(BorderPane mainTbHistorico) {
        this.mainTbHistorico = mainTbHistorico;
    }

}
