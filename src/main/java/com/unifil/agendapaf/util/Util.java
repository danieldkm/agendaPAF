package com.unifil.agendapaf.util;

import com.unifil.agendapaf.view.util.enums.EnumServico;
import com.unifil.agendapaf.view.util.enums.EnumStatus;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author danielmorita
 */
public class Util {

    public static String removerAcentuacaoServico(String comAcento) {
        String retorna = comAcento;
        if (comAcento.equals(EnumServico.Avaliacao.getServico())) {
            retorna = "Avaliacao";
        } else if (comAcento.equals(EnumServico.AvaliacaoIntinerante.getServico())) {
            retorna = "Avaliacao - Intinerante";
        } else if (comAcento.equals(EnumServico.PreAvaliacao.getServico())) {
            retorna = "Pre-Avaliacao";
        } else if (comAcento.equals(EnumServico.PreAvaliacaoRemoto.getServico())) {
            retorna = "Pre-Avaliacao - Remoto";
        } else if (comAcento.equals(EnumServico.PreAvaliacaoIntinerante.getServico())) {
            retorna = "Pre-Avaliacao - Intinerante";
        } else if (comAcento.equals(EnumStatus.NaoCompareceu.getStatus())) {
            retorna = "Nao compareceu";
        } else if (comAcento.equals(EnumStatus.Concluido.getStatus())) {
            retorna = "Concluido";
        } else if (comAcento.equals(EnumStatus.NaoEnviado.getStatus())) {
            retorna = "Nao enviado";
        }
        return retorna;
    }

    public static String porAcentuacaoServico(String semAcento) {
        String retorna = semAcento;
        if (semAcento.equals("Avaliacao")) {
            retorna = EnumServico.Avaliacao.getServico();
        } else if (semAcento.equals("Avaliacao - Intinerante")) {
            retorna = EnumServico.AvaliacaoIntinerante.getServico();
        } else if (semAcento.equals("Pre-Avaliacao")) {
            retorna = EnumServico.PreAvaliacao.getServico();
        } else if (semAcento.equals("Pre-Avaliacao - Intinerante")) {
            retorna = EnumServico.PreAvaliacaoIntinerante.getServico();
        } else if (semAcento.equals("Pre-Avaliacao - Remoto")) {
            retorna = EnumServico.PreAvaliacaoRemoto.getServico();
        } else if (semAcento.equals("Nao compareceu")) {
            retorna = EnumStatus.NaoCompareceu.getStatus();
        } else if (semAcento.equals("Concluido")) {
            retorna = EnumStatus.Concluido.getStatus();
        } else if (semAcento.equals("Nao enviado")) {
            retorna = EnumStatus.NaoEnviado.getStatus();
        }
        return retorna;
    }

    /**
     * metodo cria um arquivo contendo o log da aplicação
     *
     * @param classe classe que irá iniciar o log
     * @throws IOException qualquer erro de saida ou entrada
     */
    public static void setLogs(String classe) throws IOException {
        GregorianCalendar date = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy hh'h'mm'm'ss");
        String agora = format.format(date.getTime());
        agora += " - " + classe;
        File out = new File("logs/System.out");
        File err = new File("logs/System.err");
        if (!out.exists()) {
            out.mkdirs();
        }
        if (!err.exists()) {
            err.mkdirs();
        }
        System.setOut(new PrintStream(new FileOutputStream("logs/System.out/" + agora + ".txt", true)));
        System.setErr(new PrintStream(new FileOutputStream("logs/System.err/" + agora + ".txt", true)));
    }

}
