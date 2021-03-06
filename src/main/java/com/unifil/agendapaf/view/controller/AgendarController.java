package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.DateChooserSkin;
import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.EmpresasHomologadas;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.model.Historico;
import com.unifil.agendapaf.model.aux.Categoria;
import com.unifil.agendapaf.model.aux.Servico;
import com.unifil.agendapaf.service.AgendaService;
import com.unifil.agendapaf.service.EmpresasHomologadasService;
import com.unifil.agendapaf.service.FinanceiroService;
import com.unifil.agendapaf.service.HistoricoService;
import com.unifil.agendapaf.statics.StaticLista;
import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.view.util.enums.EnumStatus;
import com.unifil.agendapaf.util.Util;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumServico;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class AgendarController {

    @FXML
    public void initialize() {
        try {
            sceneManager = SceneManager.getInstance();
            mensagem = new Mensagem(stage);
            lblAgenda.setText("Agendamento");
            setOnActionsDts();
            cbTipo.getSelectionModel().selectFirst();
            cbStatusAgenda.getSelectionModel().selectFirst();
            cbStatusBoleto.getSelectionModel().selectFirst();
            dtVencimentoBoleto.setVisible(false);
            lblDtVencimento.setVisible(false);

            if (sceneManager.getReagendamento()) {
                isReagendamento = sceneManager.getReagendamento();
                sceneManager.setReagendamento(Boolean.FALSE);
                btnBuscar.setDisable(true);
                btnCancelar.setDisable(true);
                isUpdate = true;
            } else if (sceneManager.getUpdate()) {
                isUpdate = sceneManager.getUpdate();
                btnCancelar.setDisable(true);
                sceneManager.setUpdate(Boolean.FALSE);
            } else if (sceneManager.getCancelamento()) {
                isCancelamento = sceneManager.getCancelamento();
                isUpdate = true;
                sceneManager.setCancelamento(Boolean.FALSE);
                btnCancelar.setDisable(true);
                dtVencimentoBoleto.setDisable(true);
            }

            if (sceneManager.getAgendaEncontrada() != null) {
                agenda = sceneManager.getAgendaEncontrada();
                sceneManager.setAgendaEncontrada(null);
                setCampos();
                btnBuscar.setDisable(true);
                cbTipo.setDisable(true);
            } else if (sceneManager.getEmpresaEncontrada() != null) {
                empresaEncontrada = sceneManager.getEmpresaEncontrada();
                sceneManager.setEmpresaEncontrada(null);
//                txtEmpresa.setText(empresaEncontrada.getDescricao());
            }
            if (agenda == null) {
                agenda = new Agenda();
                agenda.setTipo("Avaliacao");
                agenda.setStatusBoleto("Nao enviado");
                agenda.setStatusAgenda("Data Agendada");
                if (empresaEncontrada != null) {
                    agenda.setIdEmpresa(empresaEncontrada);
                    Contato tempContato = null;
                    for (Contato c : StaticLista.getListaGlobalContato()) {
                        if (c.getIdEmpresa().getId().equals(empresaEncontrada.getId()) && c.selecionadoBoolean()) {
                            tempContato = c;
                            break;
                        }
                    }
                    if (tempContato != null) {
                        agenda.setResponsavel(tempContato.getResponsavelTeste());
                    }
                }
            }
            setBindComponentsWithAgenda(agenda);
        } catch (Exception e) {
            e.printStackTrace();
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao inicializar agenda", e);
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
    private Agenda agenda = null;
    private boolean isReagendamento = false;
    private boolean isCancelamento = false;
    private String motivoReagendamento = "";
    private static Stage stage;
    private String alteracao = "";
    private Calendar cale1 = new GregorianCalendar();
    private Calendar cale2 = new GregorianCalendar();
    private SimpleDateFormat diaSemana = new SimpleDateFormat("EEEE");
    private boolean isUpdate = false;
    private static SceneManager sceneManager;
    private LocalDate dataSelecionada;
    private Mensagem mensagem;

    @FXML
    private void actionBtnSalvar() {
        actionBtnAtualizarDiaSemana();
        if (validarCampos()) {
            try {
                salvarOuAtualizar(isUpdate);
                if (isUpdate) {
                    validarCamposAoAtualizar(agenda);
//                    agenda.setIdEmpresa(empresaEncontrada);
                }
                gravarHistorico();
                seConcluido();
//                if (gravarHistorico() && seConcluido()) {
//                    if (isUpdate) {
//                        mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
//                    } else {
//                        mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
//                    }
//                }
//                if (cbStatusAgenda.getValue().equals(EnumStatus.Concluido.getStatus())) {
//                    Dialogos d = new Dialogos(stage);
//                    Optional<?> result = d.confirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.AgendaFinanceiroDesejaCadastrar.getMensagem());
//                    if (result.get() == ButtonType.OK) {
//                        iniciarCadastroFinanceiro();
//                    }
//                }
                AgendaService as = new AgendaService();
                StaticLista.setListaGlobalAgenda(as.findOrderBy("dataInicial"));
                as.getEm().clear();
                as.getEm().close();
                StaticLista.setListaGlobalHistorico(Controller.getHistoricos());
                stage.close();
                DateChooserSkin.getMonthBack().arm();
                DateChooserSkin.getMonthBack().fire();
                DateChooserSkin.getMonthForward().arm();
                DateChooserSkin.getMonthForward().fire();
                if (isUpdate) {
                    mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Atualizado.getMensagem());
                } else {
                    mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
                }
                resetarCampos();
            } catch (Exception e) {
                e.printStackTrace();
                mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar.getMensagem(), e);
            }
        }
    }

    @FXML
    private void actionBtnCancelar() {
        resetarCampos();
    }

    @FXML
    private void actionBtnBuscar() {
//        StaticBoolean.setAgenda(true);
        stage.close();
        sceneManager.showTabelaEmpresa(false, true, false, false, false, false, false);
    }

    @FXML
    private void actionCBTipo() {
        if (cbTipo.getSelectionModel().getSelectedItem().equals(EnumServico.PreAvaliacao.getServico()) || cbTipo.getSelectionModel().getSelectedItem().equals(EnumServico.PreAvaliacaoIntinerante.getServico()) || cbTipo.getSelectionModel().getSelectedItem().equals(EnumServico.PreAvaliacaoRemoto.getServico())) {
            dtInicial.setValue(dataSelecionada);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(UtilConverter.converterLocalDateToUtilDate(dataSelecionada));
            dtFinal.setValue(UtilConverter.converterUtilDateToLocalDate(calendar.getTime()));
        } else {
            if (dataSelecionada == null) {
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
            agenda.setDataInicial(dtInicial.getValue());
            agenda.setDataFinal(dtFinal.getValue());
            agenda.setTipo(Util.removerAcentuacaoServico(agenda.getTipo()));
            agenda.setStatusBoleto(Util.removerAcentuacaoServico(agenda.getStatusBoleto()));
            agenda.setStatusAgenda(Util.removerAcentuacaoServico(agenda.getStatusAgenda()));
            if (atualizar) {
                if (isCancelamento) {
                    agenda.setStatusAgenda("Cancelado");
                }
                AgendaService as = new AgendaService();
                as.editar(agenda);
                StaticLista.setListaGlobalAgenda(as.findAll());
                JPA.em(false).close();
            } else {
                AgendaService as = new AgendaService();
                as.salvar(agenda);
                StaticLista.setListaGlobalAgenda(as.findAll());
                JPA.em(false).close();
            }
        } catch (Exception ex) {
            Logger.getLogger(AgendarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvarFinanceiro(Financeiro financeiro) {
        FinanceiroService fs = new FinanceiroService();
        int porcentagem = 0;
        financeiro.setTipoServico(Util.porAcentuacaoServico(agenda.getTipo()));
//        System.out.println("agenda.getIdEmpresa(). " + agenda.getIdEmpresa().toString2());
        for (Categoria categoria : Controller.getCategorias()) {
            if (categoria.getNome().equals(agenda.getIdEmpresa().getCategoria())) {
                porcentagem = categoria.getPorcento();
                break;
            }
        }
        double valorServico = 0;
        for (Servico servico : Controller.getServicos()) {
            if (servico.getNome().equals(financeiro.getTipoServico())) {
                valorServico = servico.getValor();
                break;
            }
        }
        if (financeiro.getHoraAdicional() > 0) {
            for (Servico servico : Controller.getServicos()) {
                if (servico.getNome().equals(EnumServico.HoraAdicional.getServico())) {
                    valorServico = servico.getValor();
                }
            }
            financeiro.setValorPago(valorServico * financeiro.getHoraAdicional());
            financeiro.setTipoServico("Hora Adicional");
        } else {
            financeiro.setHoraAdicional(0);
            if (financeiro.getValorPago() == 0.0) {
                financeiro.setValorPago((valorServico - (valorServico * (porcentagem / 100))));
            }
        }
        financeiro.setCategoria(agenda.getIdEmpresa().getCategoria());
        financeiro.setDataInicial(dtInicial.getValue());
        financeiro.setDataFinal(dtFinal.getValue());
        financeiro.setIdEmpresa(agenda.getIdEmpresa());
        fs.salvar(financeiro);
        JPA.em(false).close();
    }

    private
            boolean seConcluido() {
        try {
            if (cbStatusAgenda.getValue().equals(Util.removerAcentuacaoServico(EnumStatus.Concluido.getStatus()))) {
                Dialogos d = new Dialogos(stage);
                d.financeiro(sceneManager);
//                Optional<ButtonType> cadastrarFinanceiro = d.confirmacao(EnumMensagem.Pergunta.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.AgendaFinanceiroDesejaCadastrar.getMensagem());
//                if (cadastrarFinanceiro.get() == ButtonType.OK) {
//                    salvarFinanceiro(-1);
//                    Optional<ButtonType> result = d.confirmacao(EnumMensagem.Pergunta.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.AgendaFinanceiroExisteHoraAdicional.getMensagem());
//                    if (result.get() == ButtonType.OK) {
//                        try {
//                            int horas = Integer.parseInt(d.entrada(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.InformeHoraAdicional.getMensagem()));
//                            salvarFinanceiro(horas);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            mensagem.erro(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.ErroConversao.getTitulo(), e);
//                        }
//                    }
                StaticLista.setListaGlobalFinanceiro(Controller.getFinanceiros());
//                }
//                System.out.println("cbTipo.getValue() " + cbTipo.getValue());
                if (cbTipo.getValue().equals(Util.removerAcentuacaoServico(EnumServico.Avaliacao.getServico()))
                        || cbTipo.getValue().equals(Util.removerAcentuacaoServico(EnumServico.AvaliacaoIntinerante.getServico()))) {
                    System.out.println("cbTipo.getValue().equals(EnumServico.Avaliacao.getServico() " + cbTipo.getValue().equals(EnumServico.Avaliacao.getServico()));
                    EmpresasHomologadas eh = new EmpresasHomologadas();

                    eh.setIdEmpresa(agenda.getIdEmpresa());
                    eh.setDataHomologada(dtInicial.getValue());
                    Calendar cal = new GregorianCalendar();

                    cal.setTime(UtilConverter.converterLocalDateToSqlDate(eh.getDataHomologada()));
                    cal.add(Calendar.YEAR,
                            1);
                    cal.add(Calendar.MONTH,
                            10);
                    eh.setDataAviso(UtilConverter.converterUtilDateToLocalDate(cal.getTime()));
                    dtFinal.setValue(UtilConverter.converterUtilDateToLocalDate(cal.getTime()));
                    Contato tempContato = null;
                    for (Contato c : StaticLista.getListaGlobalContato()) {
                        if (c.getIdEmpresa().getId().equals(agenda.getIdEmpresa().getId()) && c.selecionadoBoolean()) {
                            tempContato = c;
                            break;
                        }
                    }
                    eh.setEmail(tempContato.getEmail());
                    eh.setVisualizado(
                            "NAO");
                    EmpresasHomologadasService ehs = new EmpresasHomologadasService();
                    ehs.salvar(eh);
                    StaticLista.setListaGlobalEmpresasHomologadas(ehs.findAll());
                    JPA.em(false).close();
                    StaticLista.setListaGlobalEmpresasHomologadas(Controller.getEmpresasHomologadas());
                }
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
            if (this.agenda.getId() != null) {
                agenda = this.agenda;
                dtReagendada = this.agenda.getDataInicial();
            } else {
                AgendaService as = new AgendaService();
                agenda = as.findLast();
                JPA.em(false).close();
            }
//            System.out.println("this.agenda " + this.agenda.getIdEmpresa().toString2());
            Historico h = new Historico();

            h.setIdAgenda(agenda);
            h.setIdEmpresa(agenda.getIdEmpresa());
            h.setDataInicial(dtInicial.getValue());
            h.setDataFinal(dtFinal.getValue());
            h.setMotivo(motivoReagendamento);
            if (isReagendamento) {
                h.setStatus(EnumStatus.Reagendada.getStatus());
                h.setDataReagendada(dtReagendada);
            } else {
                h.setStatus(agenda.getStatusAgenda());
            }
            h.setDataAlteracao(LocalDate.now());
            h.setIdUsuario(sceneManager.getUsuarioLogado());

            HistoricoService hs = new HistoricoService();
            hs.salvar(h);
            StaticLista.setListaGlobalHistorico(hs.findAll());
            JPA.em(false).close();
//            StaticDao.getHistoricoService().salvar(h);
        } catch (Exception ex) {
            Logger.getLogger(AgendarController.class
                    .getName()).log(Level.SEVERE, null, ex);
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(),
                    EnumMensagem.AgendaErroSalvarHistoricoAgenda.getMensagem(), ex);
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
        txtSemana.setText("");
        agenda = null;
        empresaEncontrada = null;
        alteracao = "";
        sceneManager.setCancelamento(Boolean.FALSE);
        isReagendamento = false;
        isUpdate = false;
    }

    private boolean validarCampos() {
        ValidationSupport validationSupport = new ValidationSupport();
        boolean ok = true;
        if (txtEmpresa.getText().equals("")) {
            validationSupport.registerValidator(txtEmpresa, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.AgendaRequerEmpresa.getMensagem());
            txtEmpresa.requestFocus();
            ok = false;
        }
        if (txtResponsavel.getText() == null || txtResponsavel.getText().equals("")) {
            validationSupport.registerValidator(txtResponsavel, Validator.createEmptyValidator(EnumMensagem.Requer.getMensagem()));
            mensagem.aviso(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.AgendaRequerResponsavel.getMensagem());
            ok = false;
        }
        if (cbTipo.getValue().equals(EnumServico.Avaliacao.getServico()) || cbTipo.getValue().equals(EnumServico.AvaliacaoIntinerante.getServico())) {
            if (txtSemana.getText().equals("Sexta-feira")) {
                if (dtInicial.getValue().compareTo(dtFinal.getValue()) == -1
                        || dtInicial.getValue().compareTo(dtFinal.getValue()) == -2) {
                    validationSupport.registerValidator(dtInicial, false, (Control c, LocalDate newValue)
                            -> ValidationResult.fromWarningIf(dtInicial, "Informe a data", !LocalDate.now().equals(newValue)));
                    dtInicial.requestFocus();
                    mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                            EnumMensagem.Aviso.getMensagem() + "\n"
                            + "data final não pode ser de sabado ou domingo");
                    ok = false;
                }
            }
            if (dtInicial.getValue().equals(dtFinal.getValue())) {
                if (cbTipo.getValue().toString().equals(EnumServico.Avaliacao.getServico())) {
                    validationSupport.registerValidator(dtInicial, false, (Control c, LocalDate newValue)
                            -> ValidationResult.fromWarningIf(dtInicial, "Informe a data", !LocalDate.now().equals(newValue)));
                    dtInicial.requestFocus();
                    mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                            EnumMensagem.Aviso.getMensagem() + "\n"
                            + "data final no mesmo dia da data inicial!!!");
                }
                ok = false;
            } else {
                // aki nesse trecho do codigo verifico se a data inicial e final 
                // tem diferenteça de 2 dias ou mais se houver não deixa cadastrar
                long dias = Period.between(dtInicial.getValue(), dtFinal.getValue()).getDays();
                if (Math.abs(dias) > 1) {
                    validationSupport.registerValidator(dtFinal, false, (Control c, LocalDate newValue)
                            -> ValidationResult.fromWarningIf(dtFinal, "Informe a data", !LocalDate.now().equals(newValue)));
                    dtFinal.requestFocus();
                    mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                            EnumMensagem.Aviso.getMensagem() + "\n"
                            + "com data final em dois dias a mais de diferença da data inicial!!!");
                    ok = false;
                }
//                Calendar ca1 = new GregorianCalendar();
//                ca1.setTime(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue()));
//                ca1.set(Calendar.HOUR_OF_DAY, 0);
//                ca1.set(Calendar.MINUTE, 0);
//                ca1.set(Calendar.SECOND, 0);
//                ca1.set(Calendar.MILLISECOND, 0);
//
//                Calendar ca2 = new GregorianCalendar();
//                ca2.setTime(UtilConverter.converterLocalDateToUtilDate(dtFinal.getValue()));
//                ca2.set(Calendar.HOUR_OF_DAY, 0);
//                ca2.set(Calendar.MINUTE, 0);
//                ca2.set(Calendar.SECOND, 0);
//                ca2.set(Calendar.MILLISECOND, 0);
//
//                //varial para verificar se o proximo dia do dia ca1 é 1
//                //se for deixar cadastrar o ultimo com o 1 dia do mes seguinte!
//                Calendar ca3 = new GregorianCalendar();
//                ca3.setTime(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue()));
//                ca3.set(Calendar.HOUR_OF_DAY, 0);
//                ca3.set(Calendar.MINUTE, 0);
//                ca3.set(Calendar.SECOND, 0);
//                ca3.set(Calendar.MILLISECOND, 0);
//
//                int d1 = ca1.get(Calendar.DAY_OF_MONTH);
//                int d2 = ca2.get(Calendar.DAY_OF_MONTH);
//                ca3.set(Calendar.DAY_OF_MONTH, d1 + 1);
//                int d3 = ca3.get(Calendar.DAY_OF_MONTH);
//                if (d3 != 1) {
//                    if ((d2 - d1) > 1 || (d2 - d1) < 1) {
////                        validate(dtFinal);
//                        validationSupport.registerValidator(dtFinal, false, (Control c, LocalDate newValue)
//                                -> ValidationResult.fromWarningIf(dtFinal, "Informe a data", !LocalDate.now().equals(newValue)));
//                        dtFinal.requestFocus();
//                        Dialogos.criarDialogWarning(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
//                                EnumMensagem.Aviso.getMensagem() + "\n"
//                                + "com data final em dois dias a mais de diferença da data inicial!!!");
//                        ok = false;
//                    }
//                }
            }
        }
        if (contemFeriado(UtilConverter.converterLocalDateToUtilDate(dtInicial.getValue()))) {
            mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.Aviso.getMensagem() + "nos feriados");
            ok = false;
        }
        if (cbTipo.getValue().equals(EnumServico.HoraAdicional.getServico()) || cbTipo.getValue().equals(EnumServico.PreAvaliacao.getServico()) || cbTipo.getValue().equals(EnumServico.PreAvaliacaoIntinerante.getServico()) || cbTipo.getValue().equals(EnumServico.PreAvaliacaoRemoto.getServico())) {
            if (!dtInicial.getValue().equals(dtFinal.getValue())) {
                validationSupport.registerValidator(dtFinal, false, (Control c, LocalDate newValue)
                        -> ValidationResult.fromWarningIf(dtFinal, "Informe a data", !LocalDate.now().equals(newValue)));
                dtFinal.requestFocus();
                mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
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
                mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
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
                mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(), EnumMensagem.Aviso.getMensagem() + "\n" + "Prencher a data inicial e/ou final");
                ok = false;
            }
        }
        if (dtFinal.getValue().isBefore(dtInicial.getValue())) {
            validationSupport.registerValidator(dtFinal, false, (Control c, LocalDate newValue)
                    -> ValidationResult.fromWarningIf(dtFinal, "Informe a data", !LocalDate.now().equals(newValue)));
            dtFinal.requestFocus();
            mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                    EnumMensagem.Aviso.getMensagem() + "\n"
                    + "com data final não pode ser menor que data inicial!!!");
            ok = false;
        }
        if (!isCancelamento && !isReagendamento && !isUpdate) {
            AgendaService as = new AgendaService();
            int timesI = 0;
            int timesF = 0;
            for (Agenda a : as.findAll()) {
                if (dtInicial.getValue().equals(a.getDataInicial())) {
                    timesI++;
                    if (a.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.Cancelado.getStatus()))
                            || a.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.NaoCompareceu.getStatus()))
                            || a.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.Pendente.getStatus()))
                            || a.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.PendenteWeb.getStatus()))) {
                        timesI--;
                    }
                }
                if (dtInicial.getValue().equals(a.getDataFinal())) {
                    timesF++;
                    if (a.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.Cancelado.getStatus()))
                            || a.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.NaoCompareceu.getStatus()))
                            || a.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.Pendente.getStatus()))
                            || a.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.PendenteWeb.getStatus()))) {
                        timesF--;
                    }
                }
            }

            if (timesI > 1 || timesF > 1) {
                mensagem.aviso(EnumMensagem.Aviso.getTitulo(), EnumMensagem.Aviso.getSubTitulo(),
                        EnumMensagem.Aviso.getMensagem() + "\n"
                        + "Já existe mais de um agendamento nesse dia");
                ok = false;
            }
            JPA.em(false).close();
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

