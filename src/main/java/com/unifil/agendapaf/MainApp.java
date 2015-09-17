package com.unifil.agendapaf;

import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author danielmorita
 */
public class MainApp extends Application {

    private Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        mainStage.setTitle("Agenda PAF");
        mainStage.getIcons().add(new Image(EnumCaminho.LogoPAFECFUniFil.getCaminho()));
        SceneManager.getInstance().setPrimaryStage(this);
    }

    public Stage getStage() {
        return mainStage;
    }

}
