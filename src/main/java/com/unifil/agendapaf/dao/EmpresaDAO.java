package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Empresa;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danielmorita
 */
public class EmpresaDAO extends GenericDAO<Long, Empresa> {

    private EntityManager entityManager;

    public EmpresaDAO(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public Empresa findLast() {
        try {
            Query query = entityManager.createNamedQuery("Empresa.findLast");
            return (Empresa) query.setMaxResults(1).getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
