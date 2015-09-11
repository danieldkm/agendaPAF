package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Agenda;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danielmorita
 */
public class AgendaDAO extends GenericDAO<Long, Agenda> {

    private EntityManager entityManager;

    public AgendaDAO(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public ObservableList<Agenda> findByDate(LocalDate data) {
        try {
            Query query = entityManager.createNamedQuery("Agenda.findByDate");
            query.setParameter("data", data);
            return FXCollections.observableArrayList(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Agenda> findOrderBy(String order) {
        try {
            Query query = entityManager.createNamedQuery("Agenda.findOrderBy");
            query.setParameter("order", order);
            return FXCollections.observableArrayList(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Agenda findLast() {
        try {
            Query query = entityManager.createNamedQuery("Agenda.findLast");
            return (Agenda) query.setMaxResults(1).getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
