package com.unifil.agendapaf.service;

import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.CidadeDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Cidade;
import com.unifil.agendapaf.model.Contato;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class CidadeService implements InterfaceCrud<Cidade> {

    private CidadeDAO dao;

    public CidadeService() {
        dao = new CidadeDAO(JPA.em());
    }

    @Override
    public void salvar(Cidade cidade) {
        try {
            dao.beginTransaction();
            cidade.validate();
            dao.save(cidade);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Cidade cidade) {
        try {
            dao.beginTransaction();
            cidade.validate();
            dao.delete(cidade);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Cidade cidade) {
        try {
            dao.beginTransaction();
            cidade.validate();
            dao.update(cidade);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Cidade> findAll() {
        return dao.findAll();
    }

    public Cidade findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
