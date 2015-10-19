package com.unifil.agendapaf.service;

import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.EmpresaDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Empresa;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class EmpresaService implements InterfaceCrud<Empresa> {

    private EmpresaDAO dao;

    public EmpresaService() {
        dao = new EmpresaDAO(JPA.em());
    }

    @Override
    public void salvar(Empresa empresa) {
        try {
            dao.beginTransaction();
            empresa.validate();
            dao.save(empresa);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Empresa empresa) {
        try {
            dao.beginTransaction();
//            empresa.validate();
            dao.delete(empresa);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Empresa empresa) {
        try {
            dao.beginTransaction();
            empresa.validate();
            dao.update(empresa);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Empresa> findAll() {
        return dao.findAll();
    }

    public Empresa findById(Long id) {
        return dao.findById(id);
    }

    public Empresa findLast() {
        Empresa r = dao.findLast();
        return r;
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
