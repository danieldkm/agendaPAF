package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.DateChooserSkin;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.EmpresasHomologadas;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.model.Historico;
import com.unifil.agendapaf.service.AgendaService;
import com.unifil.agendapaf.service.EmpresasHomologadasService;
import com.unifil.agendapaf.service.HistoricoService;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.statics.StaticCalendar;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.statics.StaticObject;
import com.unifil.agendapaf.statics.StaticString;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.view.util.enums.EnumStatus;
import com.unifil.agendapaf.util.RunAnotherApp;
import com.unifil.agendapaf.util.Util;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumServico;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class AgendarController extends FXMLController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilDialog = new UtilDialog();
            mainAgendamento.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ESCAPE) {
                        stage.close();
                    }
                }
            });
            lblAgenda.setText("Agendamento");
            setOnActionsDts();
            System.out.println("StaticDates.getDataSelecionada() " + StaticCalendar.getDataSelecionada());
            if (StaticCalendar.getDataSelecionada() != null) {
                dtInicial.setValue(StaticCalendar.getDataSelecionada());
                setSomarUmDiaDtFinal();
                actionBtnAtualizarDiaSemana();
            }
            cbTipo.getSelectionModel().selectFirst();
            cbStatusAgenda.getSelectionModel().selectFirst();
            cbStatusBoleto.getSelectionModel().selectFirst();
            dtVencimentoBoleto.setVisible(false);
            lblDtVencimento.setVisible(false);

            // antes de fechar o stage faça ==== close stage before
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    dtInicial.setDisable(false);
                    dtFinal.setDisable(false);
                    empresaEncontrada = null;
                    agendaEncontrada = null;
                    motivoReagendamento = null;
                    StaticBoolean.setAgenda(false);
                }
            });

            if (StaticBoolean.isReagendamento()) {
                isReagendamento = StaticBoolean.isReagendamento();
                StaticBoolean.setReagendamento(false);
                motivoReagendamento = StaticString.getTxtMotivoReagendamento();
                StaticString.setTxtMotivoReagendamento("");
                bloquearCamposEmpresa();
                btnCancelar.setDisable(true);
                isUpdate = true;
            } else if (StaticBoolean.isUpdate()) {
                isUpdate = StaticBoolean.isUpdate();
                btnCancelar.setDisable(true);
                StaticBoolean.setUpdate(false);
            } else if (StaticBoolean.isCancelamento()) {
                isCancelamento = StaticBoolean.isCancelamento();
                isUpdate = true;
                StaticBoolean.setCancelamento(false);
                motivoReagendamento = StaticString.getTxtMotivoReagendamento();
                btnCancelar.setDisable(true);
                StaticString.setTxtMotivoReagendamento("");
            }
            if (StaticObject.getAgendaEncontrada() != null) {
                agendaEncontrada = StaticObject.getAgendaEncontrada();
                StaticObject.setAgendaEncontrada(null);
                setCampos();
                bloquearCampos();
            } else if (StaticObject.getEmpresaEncontrada() != null) {
                empresaEncontrada = StaticObject.getEmpresaEncontrada();
                StaticObject.setEmpresaEncontrada(null);
                txtEmpresa.setText(empresaEncontrada.getDescricao());
                txtResponsavel.setText(empresaEncontrada.getNomeContato());
            }
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar agenda", e, "Exception:");
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainAgendamento = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.Agenda.getCaminho()));
            Scene scene = new Scene(mainAgendamento);
//            scene.getStylesheets().clear();
//            String css = "/css/buttonStyle.css";
//            System.out.println("Loading...css Style" + css);
//            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("Agendamento");
//            stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start da agenda", e, "Exception:");
        }
    }

    @FXML
    private TextField txtEmpresa;
    @FXML
    private TextField txtResponsavel;
    @FXML
    private TextField txtSemana;
    @FXML
    private ComboBox cbTipo;
    @FXML
    private ComboBox cbStatusBoleto;
    @FXML
    private ComboBox cbStatusAgenda;
    @FXML
    private Button btnBuscar;
    @FXML
    private BorderPane mainAgendamento;
    @FXML
    private Label lblAgenda;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblDtVencimento;
    @FXML
    private DatePicker dtInicial;
    @FXML
    private DatePicker dtFinal;
    @FXML
    private DatePicker dtVencimentoBoleto;

    private Empresa empresaEncontrada = null;
    private Agenda agendaEncontrada = null;
    private boolean isReagendamento = false;
    private boolean isCancelamento = false;
    private Agenda a;
    private String motivoReagendamento = "";
    private static Stage stage;
