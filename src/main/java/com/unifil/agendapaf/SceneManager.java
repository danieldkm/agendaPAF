package com.unifil.agendapaf;

import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.view.controller.LoginController;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author danielmorita
 */
public class SceneManager {

    private VBox loginLayout;

    private MainApp application;
    private static SceneManager instance;

    private LoginController loginController;

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    void setPrimaryStage(MainApp aThis) {
        this.application = aThis;
        initPrimaryStage();

    }

    private void initPrimaryStage() {
        showLogin();
    }

    public void showLogin() {

        try {

            Stage rootStage = this.application.getStage();
            FXMLLoader rootLoader = new FXMLLoader();
            rootLoader.setLocation(MainApp.class.getResource(EnumCaminho.Login.getCaminho()));
            loginLayout = (VBox) rootLoader.load();
            loginController = rootLoader.getController();
            loginController.setStage(rootStage);

            Scene s = new Scene(loginLayout);
            rootStage.setScene(s);
            rootStage.setTitle("Agenda PAF-ECF");
            rootStage.setResizable(false);
            rootStage.show();
            rootStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    JPA.getFactory().close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
