package com.unifil.agendapaf;

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
        mainStage.setTitle("Musicott");
        mainStage.getIcons().add(new Image("file:resources/images/musicotticon.png"));
        SceneManager.getInstance().setPrimaryStage(this);
    }

    public Stage getStage() {
        return mainStage;
    }

}