//    private ObservableList<Agenda> listaTemp = FXCollections.observableArrayList();
//    private SimpleDateFormat diaSemana2 = new SimpleDateFormat("EEEE");
//    private SimpleDateFormat dtFormatada = new SimpleDateFormat("yyyy-MM-dd");
    private String alteracao = "";
    private Calendar cale1 = new GregorianCalendar();
    private Calendar cale2 = new GregorianCalendar();
    private SimpleDateFormat diaSemana = new SimpleDateFormat("EEEE");
    private boolean isUpdate = false;
    private UtilDialog utilDialog;

    @FXML
    private void actionBtnSalvar() {
        actionBtnAtualizarDiaSemana();
        if (validarCampos()) {
            try {
                salvarOuAtualizar(isUpdate);
                if (isUpdate) {
                    validarCamposAoAtualizar(agendaEncontrada);
                }
                if (gravarHistorico() && seConcluido()) {
                    if (isUpdate) {
                        utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
                    } else {
                        utilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
                    }
                }
                if (cbStatusAgenda.getValue().equals(EnumStatus.Concluido.getStatus())) {
                    Optional<ButtonType> result = utilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.AgendaDesejaCadastrarFinanceiro.getMensagem());
                    if (result.get() == ButtonType.OK) {
                        iniciarCadastroFinanceiro();
                    }
                }
                AgendaService as = new AgendaService();
                StaticLista.setListaGlobalAgenda(as.findOrderBy("dataInicial"));
                StaticLista.setListaGlobalHistorico(Controller.getHistoricos());
                stage.close();
                DateChooserSkin.getMonthBack().arm();
                DateChooserSkin.getMonthBack().fire();
                DateChooserSkin.getMonthForward().arm();
                DateChooserSkin.getMonthForward().fire();
                resetarCampos();
            } catch (Exception e) {
                e.printStackTrace();
                utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e, "Exception:");
            }
        }
    }

    @FXML
    private void actionBtnCancelar() {
        resetarCampos();
    }

    @FXML
    private void actionBtnBuscar() {
        StaticBoolean.setAgenda(true);
        RunAnotherApp.runAnotherApp(TabelaEmpresaController.class);
        stage.close();
    }

    @FXML
    private void actionCBTipo() {
        if (cbTipo.getSelectionModel().getSelectedItem().equals(EnumServico.PreAvaliacao.getServico()) || cbTipo.getSelectionModel().getSelectedItem().equals(EnumServico.PreAvaliacaoIntinerante.getServico()) || cbTipo.getSelectionModel().getSelectedItem().equals(EnumServico.PreAvaliacaoRemoto.getServico())) {
            dtInicial.setValue(StaticCalendar.getDataSelecionada());
            dtFinal.setValue(StaticCalendar.getDataSelecionada());
        } else {
            if (StaticCalendar.getDataSelecionada() == null) {
                dtInicial.setValue(LocalDate.now());
                actionBtnAtualizarDiaSemana();
            }
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue()));
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dtFinal.setValue(UtilConverter.converterUtilDateToLocalDate(calendar.getTime()));
        }
    }

    @FXML
    private void actionBtnAtualizarDiaSemana() {
        try {
            txtSemana.setText(diaSemana.format(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue())));
        } catch (RuntimeException e) {
            e.printStackTrace();
            txtSemana.setText("");
        }
    }

    @FXML
    private void actionCbStatusBoleto() {
        if (cbStatusBoleto.getSelectionModel().getSelectedItem().equals(EnumStatus.Enviado.getStatus())) {
            dtVencimentoBoleto.setVisible(true);
            lblDtVencimento.setVisible(true);
        } else {
            dtVencimentoBoleto.setVisible(false);
            lblDtVencimento.setVisible(false);
        }
    }

    private void salvarOuAtualizar(boolean atualizar) {
        try {
            a = new Agenda();
            if (atualizar) {
                a.setId(agendaEncontrada.getId());
            }
            a.setIdEmpresa(empresaEncontrada);
            a.setResponsavel(txtResponsavel.getText());
            a.setTipo(Util.removerAcentuacaoServico(cbTipo.getValue().toString()));
            try {
                a.setDataFinal(dtFinal.getValue());
                a.setDataInicial(dtInicial.getValue());
                a.setDiaSemana(diaSemana.format(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue())));
            } catch (NullPointerException e) {
                a.setDataFinal(null);
                a.setDataInicial(null);
                a.setDiaSemana(null);
            }
            a.setStatusBoleto(Util.removerAcentuacaoServico(cbStatusBoleto.getValue().toString()));
            a.setStatusAgenda(Util.removerAcentuacaoServico(cbStatusAgenda.getValue().toString()));
            if (cbStatusBoleto.getSelectionModel().getSelectedItem().equals(EnumStatus.Enviado.getStatus())) {
                a.setDataVencimentoBoleto(dtVencimentoBoleto.getValue());
            } else if (cbStatusBoleto.getSelectionModel().getSelectedItem().equals(EnumStatus.Pago.getStatus())) {
                a.setDataVencimentoBoleto(agendaEncontrada.getDataVencimentoBoleto());
            } else {
                a.setDataVencimentoBoleto(null);
            }
            if (atualizar) {
                if (isCancelamento) {
                    a.setStatusAgenda("Cancelado");
                }
                AgendaService as = new AgendaService();
                as.editar(a);
                JPA.em(false).close();
            } else {
                AgendaService as = new AgendaService();
                as.salvar(a);
                JPA.em(false).close();
            }
        } catch (Exception ex) {
            Logger.getLogger(AgendarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private
            boolean seConcluido() {
        try {
            if (cbStatusAgenda.getValue().equals(EnumStatus.Concluido.getStatus())
                    && (cbTipo.getValue().equals(EnumServico.Avaliacao.getServico())
                    || cbTipo.getValue().equals(EnumServico.AvaliacaoIntinerante.getServico()))) {
                EmpresasHomologadas eh = new EmpresasHomologadas();

                eh.setIdEmpresa(empresaEncontrada);
                eh.setDataHomologada(dtInicial.getValue());
                Calendar cal = new GregorianCalendar();

                cal.setTime(UtilConverter.converterLocalDateToSqlDate(eh.getDataHomologada()));
                cal.add(Calendar.MONTH,
                        10);
                eh.setDataAviso(UtilConverter.converterUtilDateToLocalDate(cal.getTime()));
                dtFinal.setValue(UtilConverter.converterUtilDateToLocalDate(cal.getTime()));
                eh.setEmail(empresaEncontrada.getEmail());
                eh.setVisualizado(
                        "NAO");
                EmpresasHomologadasService ehs = new EmpresasHomologadasService();
                ehs.salvar(eh);
                JPA.em(false).close();
                StaticLista.setListaGlobalEmpresasHomologadas(Controller.getEmpresasHomologadas());
            }

        } catch (Exception ex) {
            Logger.getLogger(AgendarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private boolean gravarHistorico() {
        try {
            LocalDate dtReagendada = null;
            Agenda agenda = null; //= listaTemp.get(listaTemp.size() - 1);
            if (agendaEncontrada != null) {
                agenda = agendaEncontrada;
                dtReagendada = agendaEncontrada.getDataInicial();
            } else {
                AgendaService as = new AgendaService();
                agenda = as.findLast();
                JPA.em(false).close();
            }
//            if (isReagendamento) {
//                agenda.setStatusAgenda("Reagendada");
//            } else if (isCancelamento) {
//                agenda.setStatusAgenda("Cancelado");
//            } else {
//                System.out.println("cbStatusAgenda.getValue() " + cbStatusAgenda.getValue());
//                agenda.setStatusAgenda(util.tirarAcentuacaoServico(cbStatusAgenda.getValue().toString()));
//            }
            Historico h = new Historico();

            h.setIdAgenda(agenda);
            h.setIdEmpresa(empresaEncontrada);
            h.setDataInicial(dtInicial.getValue());
            h.setDataFinal(dtFinal.getValue());
            h.setMotivo(motivoReagendamento);
            if (isReagendamento) {
//                "Reagendada"
                h.setStatus(EnumStatus.Reagendada.getStatus());
                h.setDataReagendada(dtReagendada);
            } else {
                h.setStatus(agenda.getStatusAgenda());
            }
            h.setDataAlteracao(LocalDate.now());
            h.setIdUsuario(StaticObject.getUsuarioLogado());

            HistoricoService hs = new HistoricoService();
            hs.salvar(h);
            JPA.em(false).close();
//            StaticDao.getHistoricoService().salvar(h);
        } catch (Exception ex) {
            Logger.getLogger(AgendarController.class
                    .getName()).log(Level.SEVERE, null, ex);
            utilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(),
                    EnumMensagem.AgendaErroSalvarHistoricoAgenda.getMensagem(), ex, "Exception:");
            System.out.println(
                    "Erro: ao salvar no banco. Metodo: gravarHistorico. classe: AgendarController");
            return false;
        }
        return true;
    }

    /**
     * Caso o usuário selecionar a data inicial verificar e somar um dia a mais
     * para a data final automaticamente caso o tipo for avaliação
     */
    private void setSomarUmDiaDtFinal() {
        if (dtInicial.getValue() == null) {
            dtFinal.setValue(null);
        } else {
            if (cbTipo.getSelectionModel().getSelectedItem() != null) {
                if (cbTipo.getSelectionModel().getSelectedItem().equals(EnumServico.Avaliacao.getServico()) || cbTipo.getSelectionModel().getSelectedItem().equals(EnumServico.AvaliacaoIntinerante.getServico())) {
                    Calendar ca3 = new GregorianCalendar();
                    ca3.setTime(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue()));
                    ca3.set(Calendar.HOUR_OF_DAY, 0);
                    ca3.set(Calendar.MINUTE, 0);
                    ca3.set(Calendar.SECOND, 0);
                    ca3.set(Calendar.MILLISECOND, 0);
                    ca3.add(Calendar.DAY_OF_MONTH, 1);
                    dtFinal.setValue(UtilConverter.converterUtilDateToLocalDate(ca3.getTime()));
                } else {
                    dtFinal.setValue(dtInicial.getValue());
                }
            }
        }
    }

    private void resetarCampos() {
        txtEmpresa.setText("");
        txtResponsavel.setText("");
        cbTipo.getSelectionModel().selectFirst();
        cbStatusBoleto.getSelectionModel().selectFirst();
        cbStatusAgenda.getSelectionModel().selectFirst();
        txtSemana.setText(null);
        agendaEncontrada = null;
        empresaEncontrada = null;
        alteracao = "";
        StaticBoolean.setCancelamento(false);
        isReagendamento = false;
        isUpdate = false;
    }

    private boolean validarCampos() {
        ValidationSupport validationSupport = new ValidationSupport();
        boolean ok = true;
        if (txtEmpresa.getText().equals("")) {
            validationSupport.registerValidator(txtEmpresa, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            txtEmpresa.requestFocus();
            ok = false;
        }
        if (txtResponsavel.getText().equals("")) {
            validationSupport.registerValidator(txtResponsavel, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            txtResponsavel.requestFocus();
            ok = false;
        }
        if (cbTipo.getValue().equals(EnumServico.Avaliacao.getServico()) || cbTipo.getValue().equals(EnumServico.AvaliacaoIntinerante.getServico())) {
            if (txtSemana.getText().equals("Sexta-feira")) {
                if (dtInicial.getValue().compareTo(dtFinal.getValue()) == -1
                        || dtInicial.getValue().compareTo(dtFinal.getValue()) == -2) {
                    validationSupport.registerValidator(dtInicial, false, (Control c, LocalDate newValue)
                            -> ValidationResult.fromWarningIf(dtInicial, "Informe a data", !LocalDate.now().equals(newValue)));
                    dtInicial.requestFocus();
                    utilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                            EnumMensagem.Aviso.getMensagem() + "\n"
                            + "data final não pode ser de sabado ou domingo");
                    ok = false;
                }
            }
            if (dtInicial.getValue().equals(dtFinal.getValue())) {
                validationSupport.registerValidator(dtInicial, false, (Control c, LocalDate newValue)
                        -> ValidationResult.fromWarningIf(dtInicial, "Informe a data", !LocalDate.now().equals(newValue)));
                dtInicial.requestFocus();
                utilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                        EnumMensagem.Aviso.getMensagem() + "\n"
                        + "data final no mesmo dia da data inicial!!!");
                ok = false;
            } else {
                // aki nesse trecho do codigo verifico se a data inicial e final 
                // tem diferenteça de 2 dias ou mais se houver não deixa cadastrar
                Calendar ca1 = new GregorianCalendar();
                ca1.setTime(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue()));
                ca1.set(Calendar.HOUR_OF_DAY, 0);
                ca1.set(Calendar.MINUTE, 0);
                ca1.set(Calendar.SECOND, 0);
                ca1.set(Calendar.MILLISECOND, 0);

                Calendar ca2 = new GregorianCalendar();
                ca2.setTime(UtilConverter.converterLocalDateToUtilDate(dtFinal.getValue()));
                ca2.set(Calendar.HOUR_OF_DAY, 0);
                ca2.set(Calendar.MINUTE, 0);
                ca2.set(Calendar.SECOND, 0);
                ca2.set(Calendar.MILLISECOND, 0);

                //varial para verificar se o proximo dia do dia ca1 é 1
                //se for deixar cadastrar o ultimo com o 1 dia do mes seguinte!
                Calendar ca3 = new GregorianCalendar();
                ca3.setTime(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue()));
                ca3.set(Calendar.HOUR_OF_DAY, 0);
                ca3.set(Calendar.MINUTE, 0);
                ca3.set(Calendar.SECOND, 0);
                ca3.set(Calendar.MILLISECOND, 0);

                int d1 = ca1.get(Calendar.DAY_OF_MONTH);
                int d2 = ca2.get(Calendar.DAY_OF_MONTH);
                ca3.set(Calendar.DAY_OF_MONTH, d1 + 1);
                int d3 = ca3.get(Calendar.DAY_OF_MONTH);
                if (d3 != 1) {
                    if ((d2 - d1) > 1 || (d2 - d1) < 1) {
//                        validate(dtFinal);
                        validationSupport.registerValidator(dtFinal, false, (Control c, LocalDate newValue)
                                -> ValidationResult.fromWarningIf(dtFinal, "Informe a data", !LocalDate.now().equals(newValue)));
                        dtFinal.requestFocus();
                        utilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                                EnumMensagem.Aviso.getMensagem() + "\n"
                                + "com data final em dois dias a mais de diferença da data inicial!!!");
                        ok = false;
                    }
                }
            }
        }
        if (contemFeriado(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue()))) {
            utilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.Aviso.getMensagem() + "nos feriados");
            ok = false;
        }
        if (cbTipo.getValue().equals(EnumServico.HoraAdicional.getServico()) || cbTipo.getValue().equals(EnumServico.PreAvaliacao.getServico()) || cbTipo.getValue().equals(EnumServico.PreAvaliacaoIntinerante.getServico()) || cbTipo.getValue().equals(EnumServico.PreAvaliacaoRemoto.getServico())) {
            if (!dtInicial.getValue().equals(dtFinal.getValue())) {
                validationSupport.registerValidator(dtFinal, false, (Control c, LocalDate newValue)
                        -> ValidationResult.fromWarningIf(dtFinal, "Informe a data", !LocalDate.now().equals(newValue)));
                dtFinal.requestFocus();
                utilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                        EnumMensagem.Aviso.getMensagem()
                        + "\n com data final diferente da data inicial");
                return false;
            }
        }
        if (dtInicial.getValue() == null) {
            if (cbStatusAgenda.getValue().equals(EnumStatus.Pendente.getStatus())) {
                ok = true;
            } else {
                validationSupport.registerValidator(cbStatusAgenda, Validator.createEmptyValidator("ComboBox Selection required"));
                cbStatusAgenda.requestFocus();
                utilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                        EnumMensagem.Aviso.getMensagem() + "\n"
                        + "Status inválido, para criar o agendamento sem data,"
                        + "\ntroque o status do agendamento para Pendente");
                ok = false;
            }
        }
        if (!cbStatusAgenda.getSelectionModel().getSelectedItem().equals(EnumStatus.Pendente.getStatus())) {
            if (dtInicial.getValue() == null || dtFinal.getValue() == null) {
                validationSupport.registerValidator(dtFinal, false, (Control c, LocalDate newValue)
                        -> ValidationResult.fromWarningIf(dtFinal, "Informe a data", !LocalDate.now().equals(newValue)));
                dtInicial.requestFocus();
                validationSupport.registerValidator(dtFinal, false, (Control c, LocalDate newValue)
                        -> ValidationResult.fromWarningIf(dtFinal, "Informe a data", !LocalDate.now().equals(newValue)));
                utilDialog.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.Aviso.getMensagem() + "\n" + "Prencher a data inicial e/ou final");
                ok = false;
            }
        }
        return ok;
    }

    private void setOnActionsDts() {
        dtInicial.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (dtInicial.getValue() != null) {
                    if (!diaSemana.format(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue())).equals(txtSemana.getText())) {
                        txtSemana.setText(diaSemana.format(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue())));
                    }
                }
            }
        });

        dtInicial.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                try {
                    setSomarUmDiaDtFinal();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Exption: setOnKeyPressed, class AgendarController, metodo initialize - dtInici");
                }
            }
        });

        dtInicial.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                try {
                    setSomarUmDiaDtFinal();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Exption: setOnMouseExited, class AgendarController, metodo initialize - dtInicial");
                }
            }
        });

        dtFinal.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                try {
                    setSomarUmDiaDtFinal();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Exption: setOnMouseEntered, class AgendarController, metodo initialize - dtFinal");
                }
            }
        });

        dtFinal.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                setSomarUmDiaDtFinal();
            }
        });
    }

    public void setCampos() {
        try {
            lblAgenda.setText("Atualizar Agendamento");
            if (agendaEncontrada.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.Concluido.getStatus()))
                    || agendaEncontrada.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.NaoCompareceu.getStatus()))
                    || agendaEncontrada.getStatusAgenda().equals(EnumStatus.DataAgendada.getStatus())) {
                dtInicial.setDisable(true);
                dtFinal.setDisable(true);
            }
            if (isReagendamento) {
                lblAgenda.setText("Reagendamento");
                dtInicial.setDisable(false);
                dtFinal.setDisable(false);
//                cbStatusAgenda.setDisable(true);
            } else if (isCancelamento) {
                lblAgenda.setText("Cancelamento");
                dtInicial.setDisable(true);
                dtFinal.setDisable(true);
                cbStatusAgenda.setDisable(true);
                cbTipo.setDisable(true);
                cbStatusBoleto.setDisable(true);
            }
            ObservableList<Empresa> empresas = Controller.getEmpresas();
            for (Empresa empresa : empresas) {
                if (empresa.getId() == agendaEncontrada.getIdEmpresa().getId()) {
                    txtEmpresa.setText(empresa.getDescricao());
                    empresaEncontrada = empresa;
                }
            }
            txtResponsavel.setText(agendaEncontrada.getResponsavel());
            txtSemana.setText(agendaEncontrada.getDiaSemana());
            String aux = agendaEncontrada.getStatusAgenda();
            aux = Util.porAcentuacaoServico(aux);
            while (!cbStatusAgenda.getSelectionModel().getSelectedItem().toString().toLowerCase().equals(aux)) {
                if (aux.equals(EnumStatus.Reagendada.getStatus()) || aux.equals(EnumStatus.Cancelado.getStatus())
                        || aux.equals(EnumStatus.PendenteWeb.getStatus())) {
                    cbStatusAgenda.getSelectionModel().selectLast();
                    break;
                } else {
                    cbStatusAgenda.getSelectionModel().selectNext();
                }
//                }
            }
            cbStatusBoleto.getSelectionModel().selectFirst();
            String boleto = Util.porAcentuacaoServico(agendaEncontrada.getStatusBoleto());
            if (!boleto.equals(EnumStatus.NaoEnviado.getStatus())) {
                dtVencimentoBoleto.setVisible(true);
                dtVencimentoBoleto.setValue(agendaEncontrada.getDataVencimentoBoleto());
            }
            while (!cbStatusBoleto.getSelectionModel().getSelectedItem().toString().equals(boleto)) {
                cbStatusBoleto.getSelectionModel().selectNext();
            }
            String tipo = Util.porAcentuacaoServico(agendaEncontrada.getTipo());
            while (!cbTipo.getSelectionModel().getSelectedItem().equals(tipo)) {
                cbTipo.getSelectionModel().selectNext();
            }
            dtInicial.setValue(agendaEncontrada.getDataInicial());
            dtFinal.setValue(agendaEncontrada.getDataFinal());
            if (agendaEncontrada.getDataVencimentoBoleto() != null) {
                dtVencimentoBoleto.setValue(agendaEncontrada.getDataVencimentoBoleto());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bloquearCamposEmpresa() {
        txtResponsavel.setEditable(false);
        btnBuscar.setDisable(true);
    }

    public void bloquearCampos() {
        txtResponsavel.setEditable(false);
        btnBuscar.setDisable(true);
        cbTipo.setDisable(true);
    }

    private void validarCamposAoAtualizar(Agenda agenda) {
        if (!txtEmpresa.getText().equals(agenda.getIdEmpresa().getDescricao())) {
            alteracao += "Empresa: de " + agenda.getIdEmpresa().getDescricao() + " para " + txtEmpresa.getText() + " - ";
        }
        if (!txtResponsavel.getText().equals(agenda.getResponsavel())) {
            alteracao += "Responsâvel: de " + agenda.getResponsavel() + " para " + txtResponsavel.getText() + " - ";
        }
        if (!Util.removerAcentuacaoServico(cbStatusAgenda.getValue().toString()).equals(agenda.getStatusAgenda())) {
            alteracao += "Status da Agenda: de " + agenda.getStatusAgenda() + " para " + cbStatusAgenda.getValue() + " - ";
        }
        String boleto = "";
        if (agenda.getStatusBoleto().equals("Nao enviado")) {
            boleto = "Não enviado";
        }
        if (!cbStatusBoleto.getValue().equals(boleto)) {
            alteracao += "Status do Boleto: de " + agenda.getStatusBoleto() + " para " + cbStatusBoleto.getValue() + " - ";
        }
        if (!Util.removerAcentuacaoServico(cbTipo.getValue().toString()).equals(agenda.getTipo())) {
            alteracao += "Tipo da avaliação: de " + agenda.getTipo() + " para " + cbTipo.getValue() + " - ";
        }
        if (dtInicial.getValue() != agenda.getDataInicial()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            alteracao += "Data: de " + agenda.getDataInicial() + " para " + sdf.format(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue())) + " - ";
        }
        if (isCancelamento) {
            alteracao += "Status da agenda: de " + agenda.getStatusAgenda() + " para Cancelado";
        }

    }

    private boolean contemFeriado(Date data) {
        cale1.setTime(data);
        cale1.set(Calendar.HOUR_OF_DAY, 0);
        cale1.set(Calendar.MINUTE, 0);
        cale1.set(Calendar.SECOND, 0);
        cale1.set(Calendar.MILLISECOND, 0);
        cale1.set(Calendar.YEAR, 0);

        for (Feriado feriado : Controller.getFeriados()) {
            cale2.setTime(UtilConverter.converterLocalDateToUtilDate(feriado.getData()));

            cale2.set(Calendar.HOUR_OF_DAY,
                    0);
            cale2.set(Calendar.MINUTE,
                    0);
            cale2.set(Calendar.SECOND,
                    0);
            cale2.set(Calendar.MILLISECOND,
                    0);
            cale2.set(Calendar.YEAR,
                    0);
            if (cale1.equals(cale2)) {
                return true;
            }
        }
        return false;
    }

    protected void iniciarCadastroFinanceiro() {
        AgendaService as = new AgendaService();
        agendaEncontrada = as.findLast();
        JPA.em(false).close();
        StaticObject.setAgendaEncontrada(agendaEncontrada);
        StaticObject.setEmpresaEncontrada(empresaEncontrada);
        RunAnotherApp.runAnotherApp(FinanceiroController.class
        );
    }

}
