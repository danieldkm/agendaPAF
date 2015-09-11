package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.EmpresasHomologadas;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class EmpresasHomologadasDAO extends GenericDAO<Long, EmpresasHomologadas> {

    public EmpresasHomologadasDAO(EntityManager entityManager) {
        super(entityManager);
    }

}
