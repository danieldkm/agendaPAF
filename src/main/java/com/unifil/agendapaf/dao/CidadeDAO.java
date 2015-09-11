package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Cidade;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class CidadeDAO extends GenericDAO<Long, Cidade> {

    public CidadeDAO(EntityManager entityManager) {
        super(entityManager);
    }

}
