package com.unifil.agendapaf.util;

import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.service.AgendaService;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.SeparatorMenuItemBuilder;

/**
 *
 * @author danielmorita
 */
public class PopUp {

    private ContextMenu popUp;
    private SceneManager sceneManager;
    private Mensagem mensagem;

    public PopUp() {
        popUp = new ContextMenu();
        sceneManager = SceneManager.getInstance();
        mensagem = new Mensagem(null);
    }

    public void criarPopUp() {
        try {
            popUp.getItems().addAll(
                    MenuItemBuilder.create()
                    .text("Agendar")
                    .onAction(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            SceneManager.getInstance().showAgenda(null);
                        }
                    })
                    .
                    build(),
                    SeparatorMenuItemBuilder.create().build(),
                    MenuItemBuilder.create()
                    .text("Reagendar")
                    .onAction(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            sceneManager.setReagendamento(Boolean.TRUE);
                            sceneManager.showTabelaAgenda();
//                            RunAnotherApp.runAnotherApp(TabelaAgendaController.class);
                        }
                    })
                    .build(),
                    /*.graphic(createIcon())*/
                    SeparatorMenuItemBuilder.create().build(),
                    MenuItemBuilder.create()
                    .text("Atualizar Agenda")
                    .onAction(new EventHandler() {

                        @Override
                        public void handle(Event t) {
                            AgendaService as = new AgendaService();
                            ObservableList<Agenda> listaAgendas = as.findByDate(sceneManager.getDataSelecionada());
                            JPA.em(false).close();
                            if (listaAgendas.size() == 0) {
                                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.PrincipalErroNaoExisteAgendamento.getMensagem());
                            } else {
                                sceneManager.setUpdate(Boolean.TRUE);
                                sceneManager.showTabelaAgenda();
                            }
                        }

                    }).build(),
                    SeparatorMenuItemBuilder.create().build(),
                    MenuItemBuilder.create()
                    .text("Cancelar Agendamento")
                    .onAction(new EventHandler() {
                        @Override
                        public void handle(Event t) {
                            AgendaService as = new AgendaService();
                            ObservableList<Agenda> listaAgendas = as.findByDate(sceneManager.getDataSelecionada());
                            JPA.em(false).close();
                            if (listaAgendas.size() == 0) {
                                mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.PrincipalErroNaoExisteAgendamento.getMensagem());
                            } else {
                                boolean dataAgendada = false;
                                for (Agenda agenda : listaAgendas) {
                                    if (agenda.getStatusAgenda().equals("Data Agendada")) {
                                        dataAgendada = true;
                                        break;
                                    }
                                }
                                if (dataAgendada) {
                                    sceneManager.setCancelamento(Boolean.TRUE);
                                    sceneManager.showTabelaAgenda();
                                } else {
                                    mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.PrincipalErroNaoExisteAgendamento.getMensagem());
                                }
                            }
                        }
                    })
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro!! Classe: Controller.class  Metodo: criarPopUp");
        }
    }

    public ContextMenu getPopUp() {
        return popUp;
    }

}
