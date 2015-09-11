package com.unifil.agendapaf;

import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.view.controller.LoginController;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

/**
 *
 * @author danielmorita
 */
public class SceneManager {

    private MainApp application;
    private static SceneManager instance;

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    void setPrimaryStage(MainApp aThis) {
        this.application = application;
        initPrimaryStage();

    }

    private void initPrimaryStage() {

        try {
//            stage = primaryStage;
//            System.out.println("EnumCaminho.Login.getCaminho() " + EnumCaminho.Login.getCaminho());
//            MainLogin = FXMLLoader.load(LoginController.class.getResource(EnumCaminho.Login.getCaminho()));
//            Scene s = new Scene(MainLogin);
//            stage.setScene(s);
//            stage.setTitle("Agenda PAF-ECF");
//            stage.setResizable(false);
//            stage.show();
//            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//
//                @Override
//                public void handle(WindowEvent t) {
//                    JPA.getFactory().close();
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
