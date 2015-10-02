package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Endereco;
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

}
