package com.unifil.agendapaf.util;

import com.unifil.agendapaf.view.controller.LaudoController;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author danielmorita
 */
public class UtilConverter {

    public static java.util.Date converteSqlDateToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.sql.Date converterUtilDateToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    public static java.sql.Date converterLocalDateToSqlDate(LocalDate localDate) {
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        java.util.Date date = java.util.Date.from(instant);
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date converterLocalDateToUtilDate(LocalDate localDate) {
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        return java.util.Date.from(instant);
    }

    public static LocalDate converterSqlDateToLocalDate(java.sql.Date sqlDate) {
        java.util.Date uDate = converteSqlDateToUtilDate(sqlDate);
        Instant instant = uDate.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate converterUtilDateToLocalDate(java.util.Date utilDate) {
        Instant instant = utilDate.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String converterDoubleString(double precoDouble) {
        /*Transformando um double em 2 casas decimais*/
        DecimalFormat fmt = new DecimalFormat("0.00");   //limita o n�mero de casas decimais      
        String string = fmt.format(precoDouble);
        String[] part = string.split("[,]");
        String preco = part[0] + "." + part[1];
        return preco;
    }

    public static double converterDoubleDoisDecimais(double precoDouble) {
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(precoDouble);
        String[] part = string.split("[,]");
        String string2 = part[0] + "." + part[1];
        double preco = Double.parseDouble(string2);
        return preco;
    }

    /**
     * Exemplo: yyyy-MM-dd EEEE: dia da semana
     *
     * @param data a ser formatada
     * @param formato formato da data
     * @return dtFormatada
     */
    public static String converterDataToFormat(Date data, String formato) {
        SimpleDateFormat dtFormatada = new SimpleDateFormat(formato);
        return dtFormatada.format(data);
    }

    /**
     * Converte date.util para XMLGregorianCalendar
     *
     * @param d DatePicker do javafx, objeto contem LocalDate que ser�
     * convertido para date
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertDateForXMLGregorianCalendar(DatePicker d) {
        Date date = Date.from(d.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(date);
        XMLGregorianCalendar calendar = null;
        try {
            calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory);

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(LaudoController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return calendar;
    }

    /**
     * Converte XMLGregorianCalendar para LocalDate
     *
     * @param d XMLGregorianCalendar, objeto contem Time
     * @return LocalDate
     */
    public static LocalDate convertXMLGregorianCalendarForDate(XMLGregorianCalendar d) {
        Date utilDate = d.toGregorianCalendar().getTime();
        return LocalDateTime.ofInstant(utilDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }
}
