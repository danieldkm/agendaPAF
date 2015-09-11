package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Empresa;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class EmpresaDAO extends GenericDAO<Long, Empresa> {



    public EmpresaDAO(EntityManager entityManager) {
        super(entityManager);
    }

   
}
