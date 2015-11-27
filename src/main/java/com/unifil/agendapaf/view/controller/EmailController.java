package com.unifil.agendapaf.view.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.unifil.agendapaf.MainApp;
import com.unifil.agendapaf.util.UtilEmail;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.util.Arrays;
import java.util.Collection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;

/**
 * FXML Controller class
 *
 * @author danielmorita
 */
public class EmailController extends Application {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        System.out.println("WHATs1111");
//        principal.getChildren().add(ActionUtils.createToolBar(actions, ActionUtils.ActionTextBehavior.SHOW));
        principal.getChildren().add(0, ActionUtils.createToolBar(actions, ActionUtils.ActionTextBehavior.SHOW));
//        principal.getChildren().add(new Button("jbjvttrxe"));
        System.out.println("WHATs");
    }

    static class DummyAction extends Action {

        private UtilEmail utilEmail;

        public DummyAction(String name, Node image) {
            super(name);
            utilEmail = new UtilEmail();
            setGraphic(image);
            setStyle("-fx-font: 18px Arial");

//            setEventHandler(ae -> String.format("Action '%s' is executed", getText()));
            setEventHandler(ae -> System.out.println("???? " + name));
        }

        public DummyAction(String name) {
            super(name);
            utilEmail = new UtilEmail();
            setEventHandler(ae -> System.out.println("awdwd" + name));
        }

        @Override
        public String toString() {
            return getText();
        }

        private void onActionToolBar() {
            utilEmail.enviaEmailSimples(null, "Mensagem", "Assunto");
        }
    }

    @FXML
    private VBox principal;
    private Stage stage;
    private Collection<? extends Action> actions = Arrays.asList(
            new DummyAction("", imgEnviar),
            new DummyAction("", imgAnexo),
            new DummyAction("   A   ", null)
    //            new ActionGroup("Group 1", image, new DummyAction("Action 1.1", image),
    //                    new DummyAction("Action 1.2")),
    //            new ActionGroup("Group 2", image, new DummyAction("Action 2.1"),
    //                    ActionUtils.ACTION_SEPARATOR,
    //                    new ActionGroup("Action 2.2", new DummyAction("Action 2.2.1"),
    //                            new DummyAction("Action 2.2.2")),
    //                    new DummyAction("Action 2.3")),
    //            ActionUtils.ACTION_SEPARATOR,
    //            new DummyAction("Action 3", image),
    //            new ActionGroup("Group 4", image, new DummyAction("Action 4.1", image),
    //                    new DummyAction("Action 4.2"))
    );

    private static final ImageView imgFormatacao = new ImageView(new Image("/image/formatacao.png"));
    private static final ImageView imgEnviar = new ImageView(new Image("/image/enviar.png"));
    private static final ImageView imgAnexo = new ImageView(new Image("image/anexo.png"));

    @FXML
    private TextField txtPara;

    @FXML
    private ImageView imgAddContato;

    @FXML
    private void onMouseClickedTxtPara() {
        imgAddContato.setVisible(true);
    }

    @FXML
    private void onMouseClickedTxtAssunto() {
        imgAddContato.setVisible(false);
    }

    @FXML
    private void onMouseClickedTxtDe() {
        imgAddContato.setVisible(false);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader rootLoader = new FXMLLoader();
        rootLoader.setLocation(MainApp.class.getResource(EnumCaminho.Email.getCaminho()));
        VBox layout = (VBox) rootLoader.load();
//        stage.initStyle(StageStyle.UNDECORATED);
        principal = layout;
//        layout.getChildren().add(ActionUtils.createToolBar(null, ActionUtils.ActionTextBehavior.SHOW));
//        stage.setResizable(false);
        Scene scene = new Scene(layout);
        stage.setTitle("TITULO");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
//        initialize();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