//        dtInicial.setOnMouseExited(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent t) {
//                try {
//                    setSomarUmDiaDtFinal();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.err.println("Exption: setOnMouseExited, class AgendarController, metodo initialize - dtInicial");
//                }
//            }
//        });
//        dtFinal.setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent t) {
//                try {
//                    setSomarUmDiaDtFinal();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.err.println("Exption: setOnMouseEntered, class AgendarController, metodo initialize - dtFinal");
//                }
//            }
//        });
//        dtFinal.setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//            @Override
//            public void handle(KeyEvent t) {
//                setSomarUmDiaDtFinal();
//            }
//        });
    }

    public void setCampos() {
        try {
            lblAgenda.setText("Atualizar Agendamento");
            if (agenda.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.Concluido.getStatus()))
                    || agenda.getStatusAgenda().equals(Util.removerAcentuacaoServico(EnumStatus.NaoCompareceu.getStatus()))
                    || agenda.getStatusAgenda().equals(EnumStatus.DataAgendada.getStatus())) {
                dtInicial.setDisable(true);
            } else if (agenda.getStatusAgenda().equals(EnumStatus.DataAgendada.getStatus())) {
                dtFinal.setDisable(true);
            }
            if (isReagendamento) {
                lblAgenda.setText("Reagendamento");
                dtInicial.setDisable(false);
                dtFinal.setDisable(false);
            } else if (isCancelamento) {
                lblAgenda.setText("Cancelamento");
                dtInicial.setDisable(true);
                dtFinal.setDisable(true);
                cbStatusAgenda.setDisable(true);
                cbTipo.setDisable(true);
                cbStatusBoleto.setDisable(true);
            }
            String boleto = Util.porAcentuacaoServico(agenda.getStatusBoleto());
            if (!boleto.equals(EnumStatus.NaoEnviado.getStatus())) {
                dtVencimentoBoleto.setVisible(true);
                dtVencimentoBoleto.setValue(agenda.getDataVencimentoBoleto());
            }
