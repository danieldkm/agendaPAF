package com.unifil.agendapaf.statics;

import java.time.LocalDate;

/**
 *
 * @author danielmorita
 */
public class StaticCalendar {

    private static boolean semestral = false;
    private static LocalDate dataSelecionada;

    public static boolean isSemestral() {
        return semestral;
    }

    public static void setSemestral(boolean semestral) {
        StaticCalendar.semestral = semestral;
    }

    public static LocalDate getDataSelecionada() {
        return dataSelecionada;
    }

    public static void setDataSelecionada(LocalDate dataSelecionada) {
        StaticCalendar.dataSelecionada = dataSelecionada;
    }

}
