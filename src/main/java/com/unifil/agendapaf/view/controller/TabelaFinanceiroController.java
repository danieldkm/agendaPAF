package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TabelaFinanceiroController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilDialog = new UtilDialog();
            mainTabelaFinanceiro.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            tvFinanceiro.getItems().setAll(StaticLista.getListaGlobalFinanceiro());
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela financeiro", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainTabelaFinanceiro = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.TabelaFinanceiro.getCaminho()));
            Scene scene = new Scene(mainTabelaFinanceiro);
            stage.setScene(scene);
            stage.setTitle("Tabela Financeiro");
//        stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela financeiro", e, "Exception:");
        }
    }

    private static Stage stage;
    public ObservableList<Financeiro> lista = FXCollections.observableArrayList();
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private BorderPane mainTabelaFinanceiro;
    @FXML
    private TableView<Financeiro> tvFinanceiro;

    private UtilDialog utilDialog;

    @FXML
    private void onKeyPressdTxtBuscar(KeyEvent e) {
        actionBtnBuscar(e);
    }

    @FXML
    private void onMouseClickedTabelaFinanceiro(MouseEvent event) {
        if (event.getClickCount() == 2) {
            StaticObject.setFinanceiroEncontrada(tvFinanceiro.getSelectionModel().getSelectedItem());
            RunAnotherApp.runAnotherApp(FinanceiroController.class);
            stage.close();
        }
    }

    @FXML
    private void actionBtnBuscar(KeyEvent e) {
        lista.clear();
        for (Financeiro financeiro : StaticLista.getListaGlobalFinanceiro()) {
            String todos = financeiro.getId() + " " + financeiro.getIdEmpresa().getDescricao() + " "
                    + financeiro.getTipoServico() + " " + financeiro.getValorPago() + " "
                    + financeiro.getCategoria() + " " + financeiro.getNumeroLaudo() + " "
                    + financeiro.getDataInicial() + " " + financeiro.getDataFinal() + " "
                    + financeiro.getHoraAdicional();
            if (todos.toLowerCase().contains(txtBuscar.getText().toLowerCase().trim() + e.getText().toLowerCase())) {
                lista.add(financeiro);
            }
        }
        tvFinanceiro.getItems().setAll(lista);

    }

}
