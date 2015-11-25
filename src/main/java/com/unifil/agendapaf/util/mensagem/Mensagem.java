package com.unifil.agendapaf.util.mensagem;

import javafx.stage.Stage;

/**
 *
 * @author danielmorita
 */
public class Mensagem implements ImplMensagem {

    private Stage stage;
//    private Notificacao notificacao;
//    private Dialogos dialogos;

    private ImplMensagem msg;

    public Mensagem(Stage stage) {
        this.stage = stage;
        msg = new Notificacao(stage);
//        msg = new Dialogos(stage);
    }

    @Override
    public void erro(String titulo, String cabecalho, String corpo, Exception ex) {
        msg.erro(titulo, cabecalho, corpo, ex);
    }

    @Override
    public void informacao(String titulo, String cabecalho, String corpo) {
        msg.informacao(titulo, cabecalho, corpo);
    }

    @Override
    public void aviso(String titulo, String cabecalho, String corpo) {
        msg.aviso(titulo, cabecalho, corpo);
    }

}
