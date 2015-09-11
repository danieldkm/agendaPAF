package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.service.AgendaService;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.statics.StaticCalendar;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.statics.StaticString;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.util.Util;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import org.controlsfx.control.action.Action;

public class TabelaAgendaController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilDialog = new UtilDialog();

            btnBuscar.setVisible(false);

            tcEmpresa.setCellFactory(new Callback<TableColumn<Agenda, Empresa>, TableCell<Agenda, Empresa>>() {
                @Override
                public TableCell<Agenda, Empresa> call(TableColumn<Agenda, Empresa> param) {
                    final TableCell<Agenda, Empresa> cell = new TableCell<Agenda, Empresa>() {
                        @Override
                        public void updateItem(final Empresa item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                this.setText("");
                            } else {
                                this.setText(item.getDescricao());
                            }
                        }
                    };
                    return cell;
                }

            });

            tcTipo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Agenda, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Agenda, String> data) {
                    return new SimpleObjectProperty<>(Util.porAcentuacaoServico(data.getValue().getTipo()));
                }
            });

            tcStatusAgenda.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Agenda, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Agenda, String> data) {
                    return new SimpleObjectProperty<>(Util.porAcentuacaoServico(data.getValue().getStatusAgenda()));
                }
            });

            tcStatusBoleto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Agenda, String>, ObservableValue<String>>() {

                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Agenda, String> data) {
                    return new SimpleObjectProperty<>(Util.porAcentuacaoServico(data.getValue().getStatusBoleto()));
                }
            });

            tcDtInicial.setCellFactory(column -> {
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
                            if (item.getDayOfMonth() < LocalDate.now().getDayOfMonth()) {
                                setTextFill(Color.CHOCOLATE);
                                setStyle("-fx-background-color: yellow");
                            } else {
                                setTextFill(Color.BLACK);
                                setStyle("");
                            }
                        }
                    }
                };
            });
            tcDtFinal.setCellFactory(column -> {
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
                            if (item.getDayOfMonth() < LocalDate.now().getDayOfMonth()) {
                                setTextFill(Color.CHOCOLATE);
                                setStyle("-fx-background-color: yellow");
                            } else {
                                setTextFill(Color.BLACK);
                                setStyle("");
                            }
                        }
                    }
                };
            });
            tcDtVencimento.setCellFactory(column -> {
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

            data = new DatePicker();
            pane.getChildren().add(data);
            data.setTranslateY(40);
            data.setVisible(false);

//            System.out.println("EMPRESA?? " + getEmpresaEncontrada());
//            System.out.println("TABELA AGENDA FINANCEIRO?? " + isTabelaAgendaToFinanceiro());
//            System.out.println("REAGENDAMENTO?? " + isReagendamento());
//            System.out.println("UPDATE?? " + isUpdate());
            AgendaService as = new AgendaService();
            if (StaticBoolean.isReagendamento()) {
//            TabelaAgendaController.data.setSelectedDate(AgendarController.dataSelecionada);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cbComboBox.getSelectionModel().selectLast();
//            TabelaAgendaController.getCbComboBox().getSelectionModel().selectLast();
//                Dao<Agenda> dao = new Dao(Agenda.class);
                listaAgendas.clear();
                listaAgendas = as.findByDate(StaticCalendar.getDataSelecionada());
                tvAgenda.getItems().setAll(listaAgendas);
            } else if (StaticBoolean.isUpdate()) {
                data.setValue(StaticCalendar.getDataSelecionada());
                cbComboBox.getSelectionModel().selectLast();
                listaAgendas.clear();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                listaAgendas = as.findByDate(StaticCalendar.getDataSelecionada());
                tvAgenda.getItems().setAll(listaAgendas);
            } else if (StaticBoolean.isCancelamento()) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cbComboBox.getSelectionModel().selectLast();
                listaAgendas.clear();
                listaAgendas = as.findByDate(StaticCalendar.getDataSelecionada());
                tvAgenda.setItems(listaAgendas);
            } else {
                cbComboBox.getSelectionModel().selectFirst();
                listaAgendas = as.findOrderBy("dataInicial");
                tvAgenda.getItems().setAll(listaAgendas);
            }
            JPA.em(false).close();

            data.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (data.getValue() == null) {
                        AgendaService as = new AgendaService();
                        listaAgendas = as.findOrderBy("dataInicial");
                        JPA.em(false).close();
                        tvAgenda.getItems().setAll(listaAgendas);
                    } else {
                        AgendaService as = new AgendaService();
                        listaAgendas.clear();
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        listaAgendas = as.findByDate(data.getValue());
                        JPA.em(false).close();
                        tvAgenda.getItems().setAll(listaAgendas);
                    }
                }
            });

            if (StaticObject.getEmpresaEncontrada() != null) {
                empresaEncontrada = StaticObject.getEmpresaEncontrada();
                setCampos();
                StaticObject.setEmpresaEncontrada(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar tabela agenda", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainTbAgenda = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.TabelaAgenda.getCaminho()));
            Scene scene = new Scene(mainTbAgenda);
            stage.setScene(scene);
            stage.setTitle("Tabela de Agenda");
//        stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnHidden(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent t) {
//                TabelaEmpresaController.isTabelaAgenda = false;
                }
            });
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                    StaticBoolean.setTabelaAgenda(false);
                    StaticBoolean.setReagendamento(false);
                    StaticBoolean.setUpdate(false);
                    StaticBoolean.setCancelamento(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start tabela agenda", e, "Exception:");
        }

    }

    @FXML
    public BorderPane mainTbAgenda;
    @FXML
    public TableView<Agenda> tvAgenda;
    @FXML
    private TableColumn tcEmpresa;
