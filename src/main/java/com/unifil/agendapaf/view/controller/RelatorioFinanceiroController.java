package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RelatorioFinanceiroController {

    @FXML
    public void initialize() {
        mensagem = new Mensagem(stage);
    }

    private Stage stage;
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

    private String tipo;
    private DatePicker dataInicial;
    private DatePicker dataFinal;
    private String titulo;
    private ObservableList stringFiltro = FXCollections.observableArrayList();
    private ObservableList<Mes> listaMes = FXCollections.observableArrayList();
    private Mensagem mensagem;

    public void setCampos(String titulo, String tipo) {
        dataInicial = new DatePicker();
        dataFinal = new DatePicker();
        this.tipo = tipo;
        this.titulo = titulo;
        preencherListaMes();
        verificarTipo();
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
            dataInicial.setValue(UtilConverter.converterUtilDateToLocalDate(calendar.getTime()));
            dia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(ano, mes, dia);
            dataFinal.setValue(UtilConverter.converterUtilDateToLocalDate(calendar.getTime()));
            SceneManager.getInstance().getRelatorioController().setCampos(stage, dataInicial, dataFinal, 0);
//            RelatorioController.setCampos(stage, dataInicial, dataFinal, 0);
        } else if (lblTexto.getText().equals("Anual")) {
            int ano = 0, mes, dia;
            boolean erro = false;
            try {
                ano = Integer.parseInt(combo.getSelectionModel().getSelectedItem().toString());
                if (ano < 1990) {
                    mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.RelatorioErroValorIncorreto.getMensagem());
                    erro = true;
                }
            } catch (Exception e) {
                erro = true;
                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.RelatorioErroValorIncorreto.getMensagem());
            }
            try {
                if (!erro) {
                    Calendar calendar = Calendar.getInstance();
                    dia = 1;
                    mes = 0;
                    calendar.set(ano, mes, dia);
                    dataInicial.setValue(UtilConverter.converterUtilDateToLocalDate(calendar.getTime()));
                    dia = 31;
                    mes = 11;
                    calendar.set(ano, mes, dia);
                    dataFinal.setValue(UtilConverter.converterUtilDateToLocalDate(calendar.getTime()));
                    System.out.println("ano " + ano);
                    SceneManager.getInstance().getRelatorioController().setCampos(stage, dataInicial, dataFinal, Integer.parseInt(combo.getSelectionModel().getSelectedItem() + ""));
//                    RelatorioController.setCampos(stage, dataInicial, dataFinal, Integer.parseInt(combo.getSelectionModel().getSelectedItem() + ""));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (lblTexto.getText().equals("Período")) {
            System.out.println("periodo");
            SceneManager.getInstance().getRelatorioController().setCampos(stage, dataInicial, dataFinal, 0);
//            RelatorioController.setCampos(stage, dataInicial, dataFinal, 0);

        }
    }

    private void preencherListaMes() {
        Mes m = Mes.Janeiro;
        listaMes.addAll(m, m.Fevereiro, m.Marco, m.Abril, m.Maio, m.Junho, m.Julho, m.Agosto, m.Setembro, m.Outubro, m.Novembro, m.Dezembro);
    }

    public void verificarTipo() {
        lblTitulo.setText(titulo);
        System.out.println("TIPO " + tipo);
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
    }

    @FXML
    private void actionBtnCancelar() {
        stage.close();
    }

    private void limpar() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainRelatorioFinanceiro(VBox mainRelatorioFinanceiro) {
        this.mainRelatorioFinanceiro = mainRelatorioFinanceiro;
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
