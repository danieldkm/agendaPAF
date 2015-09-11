package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Estado;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class EstadoDAO extends GenericDAO<Long, Estado> {

    public EstadoDAO(EntityManager entityManager) {
        super(entityManager);
    }

}
