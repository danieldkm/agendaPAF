package com.unifil.agendapaf.view.util.enums;

/**
 *
 * @author danielmorita
 */
public enum EnumStatus {

    Concluido("Concluído"),
    DataAgendada("Data Agendada"),
    Reagendada("Reagendada"),
    Cancelado("Cancelado"),
    NaoCompareceu("Não compareceu"),
    Enviado("Enviado"),
    NaoEnviado("Não enviado"),
    Pago("Pago"),
    Pendente("Pendente"),
    PendenteWeb("Pendente Web");

    private String status;

    private EnumStatus() {
    }

    private EnumStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
