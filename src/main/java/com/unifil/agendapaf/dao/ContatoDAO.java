package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Endereco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danielmorita
 */
public class ContatoDAO extends GenericDAO<Long, Contato> {

    private EntityManager entityManager;

    public ContatoDAO(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public Contato findLast() {
        try {
            Query query = entityManager.createNamedQuery("Contato.findLast");
            return (Contato) query.setMaxResults(1).getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Contato> findByIdEmpresa(Empresa idEmpresa) {
        try {
            Query query = entityManager.createNamedQuery("Contato.findByIDEmpresa");
            query.setParameter("idEmpresa", idEmpresa);
            return (ObservableList<Contato>) FXCollections.observableArrayList(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
