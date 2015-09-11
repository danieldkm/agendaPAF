package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Historico;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class HistoricoDAO extends GenericDAO<Long, Historico> {

    public HistoricoDAO(EntityManager entityManager) {
        super(entityManager);
    }

}
