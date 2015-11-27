package com.unifil.agendapaf.util.mensagem;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.util.MaskFieldUtil;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Pair;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.dialog.ExceptionDialog;
import org.controlsfx.dialog.LoginDialog;
import org.controlsfx.dialog.Wizard;
import org.controlsfx.dialog.WizardPane;

/**
 *
 * @author danielmorita
 */
public class Dialogos implements ImplMensagem {

    private final StageStyle stageStyle = StageStyle.DECORATED;
    private final Modality modality = Modality.APPLICATION_MODAL;

//    private final ComboBox<StageStyle> styleCombobox = new ComboBox<>();
//    private final ComboBox<Modality> modalityCombobox = new ComboBox<>();
//    private final CheckBox cbUseBlocking = new CheckBox();
//    private final CheckBox cbCloseDialogAutomatically = new CheckBox();
//    private final CheckBox cbShowMasthead = new CheckBox();
//    private final CheckBox cbSetOwner = new CheckBox();
//    private final CheckBox cbCustomGraphic = new CheckBox();
    private Stage stage;

    public Dialogos(Stage stage) {
        this.stage = stage;
    }

    /**
     * Configuração padrão do dialogo.
     *
     */
    private void configurarDialogo(Dialog<?> dlg, String header) {
        Window owner = stage;
        dlg.getDialogPane().setHeaderText(header);
        dlg.initStyle(stageStyle);
        dlg.initOwner(owner);
    }

    /**
     * Configuração padrão do dialogo com alerta.
     *
     */
    private Alert createAlert(AlertType type) {
        Window owner = stage;
        Alert dlg = new Alert(type, "");
        dlg.initModality(modality);
        dlg.initOwner(owner);
        return dlg;
    }

    /**
     * Caso necessario antes de exibir o dialogo determinar se será fechado
     * automaticamente. ex: if (cbCloseDialogAutomatically.isSelected()) { new
     * Thread(() -> { try { Thread.sleep(2000); } catch (InterruptedException e)
     * { e.printStackTrace(); } System.out.println("Attempting to close dialog
     * now..."); Platform.runLater(() -> dlg.close()); }).start(); }
     * dlg.initOwner(owner);
     *
     */
    private Optional<?> showDialog(Dialog<?> dlg) {
        return dlg.showAndWait();
    }

    private TextField createTextField(String id) {
        TextField textField = new TextField("");
        textField.setId(id);
        GridPane.setHgrow(textField, Priority.ALWAYS);
        return textField;
    }

    public void excecao(String cabecalho, Exception ex) {
        ExceptionDialog dlg = new ExceptionDialog(ex);
        configurarDialogo(dlg, cabecalho);
        showDialog(dlg);
    }

    public void erro(String titulo, String cabecalho, String corpo, Exception ex) {
        Alert dlg = createAlert(AlertType.ERROR);
        dlg.setTitle(titulo);
        dlg.getDialogPane().setContentText(corpo);
        final CheckBox checkBox = new CheckBox("");
        checkBox.setId("skip-page-2");
        VBox vbox = new VBox(10, new Label("Deseja exibir exceção?"), checkBox);
        dlg.getDialogPane().setContent(vbox);
        configurarDialogo(dlg, cabecalho);
        Optional<?> resultado = showDialog(dlg);
        if (resultado.get() == ButtonType.OK) {
            if (checkBox.isSelected()) {
                excecao(cabecalho, ex);
            }
        }
    }

    public void informacao(String titulo, String cabecalho, String corpo) {
        Alert dlg = createAlert(AlertType.INFORMATION);
        dlg.setTitle(titulo);
        dlg.getDialogPane().setContentText(corpo);
        configurarDialogo(dlg, cabecalho);

        // lets get some output when events happen
//        dlg.setOnShowing(evt -> System.out.println(evt));
//        dlg.setOnShown(evt -> System.out.println(evt));
//        dlg.setOnHiding(evt -> System.out.println(evt));
//        dlg.setOnHidden(evt -> System.out.println(evt));
//              dlg.setOnCloseRequest(evt -> evt.consume());
        showDialog(dlg);
    }

    public Optional<ButtonType> confirmacao(String titulo, String cabecalho, String corpo) {
        Alert dlg = createAlert(AlertType.CONFIRMATION);
        dlg.setTitle(titulo);
        dlg.getDialogPane().setContentText(corpo);

//        if (!cbShowCancel.isSelected()) {
//            dlg.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
//        }
        configurarDialogo(dlg, cabecalho);
        return (Optional<ButtonType>) showDialog(dlg);
    }

    public void aviso(String titulo, String cabecalho, String corpo) {
        Alert dlg = createAlert(AlertType.WARNING);
        dlg.setTitle(titulo);
        dlg.getDialogPane().setContentText(corpo);
        configurarDialogo(dlg, cabecalho);
        showDialog(dlg);
    }

