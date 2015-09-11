package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Financeiro;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class FinanceiroDAO extends GenericDAO<Long, Financeiro> {

    public FinanceiroDAO(EntityManager entityManager) {
        super(entityManager);
    }

}