//            txtEmpresa.setText(empresaEncontrada.getDescricao());
//            for (Contato c : StaticLista.getListaGlobalContato()) {
//                if (c.getIdEmpresa().equals(empresaEncontrada.getId())) {
//                    txtResponsavel.setText(c.getResponsavelTeste());
//                    break;
//                }
//            }
//            setBindComponentsWithAgenda(agenda);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        sceneManager.showFinanceiro(false, empresaEncontrada, agenda);
    }

    public void setStage(Stage agendaStage) {
        this.stage = agendaStage;
        // antes de fechar o stage faça ==== close stage before
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                dtInicial.setDisable(false);
                dtFinal.setDisable(false);
                empresaEncontrada = null;
                agenda = null;
                motivoReagendamento = null;
            }
        });
    }

    public void setDataSelecionada(LocalDate dataSelecionada) {
        this.dataSelecionada = dataSelecionada;
        dtInicial.setValue(dataSelecionada);
        setSomarUmDiaDtFinal();
        actionBtnAtualizarDiaSemana();
    }

    public void setMotivo(String motivo) {
        motivoReagendamento = motivo;
    }

    private void setBindComponentsWithAgenda(Agenda agenda) {
        this.agenda = agenda;
        this.agenda.setTipo(Util.porAcentuacaoServico(this.agenda.getTipo()));
        this.agenda.setStatusAgenda(Util.porAcentuacaoServico(this.agenda.getStatusAgenda()));
        this.agenda.setStatusBoleto(Util.porAcentuacaoServico(this.agenda.getStatusBoleto()));
        cbTipo.valueProperty().bindBidirectional(this.agenda.tipoProperty());
        cbStatusBoleto.valueProperty().bindBidirectional(this.agenda.statusBoletoProperty());
        cbStatusAgenda.valueProperty().bindBidirectional(this.agenda.statusAgendaProperty());
        txtEmpresa.textProperty().bindBidirectional(this.agenda.getIdEmpresa().descricaoProperty());
//        dtFinal.valueProperty().bindBidirectional(this.agenda.dataFinalProperty());
//        dtInicial.valueProperty().bindBidirectional(this.agenda.dataInicialProperty());
        txtResponsavel.textProperty().bindBidirectional(this.agenda.responsavelProperty());
        txtSemana.textProperty().bindBidirectional(this.agenda.diaSemanaProperty());
        dtVencimentoBoleto.valueProperty().bindBidirectional(this.agenda.dataVencimentoBoletoProperty());
    }

}
