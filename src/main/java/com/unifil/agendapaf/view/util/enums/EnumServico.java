package com.unifil.agendapaf.view.util.enums;

/**
 *
 * @author danielmorita
 */
public enum EnumServico {

    Avaliacao("Avaliação"),
    AvaliacaoIntinerante("Avaliação - Intinerante"),
    PreAvaliacao("Pré-Avaliação"),
    PreAvaliacaoIntinerante("Pré-Avaliação - Intinerante"),
    PreAvaliacaoRemoto("Pré-Avaliação - Remoto"),
    HoraAdicional("Hora Adicional");

    private String servico;

    private EnumServico() {
    }

    private EnumServico(String servico) {
        this.servico = servico;
    }

    public String getServico() {
        return servico;
    }
}
