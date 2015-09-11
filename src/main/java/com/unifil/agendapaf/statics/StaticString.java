package com.unifil.agendapaf.statics;

/**
 *
 * @author danielmorita
 */
public class StaticString {

    private static String txtMotivoReagendamento;
    private static String txtVisualizadorMotivo;

    public static String getTxtMotivoReagendamento() {
        return txtMotivoReagendamento;
    }

    public static void setTxtMotivoReagendamento(String txtMotivoReagendamento) {
        StaticString.txtMotivoReagendamento = txtMotivoReagendamento;
    }

    public static String getTxtVisualizadorMotivo() {
        return txtVisualizadorMotivo;
    }

    public static void setTxtVisualizadorMotivo(String txtVisualizadorMotivo) {
        StaticString.txtVisualizadorMotivo = txtVisualizadorMotivo;
    }

}