//    @FXML
//    private TableColumn<Agenda, String> tcEmpresa;
//    @FXML
//    private TableColumn<Agenda, String> tcResponsavel;
    @FXML
    private TableColumn<Agenda, String> tcTipo;
    @FXML
    private TableColumn tcDtInicial;
    @FXML
    private TableColumn tcDtFinal;
    @FXML
    private TableColumn tcDtVencimento;
//    @FXML
//    private TableColumn<Agenda, String> tcDiaSemana;
    @FXML
    private TableColumn<Agenda, String> tcStatusBoleto;
    @FXML
    private TableColumn<Agenda, String> tcStatusAgenda;
    @FXML
    private ComboBox cbComboBox;
    @FXML
    private HBox hbPane;
    @FXML
    private Pane pane;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox cbStatusAgenda;

    private static Stage stage;
//    public eu.schudt.javafx.controls.calendar.DatePicker data;
    private DatePicker data;

    public ObservableList<Agenda> listaAgendas = FXCollections.observableArrayList();
    public Empresa empresaEncontrada;
    private UtilDialog utilDialog = new UtilDialog();

    @FXML
    private void actionCB() {
        if (cbComboBox.getSelectionModel().getSelectedItem().equals("Empresa")) {
            btnBuscar.setVisible(true);
            txtBuscar.setVisible(true);
            data.setVisible(false);
            cbStatusAgenda.setVisible(false);
        } else if (cbComboBox.getSelectionModel().getSelectedItem().equals("Data")) {
            btnBuscar.setVisible(false);
            txtBuscar.setVisible(false);
            data.setVisible(true);
            cbStatusAgenda.setVisible(false);
        } else if (cbComboBox.getSelectionModel().getSelectedItem().equals("Status da Agenda")) {
            btnBuscar.setVisible(false);
            txtBuscar.setVisible(false);
            data.setVisible(false);
            cbStatusAgenda.setVisible(true);
        } else if (cbComboBox.getSelectionModel().getSelectedItem().equals("TODOS")) {
            btnBuscar.setVisible(false);
            txtBuscar.setVisible(true);
            data.setVisible(false);
            cbStatusAgenda.setVisible(false);
        }
    }

    @FXML
    private void actionCBStatusAgenda() {
        listaAgendas.clear();
        String txt = Util.removerAcentuacaoServico(cbStatusAgenda.getSelectionModel().getSelectedItem().toString()).toLowerCase();
        if (cbComboBox.getSelectionModel().getSelectedItem().equals("Status da Agenda")) {
            AgendaService as = new AgendaService();
            for (Agenda agenda : as.findOrderBy("cod")) {
                if (agenda.getStatusAgenda().toLowerCase().contains(txt)) {
                    listaAgendas.add(agenda);
                }
            }
            JPA.em(false).close();
        }
        tvAgenda.getItems()
                .setAll(listaAgendas);
    }

    @FXML
    private void onKeyPressdTxtBuscar(KeyEvent e) {
        listaAgendas.clear();
        AgendaService as = new AgendaService();
        for (Agenda agenda : as.findOrderBy("cod")) {
            if (cbComboBox.getSelectionModel().getSelectedItem().equals("Empresa")) {
                if (agenda.getIdEmpresa().getDescricao().toLowerCase().contains(txtBuscar.getText().toLowerCase() + e.getText().toLowerCase())) {
                    listaAgendas.add(agenda);
                }
            } else if (cbComboBox.getSelectionModel().getSelectedItem().equals("TODOS")) {
                String todos = agenda.getId() + agenda.getIdEmpresa().getDescricao()
                        + agenda.getResponsavel() + agenda.getDiaSemana()
                        + agenda.getStatusAgenda() + agenda.getStatusBoleto()
                        + agenda.getTipo() + agenda.getDataInicial() + agenda.getDataFinal()
                        + agenda.getDataVencimentoBoleto();

                if (todos.toLowerCase().contains(txtBuscar.getText().toLowerCase().trim() + e.getText().toLowerCase())) {
                    listaAgendas.add(agenda);
                }
            }
        }
        tvAgenda.setItems(listaAgendas);
        JPA.em(false).close();
    }

    @FXML
    private void actionBtnBuscarEmpresa() {
//        TabelaEmpresaController.isTabelaAgenda = true;
        stage.close();
        RunAnotherApp.runAnotherApp(TabelaEmpresaController.class
        );
        StaticBoolean.setTabelaAgenda(
                true);
    }

    public void setCampos() {
        txtBuscar.setText(empresaEncontrada.getDescricao());
        txtBuscar.requestFocus();

        listaAgendas.clear();
        for (Agenda agenda : StaticLista.getListaGlobalAgenda()) {
            if (agenda.getIdEmpresa().getDescricao().toLowerCase().contains(txtBuscar.getText().toLowerCase())) {
                listaAgendas.add(agenda);
            }
        }
        tvAgenda.setItems(listaAgendas);
    }

    private void abrirTelaAgendamento() {
        StaticObject.setAgendaEncontrada(tvAgenda.getSelectionModel().getSelectedItem());
        RunAnotherApp.runAnotherApp(AgendarController.class
        );
        if (StaticObject.getEmpresaEncontrada()
                == null) {
            System.out.println("empresa nulo " + StaticObject.getEmpresaEncontrada());
        }

        stage.close();
    }

    private void setReagendamento() {
        StaticObject.setAgendaEncontrada(tvAgenda.getSelectionModel().getSelectedItem());
        RunAnotherApp.runAnotherApp(MotivoReagendamentoController.class
        );
        stage.close();
    }

    private void padrao1() {
        if (StaticBoolean.isReagendamento() || StaticBoolean.isCancelamento()) {
            setReagendamento();
        } else {
            abrirTelaAgendamento();
        }
    }

    private void padrao2() {
        if (StaticBoolean.isReagendamento()) {
            utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.TabelaAgendaErroStatusAgenda.getMensagem());
        } else {
            abrirTelaAgendamento();
//            AgendarController.bloquearCampos();
        }
    }

    @FXML
    private void onMouseClickedTabelaAgenda(MouseEvent event) {
        if (event.getClickCount() == 2) {
            switch (tvAgenda.getSelectionModel().getSelectedItem().getStatusAgenda()) {
                case "Reagendada":
                    utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.TabelaAgendaErroReagendado.getMensagem());
                    break;
                case "Pendente":
                    padrao1();
                    break;
                case "Pendente WEB":
                    padrao1();
                    break;
                case "Data Agendada":
                    padrao1();
                    break;
                case "Concluido":
                    padrao2();
                    break;
                case "Nao compareceu":
                    padrao2();
                    break;
                case "Cancelado":
                    utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.TabelaAgendaErroCancelado.getMensagem());
                    break;
            }
        }
    }

    // getter and setter
    public ComboBox getCbComboBox() {
        return cbComboBox;
    }

    public void setCbComboBox(ComboBox cb) {
        cbComboBox = cb;
    }

}
