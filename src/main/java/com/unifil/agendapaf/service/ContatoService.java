package com.unifil.agendapaf.service;

import com.unifil.agendapaf.dao.ContatoDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.model.Contato;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class ContatoService implements InterfaceCrud<Contato> {

    private ContatoDAO dao;

    public ContatoService() {
        dao = new ContatoDAO(JPA.em());
    }

    @Override
    public void salvar(Contato contato) {
        try {
            dao.beginTransaction();
            dao.save(contato);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Contato contato) {
        try {
            dao.beginTransaction();
            dao.delete(contato);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Contato contato) {
        try {
            dao.beginTransaction();
            dao.update(contato);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Contato> findAll() {
        return dao.findAll();
    }

    public Contato findById(Long id) {
        return dao.findById(id);
    }

    public Contato findLast() {
        Contato r = dao.findLast();
        return r;
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }
}
