package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.animations.FadeInLeftTransition;
import com.unifil.agendapaf.animations.FadeInRightTransition;
import com.unifil.agendapaf.animations.FadeInTransition;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.statics.StaticLista;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

/**
 * FXML Controller class
 *
 * @author danielmorita
 */
public class InicialController {

    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblRudy;
    @FXML
    private VBox vboxBottom;
    @FXML
    private Label lblClose;
    private Stage stage;
    @FXML
    private ImageView imgLoading;

    @FXML
    public void initialize() {
        System.out.println("??");
        initTask();
        lblClose.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
//        Platform.runLater(() -> {
//            new FadeInLeftTransition(lblWelcome).play();
//            new FadeInRightTransition(lblRudy).play();
//            new FadeInTransition(vboxBottom).play();
//        });

    }

    private boolean erro = false;

    private void initTask() {
        Task<String> tarefaCargaPg = new Task<String>() {

            @Override
            protected String call() throws Exception {
                try {
                    JPA.getFactory();
                    StaticLista.setListaGlobalAgenda(Controller.getAgendas());
                    StaticLista.setListaGlobalHistorico(Controller.getHistoricos());
                    StaticLista.setListaGlobalFeriado(Controller.getFeriados());
                    StaticLista.setListaGlobalEstado(Controller.getEstados());
                    StaticLista.setListaGlobalCidade(Controller.getCidades());
                    StaticLista.setListaGlobalUsuario(Controller.getUsuarios());
                    StaticLista.setListaGlobalFinanceiro(Controller.getFinanceiros());
                    StaticLista.setListaGlobalEmpresa(Controller.getEmpresas());
                    StaticLista.setListaGlobalEmpresasHomologadas(Controller.getEmpresasHomologadas());
                    StaticLista.setListaGlobalContato(Controller.getContatos());
                    StaticLista.setListaGlobalEndereco(Controller.getEnderecos());
                    StaticLista.setListaGlobalTelefone(Controller.getTelefones());
                } catch (Exception e) {
                    e.printStackTrace();
                    erro = true;
                }
                if (erro) {
                    return "ERRO";
                }
                return "OK";
            }

            @Override
            protected void succeeded() {
                System.out.println("SUCCEEDED " + erro);
                if (erro) {
//                    UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao tentar estabelecer comunicação com o Bando de dados!!\nO programa será encerrado.");
                }
//                if (getValue().equals("OK")) {
//                    SceneManager.getInstance().showNewLogin();
//                }
            }
        };
        Thread t = new Thread(tarefaCargaPg);
        t.setDaemon(true);
        t.start();

        tarefaCargaPg.setOnRunning((WorkerStateEvent event) -> {
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInRightTransition(lblRudy).play();
            new FadeInTransition(vboxBottom).play();
        });

        tarefaCargaPg.setOnSucceeded((WorkerStateEvent event) -> {
            System.out.println("Event " + erro);
            SceneManager.getInstance().showNewLogin(erro);
            stage.close();
        });
    }

    private void longStart() {
        Service<ApplicationContext> service = new Service<ApplicationContext>() {
            @Override
            protected Task<ApplicationContext> createTask() {
                return new Task<ApplicationContext>() {
                    @Override
                    protected ApplicationContext call() throws Exception {
                        System.out.println("call");
//                        ApplicationContext appContex = new App
//                        int max = appContex.getBeanDefinitionCount();
                        int max = 10000;
                        updateProgress(0, max);
                        for (int k = 0; k < max; k++) {
                            Thread.sleep(50);
                            updateProgress(k + 1, max);
                        }
                        return null;
                    }
                };
            }
        };
        service.start();
        service.setOnRunning((WorkerStateEvent event) -> {
            System.out.println("setOnRunning");
//            new FadeInLeftTransition(lblWelcome).play();
//            new FadeInRightTransition(lblRudy).play();
//            new FadeInTransition(vboxBottom).play();
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
            System.out.println("setOnScuceeded");
//            config2 config = new config2();
//            config.newStage(stage, lblClose, "/herudi/view/login.fxml", "Sample Apps", true, StageStyle.UNDECORATED, false);
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
