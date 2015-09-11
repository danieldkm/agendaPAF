package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.DateChooser;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CalendarioSemestralController extends Application implements Initializable {

    @FXML
    private BorderPane mainCalendario;
    @FXML
    private GridPane gpPane;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private DateChooser dc1;
    private DateChooser dc2;
    private DateChooser dc3;
    private DateChooser dc4;
    private DateChooser dc5;
    private DateChooser dc6;
    private Stage stage;
    private String n = null;
    private UtilDialog dialog;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("iniciar start");
        stage = primaryStage;
        mainCalendario = FXMLLoader.load(EmpresaController.class.getResource(EnumCaminho.CalendarioSemestral.getCaminho()));
        Scene scene = new Scene(mainCalendario);
        stage.setScene(scene);
        stage.setTitle("Calendário Semestral");
//        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.toFront();
//        stage.getIcons().add(Controller.icoPAF);
        stage.setResizable(true);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
            }
        });
        System.out.println("fim start");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("inicio do initialize");
        mainCalendario.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }
            }
        });
        if (dialog == null){
            dialog = new UtilDialog();
        }
        do {
            n = dialog.criarDialogInput("Confirmação", "1 - Primeiro semestre\n2 - Segundo semestre", "Digite 1 ou 2");
        } while (!n.equals("1") && !n.equals("2"));

        Date data1 = null;
        Date data2 = null;
        Date data3 = null;
        Date data4 = null;
        Date data5 = null;
        Date data6 = null;
        Date controle = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(controle);
        String ano = cal.get(Calendar.YEAR) + "";
        System.out.println("ano " + ano);
        try {
            if (n.equals("1")) {
                data1 = sdf.parse("1/01/" + ano);
                data2 = sdf.parse("1/02/" + ano);
                data3 = sdf.parse("1/03/" + ano);
                data4 = sdf.parse("1/04/" + ano);
                data5 = sdf.parse("1/05/" + ano);
                data6 = sdf.parse("1/06/" + ano);
            } else if (n.equals("2")) {
                data1 = sdf.parse("1/07/" + ano);
                data2 = sdf.parse("1/08/" + ano);
                data3 = sdf.parse("1/09/" + ano);
                data4 = sdf.parse("1/10/" + ano);
                data5 = sdf.parse("1/11/" + ano);
                data6 = sdf.parse("1/12/" + ano);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        dc1 = new DateChooser(data1);
        gpPane.add(dc1, 0, 0);

        dc2 = new DateChooser(data2);
        gpPane.add(dc2, 2, 0);

        dc3 = new DateChooser(data3);
        gpPane.add(dc3, 0, 2);

        dc4 = new DateChooser(data4);
        gpPane.add(dc4, 2, 2);

        dc5 = new DateChooser(data5);
        gpPane.add(dc5, 0, 4);

        dc6 = new DateChooser(data6);
        gpPane.add(dc6, 2, 4);

        System.out.println("fim do initialize");
    }

}
