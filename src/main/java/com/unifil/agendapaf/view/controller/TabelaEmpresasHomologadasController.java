package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.EmpresasHomologadas;
import com.unifil.agendapaf.service.EmpresasHomologadasService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TabelaEmpresasHomologadasController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilDialog = new UtilDialog();
            mainTbHomologadas.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });

            tcEmpresa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmpresasHomologadas, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<EmpresasHomologadas, String> data) {
                    return new SimpleObjectProperty<>(data.getValue().getIdEmpresa().getDescricao());
                }
            });

            tcDtAviso.setCellFactory(column -> {
                return new TableCell<Agenda, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            // Format date.
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd-MM-yyyy"));
                            // Style all dates in March with a different color.
                            setTextFill(Color.CHOCOLATE);
                            setStyle("-fx-background-color: yellow");
                        }
                    }
                };
            });

            tcDtAvaliada.setCellFactory(column -> {
                return new TableCell<Agenda, LocalDate>() {
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            // Format date.
                            setText(UtilConverter.converterDataToFormat(UtilConverter.converterLocalDateToUtilDate(item), "dd-MM-yyyy"));
                        }
                    }
                };
            });
//        tcCod.setCellValueFactory(new PropertyValueFactory<EmpresasHomologadas, Integer>("codEmpresa"));
//        tcEmpresa.setCellValueFactory(new PropertyValueFactory<EmpresasHomologadas, String>("nomeEmpresa"));
//        tcEmail.setCellValueFactory(new PropertyValueFactory<EmpresasHomologadas, String>("email"));
//        tcDtAvaliada.setCellValueFactory(new PropertyValueFactory<EmpresasHomologadas, Date>("dataHomologada"));
//        tcDtAviso.setCellValueFactory(new PropertyValueFactory<EmpresasHomologadas, Date>("dataAviso"));
//        tcVisualizado.setCellValueFactory(new PropertyValueFactory<EmpresasHomologadas, Boolean>("visualizado"));

            tvHomologadas.getItems().setAll(StaticLista.getListaGlobalEmpresasHomologadas());
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela empresas homologadas", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainTbHomologadas
                    = FXMLLoader.load(FXMLController.class
                            .getResource(EnumCaminho.TabelaEmpresasHomologadas.getCaminho()));
            Scene scene = new Scene(mainTbHomologadas);

            stage.setScene(scene);

            stage.setTitle(
                    "Tabela de Empresas Homologadas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela empresa homologadas", e, "Exception:");
        }
    }

    @FXML
    private BorderPane mainTbHomologadas;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<EmpresasHomologadas> tvHomologadas;
    @FXML
    private TableColumn tcDtAvaliada;
    @FXML
    private TableColumn tcDtAviso;
    @FXML
    private TableColumn tcEmpresa;

    private UtilDialog utilDialog;
    private static Stage stage;
    private ObservableList<EmpresasHomologadas> lista = FXCollections.observableArrayList();

    @Deprecated
    private void atualizarTabela() {
        tvHomologadas.setItems(StaticLista.getListaGlobalEmpresasHomologadas());
    }

    @FXML
    private void onKeyPressdTxtBuscar(KeyEvent e) {
        lista.clear();
        for (EmpresasHomologadas eh : StaticLista.getListaGlobalEmpresasHomologadas()) {
            if (eh.toString().toLowerCase().contains(txtBuscar.getText().toLowerCase() + e.getText().toLowerCase())) {
                lista.add(eh);
            }
        }
        tvHomologadas.setItems(lista);
    }

    @FXML
    private void onMouseClickedTabelaHomologada(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                EmpresasHomologadasService ehs = new EmpresasHomologadasService();
                Optional<ButtonType> result = utilDialog.criarDialogConfirmacao(EnumMensagem.TabelaEmpresasHomologadasPergunta.getTitulo(), EnumMensagem.TabelaEmpresasHomologadasPergunta.getSubTitulo(), EnumMensagem.TabelaEmpresasHomologadasPergunta.getMensagem());
                if (tvHomologadas.getSelectionModel().getSelectedItem() != null) {
                    for (EmpresasHomologadas empresasHomologadas : StaticLista.getListaGlobalEmpresasHomologadas()) {
                        if (empresasHomologadas.getId().equals(tvHomologadas.getSelectionModel().getSelectedItem().getId())) {
                            if (result.get() == ButtonType.OK) {
                                try {
                                    empresasHomologadas.setVisualizado("SIM");
                                    ehs.editar(empresasHomologadas);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            } else if (result.get() == ButtonType.CANCEL) {
//                                System.out.println("update Nao " + empresasHomologadas.getDataHomologada2());
                                try {
                                    empresasHomologadas.setVisualizado("NAO");
                                    ehs.editar(empresasHomologadas);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    if (JPA.em(false).isOpen()) {
                        JPA.em(false).close();
                    }
                }

            } catch (Exception e) {
                if (JPA.em(false).isOpen()) {
                    JPA.em(false).close();
                }
                System.out.println("Erro!! Metodo: onMouseClickedTabelaHomologada / Class: TabelaEmpresasHomologadasController");
                e.printStackTrace();
            }

        }
    }
}
