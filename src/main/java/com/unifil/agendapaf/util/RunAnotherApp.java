package com.unifil.agendapaf.util;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author danielmorita
 */
public class RunAnotherApp {

    public static void runAnotherApp2(Class<? extends Application> outroApp, Stage s) {
        try {
            Application app2 = outroApp.newInstance();
//            s.close();
//            Stage anotherStage = new Stage();
            app2.start(s);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro!! Classe: FXMLController.class  Metodo: runAnotherApp");
        }
    }

    public static void runAnotherApp(Class<? extends Application> outroApp) {
        try {
            Application app2 = outroApp.newInstance();
            Stage anotherStage = new Stage();
            app2.start(anotherStage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro!! Classe: FXMLController.class  Metodo: runAnotherApp");
        }
    }

}
