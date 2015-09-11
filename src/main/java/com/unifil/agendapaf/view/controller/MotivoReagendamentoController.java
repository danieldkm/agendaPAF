package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.statics.StaticString;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MotivoReagendamentoController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (dialog == null) {
            dialog = new UtilDialog();
        }
        if (StaticBoolean.isCancelamento()) {
            stage.setTitle("Motivo do Cancelamento");
        } else {
            stage.setTitle("Motivo do Reagendamento");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            mainMotivo = FXMLLoader.load(EmpresaController.class.getResource(EnumCaminho.MotivoReagendamento.getCaminho()));
            Scene scene = new Scene(mainMotivo);
            stage.setScene(scene);
            stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            dialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start agenda", e, "Exception:");
        }
    }

    @FXML
    private static BorderPane mainMotivo;
//    @FXML
//    private Button btnContinuar;
    @FXML
    private TextArea txtaTexto;

//    private Agenda agenda;
    private static Stage stage;
//    private static Stage stageTabela;
    private UtilDialog dialog;

    @FXML
    private void actionBtnContinuar() {
        if (!txtaTexto.getText().equals("")) {
            StaticString.setTxtMotivoReagendamento(txtaTexto.getText());
            if (StaticBoolean.isCancelamento()) {
                dialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.MotivoReagendamentoParaCancelar.getMensagem());
            }
            RunAnotherApp.runAnotherApp(AgendarController.class);
            stage.close();
        } else {
            dialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.MotivoReagendamentoPreencherMotivo.getMensagem());
        }
    }

    @FXML
    private void actionBtnCancelar() {
        StaticBoolean.setCancelamento(false);
        StaticBoolean.setReagendamento(false);
        StaticObject.setAgendaEncontrada(null);
        stage.close();
    }

}
