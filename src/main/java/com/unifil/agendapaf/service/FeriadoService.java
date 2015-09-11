package com.unifil.agendapaf.service;

import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.FeriadoDAO;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Feriado;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class FeriadoService implements InterfaceCrud<Feriado> {

    private FeriadoDAO dao;

    public FeriadoService() {
        dao = new FeriadoDAO(JPA.em());
    }

    @Override
    public void salvar(Feriado feriado) {
        try {
            dao.beginTransaction();
            feriado.validate();
            dao.save(feriado);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Feriado feriado) {
        try {
            dao.beginTransaction();
            feriado.validate();
            dao.delete(feriado);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Feriado feriado) {
        try {
            dao.beginTransaction();
            feriado.validate();
            dao.update(feriado);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Feriado> findAll() {
        return dao.findAll();
    }
    
    public EntityManager getEm(){
        return dao.getEntityManager();
    }

}
