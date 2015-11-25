package com.unifil.agendapaf.util.mensagem;

/**
 *
 * @author danielmorita
 */
public interface ImplMensagem {

    void erro(String titulo, String cabecalho, String corpo, Exception ex);

    void informacao(String titulo, String cabecalho, String corpo);

    void aviso(String titulo, String cabecalho, String corpo);

}
