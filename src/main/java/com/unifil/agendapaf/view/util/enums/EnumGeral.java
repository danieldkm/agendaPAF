package com.unifil.agendapaf.view.util.enums;

/**
 *
 * @author danielmorita
 */
public enum EnumGeral {

    NomeUnidadePersistencia("agendaPAFECFPU");

    private String geral;

    private EnumGeral(String geral) {
        this.geral = geral;
    }

    public String getGeral() {
        return geral;
    }

    public void setGeral(String geral) {
        this.geral = geral;
    }

}
