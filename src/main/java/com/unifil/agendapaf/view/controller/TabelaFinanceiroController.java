package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
            tcDataInicial.setCellFactory(column -> {
                return new TableCell<Financeiro, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            // Format date.
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd/MM/yyyy"));
                        }
                    }
                };
            });

            tcDataFinal.setCellFactory(column -> {
                return new TableCell<Financeiro, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            // Format date.
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd/MM/yyyy"));
                        }
                    }
                };
            });
            tvFinanceiro.setItems(Controller.getFinanceiros());
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
    @FXML
    private TableColumn tcDataInicial;
    @FXML
    private TableColumn tcDataFinal;

    private static Stage stage;
    public ObservableList<Financeiro> lista;
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
        lista = FXCollections.observableArrayList();
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
        tvFinanceiro.setItems(lista);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainTabelaFinanceiro(BorderPane mainTabelaFinanceiro) {
        this.mainTabelaFinanceiro = mainTabelaFinanceiro;
    }

}
