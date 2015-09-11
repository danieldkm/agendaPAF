package com.unifil.agendapaf.util;

import com.unifil.agendapaf.view.util.enums.EnumServico;
import com.unifil.agendapaf.view.util.enums.EnumStatus;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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
            retorna = EnumStatus.Concluido.getStatus();
        }
        return retorna;
    }

    public void salvarArquivo(String documento, String file) {
        File exeFile = new File("");
//        File path = new File(exeFile.getAbsolutePath() + file);
        File path = new File(exeFile.getAbsolutePath() + file);
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.println(
                    "<?xml version=\"1.0\" encoding=\"windows-1252\"?>"
            );
            writer.println(documento);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