    public String entrada(String titulo, String cabecalho, String corpo) {
        TextInputDialog dlg = new TextInputDialog("");
        dlg.setTitle(titulo);
        dlg.getDialogPane().setContentText(corpo);
        configurarDialogo(dlg, cabecalho);
        showDialog(dlg);
        return dlg.getResult();
    }

    public Pair<String, String> login() {
        LoginDialog dlg = new LoginDialog(null, null);
        dlg.setTitle("Login");
        DialogPane dialogPane = dlg.getDialogPane();
        dialogPane.setContentText("Login");
        configurarDialogo(dlg, "Logue com uma conta Gerencial");
        showDialog(dlg);
        return dlg.getResult();
    }

    public void financeiro(SceneManager manager) {
        Window owner = stage;
        // define pages to show.
        // Because page1 references page2, we need to declare page2 first.
        final WizardPane page2 = new WizardPane();
        int row = 0;
        GridPane pane1 = new GridPane();
        pane1.setVgap(10);
        pane1.setHgap(10);
        pane1.add(new Label("Informação opacional"), 0, row++);
        pane1.add(new Label("Valor pago"), 0, row);
        TextField txtValorPago = createTextField("valorPago");
        MaskFieldUtil.numericField(txtValorPago);
        pane1.add(txtValorPago, 1, row++);

        pane1.add(new Label("Número do laudo"), 0, row);
        TextField txtNumeroLaudo = createTextField("numeroLaudo");
        pane1.add(txtNumeroLaudo, 1, row++);
        page2.setContent(pane1);
//        page2.setContentText("Informação opacional");//titulo

        final CheckBox registrarFinanceiro = new CheckBox("Deseja cadastrar no financeiro o "
                + "\nagendamento que acaba de ser concluído?");
        registrarFinanceiro.setSelected(true);
        registrarFinanceiro.setId("pular-pagina-2");
        VBox vbox = new VBox(10, new Label("Registrar no financeiro?"), registrarFinanceiro);
        final WizardPane page1 = new WizardPane() {
            // when we exit page 1, we will check the state of the 'skip page 2'
            // checkbox, and if it is true, we will remove page 2 from the pages list
            @Override
            public void onExitingPage(Wizard wizard) {
//                List<WizardPage> pages = wizard.getPages();
//                if (checkBox.isSelected()) {
//                    pages.remove(page2);
//                } else {
//                    if (! pages.contains(page2)) {
//                        pages.add(1, page2);
//                    }
//                }
            }
        };
        page1.setContent(vbox);

        final WizardPane page3 = new WizardPane();
        row = 0;
        GridPane pane2 = new GridPane();
        pane2.setVgap(10);
        pane2.setHgap(10);
        pane2.add(new Label("Registro de hora adicional!"), 0, row++);
        pane2.add(new Label("Houve houras adicionais?"), 0, row);
        final CheckBox temHoraAdicional = new CheckBox("");
        temHoraAdicional.setSelected(false);
        temHoraAdicional.setId("add-horaAdicional-pagina-3");
        pane2.add(temHoraAdicional, 1, row++);

        pane2.add(new Label("Qtd horas adicional"), 0, row);
        TextField txtHoraAdicional = createTextField("horaAdicional");
        txtHoraAdicional.setDisable(true);
        MaskFieldUtil.numericField(txtHoraAdicional);
        pane2.add(txtHoraAdicional, 1, row++);

        temHoraAdicional.selectedProperty().addListener((p, o, n) -> {
            if (temHoraAdicional.isSelected()) {
                txtHoraAdicional.setDisable(false);
            } else {
                txtHoraAdicional.setDisable(true);
            }
        });
        page3.setContent(pane2);
//        page3.setContentText("Registro de hora adicional!");

        final WizardPane page4 = new WizardPane();
        page4.setContentText("Obrigado, clique em finalizar p/ concluir.");

        // create wizard
        Wizard wizard = new Wizard(owner);
        wizard.setTitle("Registro Financeiro");
        Wizard.Flow branchingFlow = new Wizard.Flow() {

            @Override
            public Optional<WizardPane> advance(WizardPane currentPage) {
                return Optional.of(getNext(currentPage));
            }

            @Override
            public boolean canAdvance(WizardPane currentPage) {
                return currentPage != page4;
            }

            private WizardPane getNext(WizardPane currentPage) {
                if (currentPage == null) {
                    return page1;
                } else if (currentPage == page1) {
                    return registrarFinanceiro.isSelected() ? page2 : page4;
                } else if (currentPage == page2) {
                    return page3;
                } else {
                    return page4;
                }
            }
        };

        //wizard.setFlow( new LinearWizardFlow( page1, page2, page3));
        wizard.setFlow(branchingFlow);

        // show wizard
        wizard.showAndWait().ifPresent(result -> {
            if (result == ButtonType.FINISH) {
                Financeiro f = new Financeiro();
                Financeiro f2 = new Financeiro();
                if (!txtNumeroLaudo.getText().equals("")) {
                    f.setNumeroLaudo(txtNumeroLaudo.getText());
                    f2.setNumeroLaudo(txtNumeroLaudo.getText());
                }
                if (!txtValorPago.getText().equals("")) {
                    f.setValorPago(Double.parseDouble(txtValorPago.getText()));
                }
                f.setHoraAdicional(-1);
                manager.getAgendaController().salvarFinanceiro(f);
                if (temHoraAdicional.isSelected()) {
                    f2.setHoraAdicional(Integer.parseInt(txtHoraAdicional.getText()));
                    manager.getAgendaController().salvarFinanceiro(f2);
                }
                System.out.println("Wizard finished, settings: " + wizard.getSettings());
            }
        });
    }

//    public static void criarDialogException(String titulo, String subtitulo, String informacao, Object ex, String tituloException) {
//
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(titulo);
//        alert.setHeaderText(subtitulo);
//        alert.setContentText(informacao);
//
//        DialogPane dialogPane = alert.getDialogPane();
//        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());
//
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
////        ex.printStackTrace(pw);
//        String exception = "";
//        if (ex instanceof Exception) {
//            ((Exception) ex).printStackTrace(pw);
//            exception = sw.toString();
//        } else {
//            exception = (String) ex;
//        }
//
//        Label label = new Label(tituloException);
//
//        TextArea txtArea = new TextArea(exception);
//        txtArea.setEditable(false);
//        txtArea.setWrapText(true);
//
//        txtArea.setMaxWidth(Double.MAX_VALUE);
//        txtArea.setMaxHeight(Double.MAX_VALUE);
//        GridPane.setVgrow(txtArea, Priority.NEVER);
//        GridPane.setHgrow(txtArea, Priority.NEVER);
//
//        GridPane expContent = new GridPane();
//        expContent.setMaxWidth(Double.MAX_VALUE);
//        expContent.add(label, 0, 0);
//        expContent.add(txtArea, 0, 1);
//        dialogPane.setExpandableContent(expContent);
//
//        alert.showAndWait();
//    }
//
//    public static void criarDialogInfomation(String titulo, String subtitulo, String informacao) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(titulo);
//        alert.setHeaderText(subtitulo);
//        alert.setContentText(informacao);
////        alert.getDialogPane().setStyle("/styles/material-fx-v0_3.css");
//
//        DialogPane dialogPane = alert.getDialogPane();
//        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());
//
//        alert.showAndWait();
////        dialogPane.getStyleClass().add("myDialog");
//    }
//
//    public static void criarDialogWarning(String titulo, String subtitulo, String informacao) {
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setTitle(titulo);
//        alert.setHeaderText(subtitulo);
//        alert.setContentText(informacao);
//
//        DialogPane dialogPane = alert.getDialogPane();
//        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());
//
//        alert.showAndWait();
//    }
//    public static Optional<ButtonType> criarDialogConfirmacao(String titulo, String subtitulo, String informacao) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle(titulo);
//        alert.setHeaderText(subtitulo);
//        alert.setContentText(informacao);
//
//        DialogPane dialogPane = alert.getDialogPane();
//        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());
//
//        return alert.showAndWait();
//
//        /**
//         * exemplo Optional<ButtonType> result = alert.showAndWait(); if
//         * (result.get() == ButtonType.OK){
//         */
//    }
//    public static String criarDialogInput(String titulo, String subTitulo, String confirmacao) {
//        TextInputDialog dlg = new TextInputDialog("");
//        dlg.setTitle(titulo);
//
//        DialogPane dialogPane = dlg.getDialogPane();
//        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());
//
//        dialogPane.setHeaderText(subTitulo);
//        dialogPane.setContentText(confirmacao);
//
//        dlg.showAndWait();
//        return dlg.getResult();
//    }
//    public static Pair<String, String> criarLoginDialog() {
////        Pair<String, String> p = new Pair<>("Usu�rio","Senha");
//        LoginDialog dlg = new LoginDialog(null, null);
//        dlg.setTitle("Login");
//
//        DialogPane dialogPane = dlg.getDialogPane();
//        dialogPane.getStylesheets().add(EnumCaminho.CSSMaterial.getCaminho());
//        dialogPane.setHeaderText("Logue com uma conta Gerencial");
//        dialogPane.setContentText("Login");
//
//        dlg.showAndWait();
//        return dlg.getResult();
//    }
}
