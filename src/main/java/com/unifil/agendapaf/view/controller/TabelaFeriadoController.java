package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.UtilConverter;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TabelaFeriadoController {

    @FXML
    public void initialize() {
        try {
            sceneManager = SceneManager.getInstance();
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
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela feriado", e, "Exception:");
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
    private SceneManager sceneManager;
    private ObservableList<Feriado> todos = FXCollections.observableArrayList();

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
            sceneManager.setFeriadoEncontrado(tvFeriado.getSelectionModel().getSelectedItem());
            sceneManager.showFeriado();
            stage.close();
        }

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainTabelaFeriado(BorderPane mainTabelaFeriado) {
        this.mainTabelaFeriado = mainTabelaFeriado;
    }

}
