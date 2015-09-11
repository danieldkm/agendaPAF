package com.unifil.agendapaf.util;

import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Pair;
import org.controlsfx.dialog.LoginDialog;

/**
 *
 * @author danielmorita
 */
public class UtilDialog {

    public void criarDialogException(String titulo, String subtitulo, String informacao, Object ex, String tituloException) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(subtitulo);
        alert.setContentText(informacao);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
//        ex.printStackTrace(pw);
        String exception = "";
        if (ex instanceof Exception) {
            ((Exception) ex).printStackTrace(pw);
            exception = sw.toString();
        } else {
            exception = (String) ex;
        }

        Label label = new Label(tituloException);

        TextArea txtArea = new TextArea(exception);
        txtArea.setEditable(false);
        txtArea.setWrapText(true);

        txtArea.setMaxWidth(Double.MAX_VALUE);
        txtArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(txtArea, Priority.NEVER);
        GridPane.setHgrow(txtArea, Priority.NEVER);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(txtArea, 0, 1);
        dialogPane.setExpandableContent(expContent);

        alert.showAndWait();
    }

    public void criarDialogInfomation(String titulo, String subtitulo, String informacao) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(subtitulo);
        alert.setContentText(informacao);
//        alert.getDialogPane().setStyle("/styles/material-fx-v0_3.css");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());

        alert.showAndWait();
//        dialogPane.getStyleClass().add("myDialog");
    }

    public Optional<ButtonType> criarDialogConfirmacao(String titulo, String subtitulo, String informacao) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(subtitulo);
        alert.setContentText(informacao);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());

        return alert.showAndWait();

        /**
         * exemplo Optional<ButtonType> result = alert.showAndWait(); if
         * (result.get() == ButtonType.OK){
         */
    }

    public void criarDialogWarning(String titulo, String subtitulo, String informacao) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(subtitulo);
        alert.setContentText(informacao);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());

        alert.showAndWait();
    }

    public String criarDialogInput(String titulo, String subTitulo, String confirmacao) {
        TextInputDialog dlg = new TextInputDialog("");
        dlg.setTitle(titulo);

        DialogPane dialogPane = dlg.getDialogPane();
        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());

        dialogPane.setHeaderText(subTitulo);
        dialogPane.setContentText(confirmacao);

        dlg.showAndWait();
        return dlg.getResult();
    }

    public Pair<String, String> criarLoginDialog() {
//        Pair<String, String> p = new Pair<>("Usu�rio","Senha");
        LoginDialog dlg = new LoginDialog(null, null);
        dlg.setTitle("Login");

        DialogPane dialogPane = dlg.getDialogPane();
        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());
        dialogPane.setHeaderText("Logue com uma conta Gerencial");
        dialogPane.setContentText("Login");

        dlg.showAndWait();
        return dlg.getResult();
    }
}
