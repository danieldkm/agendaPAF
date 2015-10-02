package com.unifil.agendapaf.exemplos;

import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilTexto;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author danielmorita
 */
public class OutrosTestes {

    public static void main(String[] args) {
        System.out.println(DateFormat.getDateInstance(DateFormat.LONG).format(UtilConverter.converterLocalDateToUtilDate(LocalDate.now())));
        System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM).format(UtilConverter.converterLocalDateToUtilDate(LocalDate.now())));
        System.out.println("" + UtilTexto.formatarMascaraCnpj("10293847564732"));
        System.out.println("" + UtilTexto.formatarMascaraCpf("04635185940"));
        System.out.println(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_ORDINAL_DATE));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        System.out.println("FLOAT arrenda "+Math.round(1.5));
        System.out.println("FLOAT arrenda "+Math.floor(1.5));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.valueOf("dd/MM/yyyy"))));
    }

}
