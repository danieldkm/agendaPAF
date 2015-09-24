package com.unifil.agendapaf.exemplos;

import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author danielmorita
 */
public class TesteDialogStyle extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("This is a Custom Confirmation Dialog");
        alert.setContentText("We override the style classes of dialog.css");

        Button button = new Button("Click to display an alert");
        button.setOnAction(e -> {
            Optional<ButtonType> result = alert.showAndWait();
            result.ifPresent(System.out::println);
        });

        Scene scene = new Scene(new StackPane(button), 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        DialogPane dialogPane = alert.getDialogPane();

        dialogPane.setStyle("-fx-background-color: greenyellow;");

        dialogPane.getStyleClass().remove("alert");

        GridPane grid = (GridPane) dialogPane.lookup(".header-panel");
        grid.setStyle("-fx-background-color: cadetblue; "
                + "-fx-font-style: italic;");

        StackPane stackPane = new StackPane(new ImageView(
                new Image(getClass().getResourceAsStream("lock24.png"))));
        stackPane.setPrefSize(24, 24);
        stackPane.setAlignment(Pos.CENTER);
        dialogPane.setGraphic(stackPane);

        dialogPane.lookup(".content.label").setStyle("-fx-font-size: 16px; "
                + "-fx-font-weight: bold; -fx-fill: blue;");

        ButtonBar buttonBar = (ButtonBar) alert.getDialogPane().lookup(".button-bar");
        buttonBar.setStyle("-fx-font-size: 24px;"
                + "-fx-background-color: indianred;");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-font-family: \"Andalus\";"));
    }

}
