package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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

public class TabelaFeriadoController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilDialog = new UtilDialog();
            mainTabelaFeriado.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            tvFeriado.setItems(StaticLista.getListaGlobalFeriado());

            tcData.setCellFactory(column -> {
                return new TableCell<Feriado, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            // Format date.
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd-MM-yyyy"));
                        }
                    }
                };
            });
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela feriado", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainTabelaFeriado = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.TabelaFeriados.getCaminho()));
            Scene scene = new Scene(mainTabelaFeriado);
            stage.setScene(scene);
            stage.setTitle("Tabela de Feriados");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnHidden(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela feriado", e, "Exception:");
        }
    }

    @FXML
    private TextField txtBuscar;
    @FXML
    private ComboBox cbFiltro;
    @FXML
    private BorderPane mainTabelaFeriado;
    @FXML
    private TableView<Feriado> tvFeriado;
    @FXML
    private TableColumn tcData;

    private static Stage stage;
    private ObservableList<Feriado> todos = FXCollections.observableArrayList();
    private UtilDialog utilDialog;

    @FXML
    private void onActionTxtBuscar(KeyEvent e) {
        todos.clear();
        String concate = "";
        for (Feriado feriado : StaticLista.getListaGlobalFeriado()) {
            concate = feriado.getId() + " " + feriado.getFeriado() + " " + feriado.getData();
            if (concate.toLowerCase().contains(txtBuscar.getText().toLowerCase() + e.getText().toLowerCase())) {
                todos.add(feriado);
            }
        }
        tvFeriado.setItems(todos);
    }

    @FXML
    private void onMouseClickedTabelaFeriado(MouseEvent event) {
        if (event.getClickCount() == 2) {
            StaticObject.setFeriadoEncontrada(tvFeriado.getSelectionModel().getSelectedItem());
            RunAnotherApp.runAnotherApp(FeriadoController.class);
            stage.close();
        }

    }

}
