package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RelatorioFinanceiroController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilDialog = new UtilDialog();
            lblTitulo.setText(titulo);
            mainRelatorioFinanceiro.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            preencherListaMes();
            if (tipo.equals("Mensal")) {
                vb1.getChildren().remove(dataInicial);
                vb2.getChildren().remove(dataFinal);
                lblDataInicial.setVisible(false);
                lblDataFinal.setVisible(false);
                combo.setVisible(true);
                lblTexto.setText("Mês");
                Mes m = Mes.Janeiro;
                stringFiltro.addAll(m.name(), m.Fevereiro.name(), m.Marco.name(), m.Abril.name(), m.Maio.name(),
                        m.Junho.name(), m.Julho.name(), m.Agosto.name(), m.Setembro.name(), m.Outubro.name(),
                        m.Novembro.name(), m.Dezembro.name());
                combo.setEditable(false);
                combo.setItems(stringFiltro);
            } else if (tipo.equals("Anual")) {
                lblDataInicial.setVisible(false);
                lblDataFinal.setVisible(false);
                combo.setVisible(true);
                lblTexto.setText("Anual");
                stringFiltro.addAll("2010", "2011", "2012", "2013", "2014", "2015", "2016");
                combo.setEditable(true);
                combo.setItems(stringFiltro);
            } else if (tipo.equals("Período")) {
                combo.setVisible(false);
                lblTexto.setText("Período");
                lblDataInicial.setVisible(true);
                lblDataFinal.setVisible(true);
                vb1.getChildren().add(dataInicial);
                vb2.getChildren().add(dataFinal);
            }
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar relatorio financeiro", e, "Exception:");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainRelatorioFinanceiro = FXMLLoader.load(RelatorioController.class.getResource(EnumCaminho.RelatorioFinanceiro.getCaminho()));
            Scene scene = new Scene(mainRelatorioFinanceiro);
            scene.getStylesheets().add(EnumCaminho.CSS.getCaminho());
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start relatorio financeiro", e, "Exception:");
        }
    }

    private static Stage stage;
    @FXML
    private VBox mainRelatorioFinanceiro;
    @FXML
    private Label lblTexto;
    @FXML
    private Label lblDataInicial;
    @FXML
    private Label lblDataFinal;
    @FXML
    private ComboBox combo;
    @FXML
    private HBox vb1;
    @FXML
    private HBox vb2;
    @FXML
    private Label lblTitulo;

    private static String tipo;
//    private static FXCalendar dataInicial;
//    private static FXCalendar dataFinal;
    private static DatePicker dataInicial;
    private static DatePicker dataFinal;
    private ObservableList stringFiltro = FXCollections.observableArrayList();
    private ObservableList<Mes> listaMes = FXCollections.observableArrayList();
    private static String titulo;
    private UtilDialog utilDialog;
    private UtilConverter utilConverter;

    public static void setCampos(String tipo, String titulo) {
        dataInicial = new DatePicker();
        dataFinal = new DatePicker();
        RelatorioFinanceiroController.tipo = tipo;
        RelatorioFinanceiroController.titulo = titulo;
    }

    @FXML
    private void actionBtnOK() {
        if (lblTexto.getText().equals("Mês")) {
            System.out.println("mes");
            Mes m = Mes.Janeiro;
            for (Mes n : listaMes) {
                if (combo.getValue().equals(n.name())) {
                    m = n;
                    break;
                }
            }
            int ano, mes, dia;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, m.getValor()); //Definindo o m�s atual   
            ano = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH) - 1;
            dia = 1;
            calendar.set(ano, mes, dia);
            dataInicial.setValue(utilConverter.converterUtilDateToLocalDate(calendar.getTime()));
            dia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(ano, mes, dia);
            dataFinal.setValue(utilConverter.converterUtilDateToLocalDate(calendar.getTime()));
            RelatorioController.setCampos(stage, dataInicial, dataFinal, 0);
        } else if (lblTexto.getText().equals("Anual")) {
            System.out.println("ano");
            int ano = 0, mes, dia;
            boolean erro = false;
            try {
                ano = Integer.parseInt(combo.getSelectionModel().getSelectedItem().toString());
                if (ano < 1990) {
                    utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.RelatorioErroValorIncorreto.getMensagem());
                    erro = true;
                }
            } catch (Exception e) {
                erro = true;
                utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.RelatorioErroValorIncorreto.getMensagem());
            }
            try {
                if (!erro) {
                    Calendar calendar = Calendar.getInstance();
                    dia = 1;
                    mes = 0;
                    calendar.set(ano, mes, dia);
                    dataInicial.setValue(utilConverter.converterUtilDateToLocalDate(calendar.getTime()));
                    dia = 31;
                    mes = 11;
                    calendar.set(ano, mes, dia);
                    dataFinal.setValue(utilConverter.converterUtilDateToLocalDate(calendar.getTime()));
                    RelatorioController.setCampos(stage, dataInicial, dataFinal, Integer.parseInt(combo.getSelectionModel().getSelectedItem() + ""));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (lblTexto.getText().equals("Período")) {
            System.out.println("periodo");
            RelatorioController.setCampos(stage, dataInicial, dataFinal, 0);

        }
    }

    private void preencherListaMes() {
        Mes m = Mes.Janeiro;
        listaMes.addAll(m, m.Fevereiro, m.Marco, m.Abril, m.Maio, m.Junho, m.Julho, m.Agosto, m.Setembro, m.Outubro, m.Novembro, m.Dezembro);
    }

    @FXML
    private void actionBtnCancelar() {
        stage.close();
    }

    private void limpar() {
    }

    public enum Mes {

        Janeiro(1), Fevereiro(2), Marco(3), Abril(4), Maio(5), Junho(6), Julho(7), Agosto(8), Setembro(9), Outubro(10), Novembro(11), Dezembro(12);

        private final int valor;

        Mes(int valorOpcao) {
            valor = valorOpcao;
        }

        public int getValor() {
            return valor;
        }

    }

}
