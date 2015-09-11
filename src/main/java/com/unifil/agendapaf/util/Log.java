package com.unifil.agendapaf.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Criar um arquivo contendo o log da aplicação
 *
 * @author danielmorita
 */
public class Log {

    public Log(String classe) {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
