package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TabelaFinanceiroController {

    @FXML
    public void initialize() {
        try {
            sceneManager = SceneManager.getInstance();
            tvFinanceiro.getItems().setAll(StaticLista.getListaGlobalFinanceiro());
        } catch (Exception e) {
            e.printStackTrace();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela financeiro", e, "Exception:");
        }
    }

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private BorderPane mainTabelaFinanceiro;
    @FXML
    private TableView<Financeiro> tvFinanceiro;

    private static Stage stage;
    public ObservableList<Financeiro> lista = FXCollections.observableArrayList();
    private SceneManager sceneManager;

    @FXML
    private void onKeyPressdTxtBuscar(KeyEvent e) {
        actionBtnBuscar(e);
    }

    @FXML
    private void onMouseClickedTabelaFinanceiro(MouseEvent event) {
        if (event.getClickCount() == 2) {
            sceneManager.setFinanceiroEncontrada(tvFinanceiro.getSelectionModel().getSelectedItem());
            sceneManager.showFinanceiro(false, null, null);
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainTabelaFinanceiro(BorderPane mainTabelaFinanceiro) {
        this.mainTabelaFinanceiro = mainTabelaFinanceiro;
    }

}
