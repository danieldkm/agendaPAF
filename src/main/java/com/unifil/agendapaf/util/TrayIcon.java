package com.unifil.agendapaf.util;

import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author danielmorita
 */
public class TrayIcon {

    private java.awt.TrayIcon trayIcon;
    private boolean firstAccess = true;

    public TrayIcon() {
    }

    public void createTrayIcon(Stage stage) {
        // get the SystemTray instance
        SystemTray tray = SystemTray.getSystemTray();
        // load an image
        java.awt.Image image = null;
        image = Toolkit.getDefaultToolkit().getImage(TrayIcon.class.getResource(EnumCaminho.ImgLogoPAFECFUniFil.getCaminho()));
        stage.getIcons().add(new Image(EnumCaminho.ImgLogoPAFECFUniFil.getCaminho()) {
        });
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (SystemTray.isSupported()) {
                            if (firstAccess) {
                                Dialogos d = new Dialogos(stage);
                                Optional<ButtonType> n = d.confirmacao("Informações do Sistema",
                                        "O sistema não será fechado, permanecerá acessível\n" //                                    + "no canto inferior direito ao lado do rel�gio",
                                        , "Deseja continuar?");
                                if (n.get() == ButtonType.OK) {
                                    stage.hide();
                                } else {
                                    JPA.getFactory().close();
                                    System.exit(0);
                                }
                                firstAccess = false;
                            } else {
                                stage.hide();
                            }
                        } else {
                            System.exit(0);
                        }
                    }
                });
            }
        });
        // create a action listener to listen for default action executed on the tray icon
        final ActionListener closeListener = new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        };
        ActionListener showListener = new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.show();
//                            TrayIcon.CustomPopUp p = new TrayIcon.CustomPopUp(root);
//                            p.setTranslateX(600);
//                            p.setTranslateY(200);
//                            initEventHandlers(p);
//                            root.getChildren().add(p);
                    }
                });
            }
        };
        // create a popup menu
        PopupMenu popup = new PopupMenu();
        MenuItem showItem = new MenuItem("Show");
        showItem.addActionListener(showListener);
        popup.add(showItem);
        MenuItem closeItem = new MenuItem("Exit");
        closeItem.addActionListener(closeListener);
        popup.add(closeItem);
        trayIcon = new java.awt.TrayIcon(image, "Agenda PAF-ECF", popup);
//        trayIcon.addActionListener(showListener);
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
//                    System.out.println("e.getButton() " + e.getButton());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
//                            System.out.println(23);
                        stage.show();
                    }
                });
            }
        });
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println(e);
        }
    }

    public java.awt.TrayIcon getTrayIcon() {
        return trayIcon;
    }
}
