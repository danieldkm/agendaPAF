package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.DateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalendarioSemestralController {

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

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        System.out.println("iniciar start");
//        stage = primaryStage;
//        mainCalendario = FXMLLoader.load(EmpresaController.class.getResource(EnumCaminho.CalendarioSemestral.getCaminho()));
//        Scene scene = new Scene(mainCalendario);
//        stage.setScene(scene);
//        stage.setTitle("Calendário Semestral");
////        stage.setResizable(false);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.show();
//        stage.toFront();
////        stage.getIcons().add(Controller.icoPAF);
//        stage.setResizable(true);
//        stage.setOnHidden(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent t) {
//            }
//        });
//        System.out.println("fim start");
//    }
    @FXML
    public void initialize() {
    }

    public void setFormatDate(String n) {
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

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainCalendario(BorderPane mainCalendario) {
        this.mainCalendario = mainCalendario;
    }

}
