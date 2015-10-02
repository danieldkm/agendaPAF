package com.unifil.agendapaf.service;

import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.dao.TelefoneDAO;
import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.model.Telefone;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class TelefoneService implements InterfaceCrud<Telefone> {

    private TelefoneDAO dao;

    public TelefoneService() {
        dao = new TelefoneDAO(JPA.em());
    }

    @Override
    public void salvar(Telefone telefone) {
        try {
            dao.beginTransaction();
            dao.save(telefone);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Telefone telefone) {
        try {
            dao.beginTransaction();
            dao.delete(telefone);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Telefone telefone) {
        try {
            dao.beginTransaction();
            dao.update(telefone);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Telefone> findAll() {
        return dao.findAll();
    }

    public Telefone findById(Long id) {
        return dao.findById(id);
    }

    public Telefone findLast() {
        Telefone r = dao.findLast();
        return r;
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
