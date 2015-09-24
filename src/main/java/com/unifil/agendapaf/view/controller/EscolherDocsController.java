package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author danielmorita
 */
public class EscolherDocsController {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        utilXML = new UtilFile();
        utilXML.listarArquivos(new File(EnumCaminho.ModeloDocxs.getCaminho()));
//        utilXML.listarArquivos(new File(System.getProperty("user.dir") + "/docx"));
        for (String it : utilXML.getDocs()) {
            Item item = new Item(it, false);

            // observe item's on property and display message if it changes:
//            item.onProperty().addListener((obs, wasOn, isNowOn) -> {
//                System.out.println(item.getName() + " changed on state from " + wasOn + " to " + isNowOn);
//            });
            item.setOn(true);
            lvDocs.getItems().add(item);
        }

        lvDocs.setCellFactory(CheckBoxListCell.forListView(new Callback<Item, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Item item) {
                return item.onProperty();
            }
        }));
    }

    @FXML
    private VBox vbMain;

    @FXML
    private ListView<Item> lvDocs;

    @FXML
    private Button btnAceitar;

    @FXML
    private Button btnCancelar;

    @FXML
    private RadioButton rbMarcarTodos;

    private UtilFile utilXML;
    private Stage stage;

    @FXML
    private void onActionBtnAceitar() {
        ObservableList<String> files = FXCollections.observableArrayList();
        for (Item item : lvDocs.getItems()) {
            if (item.isOn()) {
                files.add(item.getName());
            }
        }
        SceneManager.getInstance().getLaudoController().setFiles(files);
        stage.close();
    }

    @FXML
    private void onActionBtnCancelar() {
        stage.close();
    }

    @FXML
    private void onActionRbMarcarTodos() {
        if (rbMarcarTodos.isSelected()) {
            ObservableList<Item> temp = FXCollections.observableArrayList();
            for (Item item : lvDocs.getItems()) {
                item.setOn(true);
                temp.add(item);
            }
            lvDocs.setItems(temp);
        } else {
            ObservableList<Item> temp = FXCollections.observableArrayList();
            for (Item item : lvDocs.getItems()) {
                item.setOn(false);
                temp.add(item);
            }
            lvDocs.setItems(temp);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setVbMain(VBox vbMain) {
        this.vbMain = vbMain;
    }

    public static class Item {

        private final StringProperty name = new SimpleStringProperty();
        private final BooleanProperty on = new SimpleBooleanProperty();

        public Item(String name, boolean on) {
            setName(name);
            setOn(on);
        }

        public final StringProperty nameProperty() {
            return this.name;
        }

        public final String getName() {
            return this.nameProperty().get();
        }

        public final void setName(final String name) {
            this.nameProperty().set(name);
        }

        public final BooleanProperty onProperty() {
            return this.on;
        }

        public final boolean isOn() {
            return this.onProperty().get();
        }

        public final void setOn(final boolean on) {
            this.onProperty().set(on);
        }

        @Override
        public String toString() {
            return getName();
        }

    }

}
