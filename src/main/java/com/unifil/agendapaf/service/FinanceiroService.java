package com.unifil.agendapaf.service;

import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.FinanceiroDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Financeiro;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class FinanceiroService implements InterfaceCrud<Financeiro> {

    private FinanceiroDAO dao;

    public FinanceiroService() {
        dao = new FinanceiroDAO(JPA.em());
    }

    @Override
    public void salvar(Financeiro financeiro) {
        try {
            dao.beginTransaction();
            financeiro.validate();
            dao.save(financeiro);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Financeiro financeiro) {
        try {
            dao.beginTransaction();
            financeiro.validate();
            dao.delete(financeiro);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Financeiro financeiro) {
        try {
            dao.beginTransaction();
            financeiro.validate();
            dao.update(financeiro);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Financeiro> findAll() {
        return dao.findAll();
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
