package com.unifil.agendapaf.service;

import com.unifil.agendapaf.dao.EnderecoDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Endereco;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class EnderecoService implements InterfaceCrud<Endereco> {

    private EnderecoDAO dao;

    public EnderecoService() {
        dao = new EnderecoDAO(JPA.em());
    }

    @Override
    public void salvar(Endereco endereco) {
        try {
            dao.beginTransaction();
            endereco.validate();
            dao.save(endereco);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Endereco endereco) {
        try {
            dao.beginTransaction();
            dao.delete(endereco);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Endereco endereco) {
        try {
            dao.beginTransaction();
            endereco.validate();
            dao.update(endereco);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Endereco> findAll() {
        return dao.findAll();
    }

    public Endereco findById(Long id) {
        return dao.findById(id);
    }

    public Endereco findLast() {
        Endereco r = dao.findLast();
        return r;
    }

    public ObservableList<Endereco> findByIdEmpresa(Empresa idEmpresa) {
        return dao.findByIdEmpresa(idEmpresa);
    }

    public ObservableList<Endereco> findAllSelecionarTrue() {
        return dao.findAllSelecionarTrue();
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
