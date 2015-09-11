package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Feriado;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class FeriadoDAO extends GenericDAO<Long, Feriado> {

    public FeriadoDAO(EntityManager entityManager) {
        super(entityManager);
    }
    
}
