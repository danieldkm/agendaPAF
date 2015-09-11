package com.unifil.agendapaf.service;

import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.AgendaDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class AgendaService implements InterfaceCrud<Agenda> {

    private AgendaDAO dao;

//    private SimpleEntityManager simpleEntityManager;
    public AgendaService() {
//        this.simpleEntityManager = simpleEntityManager;
        dao = new AgendaDAO(JPA.em());
    }

    @Override
    public void salvar(Agenda agenda) {
        try {
            dao.beginTransaction();
            agenda.validate();
            dao.save(agenda);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Agenda agenda) {
        try {
            dao.beginTransaction();
            agenda.validate();
            dao.delete(agenda);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Agenda agenda) {
        try {
            dao.beginTransaction();
            agenda.validate();
            dao.update(agenda);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Agenda> findAll() {
        ObservableList<Agenda> r = dao.findAll();
        return r;
    }

    public ObservableList<Agenda> findByDate(LocalDate data) {
        ObservableList<Agenda> r = dao.findByDate(data);
        return r;
    }

    public ObservableList<Agenda> findOrderBy(String order) {
        ObservableList<Agenda> r = dao.findOrderBy(order);
        return r;
    }

    public Agenda findLast() {
        Agenda r = dao.findLast();
        return r;
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
