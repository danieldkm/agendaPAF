package com.unifil.agendapaf.service;

import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.EstadoDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Estado;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class EstadoService implements InterfaceCrud<Estado> {

    private EstadoDAO dao;

    public EstadoService() {
        dao = new EstadoDAO(JPA.em());
    }

    @Override
    public void salvar(Estado estado) {
        try {
            dao.beginTransaction();
            estado.validate();
            dao.save(estado);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Estado estado) {
        try {
            dao.beginTransaction();
            estado.validate();
            dao.delete(estado);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Estado estado) {
        try {
            dao.beginTransaction();
            estado.validate();
            dao.update(estado);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Estado> findAll() {
        return dao.findAll();
    }

    public Estado findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
