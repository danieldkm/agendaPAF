package com.unifil.agendapaf.notificador;

import com.unifil.agendapaf.model.Agenda;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Esta classe demonstra uma tarefa que ira ser invocada pelo Quartz Tem que
 * implementar a interface Job do Quartz
 *
 *
 */
public class TarefaExemplo implements Job {

    /**
     * Metodo que � executado quando a tarefa � invocada
     */
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
//        // Obviamente, aqui vai a l�gica da tarefa a ser chamada, nesta caso, vai imprimir no console
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
//        System.out.println("Tentando enviar notificação as: " + dateFormat.format(new Date()));
////        Agendador ag = new Agendador();
//        try {
////            ag.inicia();
//            ObservableList<Agenda> listaAgendaFiltrada = FXCollections.observableArrayList();
//            for (Agenda agenda : Controller.getAgendasOrderBy("cod")) {
//                Calendar cal = Calendar.getInstance();
//                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
//                cal.set(Calendar.HOUR_OF_DAY,
//                        0);
//                cal.set(Calendar.MINUTE,
//                        0);
//                cal.set(Calendar.SECOND,
//                        0);
//                cal.set(Calendar.MILLISECOND,
//                        0);
//                if (agenda.getDataInicial2().equals(cal.getTime())) {
//                    listaAgendaFiltrada.add(agenda);
//                }
//            }
//            String corpo = "Notifica��o!!!!! as seguintes empresa(s) ir�o realizar avalia��o AMANH�;";
//            for (Agenda agenda : listaAgendaFiltrada) {
//                corpo += "\n" + agenda.getEmpresa();
//            }
//            Notificar notificar = new Notificar("danielkeyti@gmail.com", "daniel@morita", Controller.getUsuarios(), "Notifica��o de Avalia��es do PAF-ECF", corpo);
//            notificar.sendFromGMail();
//        } catch (Exception ex) {
//            Logger.getLogger(TarefaExemplo.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
