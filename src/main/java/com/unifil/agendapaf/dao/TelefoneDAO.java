package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Telefone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danielmorita
 */
public class TelefoneDAO extends GenericDAO<Long, Telefone> {

    private EntityManager entityManager;

    public TelefoneDAO(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public Telefone findLast() {
        try {
            Query query = entityManager.createNamedQuery("Telefone.findLast");
            return (Telefone) query.setMaxResults(1).getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Telefone> findByIdEmpresa(Empresa idEmpresa) {
        try {
            Query query = entityManager.createNamedQuery("Telefone.findByIDEmpresa");
            query.setParameter("idEmpresa", idEmpresa);
            return (ObservableList<Telefone>) FXCollections.observableArrayList(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
