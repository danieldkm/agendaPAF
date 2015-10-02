package com.unifil.agendapaf.service;

import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.EmpresasHomologadasDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.EmpresasHomologadas;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class EmpresasHomologadasService implements InterfaceCrud<EmpresasHomologadas> {

    private EmpresasHomologadasDAO dao;

    public EmpresasHomologadasService() {
        dao = new EmpresasHomologadasDAO(JPA.em());
    }

    @Override
    public void salvar(EmpresasHomologadas empresasHomologadas) {
        try {
            dao.beginTransaction();
            empresasHomologadas.validate();
            dao.save(empresasHomologadas);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(EmpresasHomologadas empresasHomologadas) {
        try {
            dao.beginTransaction();
            empresasHomologadas.validate();
            dao.delete(empresasHomologadas);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(EmpresasHomologadas empresasHomologadas) {
        try {
            dao.beginTransaction();
            empresasHomologadas.validate();
            dao.update(empresasHomologadas);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<EmpresasHomologadas> findAll() {
        return dao.findAll();
    }

    public EmpresasHomologadas findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
