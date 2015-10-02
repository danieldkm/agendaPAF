package com.unifil.agendapaf.service;

import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.HistoricoDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Historico;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class HistoricoService implements InterfaceCrud<Historico> {

    private HistoricoDAO dao;

    public HistoricoService() {
        dao = new HistoricoDAO(JPA.em());
    }

    @Override
    public void salvar(Historico historico) {
        try {
            dao.beginTransaction();
            historico.validate();
            dao.save(historico);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Historico historico) {
        try {
            dao.beginTransaction();
            historico.validate();
            dao.delete(historico);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Historico historico) {
        try {
            dao.beginTransaction();
            historico.validate();
            dao.update(historico);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Historico> findAll() {
        return dao.findAll();
    }

    public Historico findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
