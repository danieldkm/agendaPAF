package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.statics.StaticString;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VisualizadorMotivoController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilDialog = new UtilDialog();
            mainMotivo.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            txtMotivo.setText(StaticString.getTxtVisualizadorMotivo());
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar visualizar motivo", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainMotivo = FXMLLoader.load(EmpresaController.class.getResource(EnumCaminho.VisualizadorMotivo.getCaminho()));
            Scene scene = new Scene(mainMotivo);
//        stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
//        stage.setTitle("Motivo!!!");
            stage.setResizable(false);
//        stage.initOwner(this.myParent);
//        stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start visualizar motivo", e, "Exception:");
        }
    }

    @FXML
    private BorderPane mainMotivo;
    @FXML
    public TextArea txtMotivo;

    public static Stage stage;
    private UtilDialog utilDialog;
}
