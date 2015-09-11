package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SincronizarBDController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        mainSinc = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.SincronizarBD.getCaminho()));
        Scene scene = new Scene(mainSinc);
        stage.setScene(scene);
        stage.setTitle("Sincronizar BD");
        stage.setResizable(false);
//        stage.initOwner(this.myParent);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.toFront();
//        stage.getIcons().add(Controller.icoPAF);
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
            }
        });
    }

    private static Stage stage;
    @FXML
    private BorderPane mainSinc;
    @FXML
    private Button btnSalvar;
    @FXML
    private RadioButton rbBackup;
    @FXML
    private RadioButton rbRestore;
    private File file;

    @FXML
    private void actionBtnSalvar() {
        if (rbBackup.isSelected()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar");
            file = fileChooser.showSaveDialog(stage);
        } else if (rbRestore.isSelected()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar");
            file = fileChooser.showOpenDialog(stage);
        }
    }

    @FXML
    private void actionGerar() {
//        File d = new File("C:/Users/Administrador/Documents/firebird/AGENDA_PAF-ECF.FBK");
        
        
//        bkpFireBirdDB();
//        restoreFireBirdDB();
//        if (rbBackup.isSelected()) {
//        } else if (rbRestore.isSelected()) {
//        }
    }
    
//    private void bkpFireBirdDB() {
//        try {
//            bkp = new FBBackupManager();
//            bkp.setUser(Controller.user);
//            bkp.setPassword(Controller.pass);
//            bkp.setDatabase(Controller.nomeDataBase);
//            bkp.setPort(3050);
//            bkp.setHost(Controller.ip);
////            String novo = file.getAbsoluteFile() + ".fbk";
////            File f = new File(novo);
////            file = f;
////            System.out.println("arquivo salvo em " + file.getAbsolutePath());
////            bkp.setBackupPath(file.getAbsolutePath());
////            C:\Users\npi\AppData\Roaming\Microsoft\Windows\Network Shortcuts\aaaa.fbk
//            bkp.setBackupPath("C:/Users/Administrador/Documents/firebird/AGENDA_PAF-ECF.FBK");
//            bkp.setLogger(System.out);
//            bkp.setVerbose(true);
//            bkp.backupDatabase();
//            bkp.clearBackupPaths();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void restoreFireBirdDB() {
//        try {
//            bkp.setUser(Controller.user);
//            bkp.setPassword(Controller.pass);
//            bkp.setDatabase("C:/Users/npi/Desktop/IBExpert.2012.02.21.1/temp/AGENDAPAF_ECF.FDB");
//            bkp.setHost("localhost");
////            System.out.println("abrir arquivo " + file.getAbsolutePath());
//            bkp.setBackupPath("C:/Users/Administrador/Documents/firebird/AGENDA_PAF-ECF.FBK");
//            bkp.setVerbose(true);
//            bkp.setRestoreReplace(true);
//            bkp.restoreDatabase();
//            
//            
////            FBBackupManager restore = new FBBackupManager();
////            restore.setUser(Controller.user);
////            restore.setPassword(Controller.pass);
////            restore.setDatabase("C:/Users/npi/Desktop/IBExpert.2012.02.21.1/temp/AGENDAPAF_ECF.FDB");
////            restore.setHost("localhost");
//////            System.out.println("abrir arquivo " + file.getAbsolutePath());
////            restore.setBackupPath("C:/Users/Administrador/Documents/firebird/AGENDA_PAF-ECF.FBK");
////            restore.setVerbose(true);
////            restore.setRestoreReplace(true);
////            restore.restoreDatabase();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void copyFile(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }

        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;

        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }

}
