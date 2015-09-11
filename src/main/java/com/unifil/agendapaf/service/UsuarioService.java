package com.unifil.agendapaf.service;

import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.interfaces.InterfaceCrud;
import com.unifil.agendapaf.dao.UsuarioDAO;
import com.unifil.agendapaf.model.Usuario;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public class UsuarioService implements InterfaceCrud<Usuario> {

    private UsuarioDAO dao;

    public UsuarioService() {
        dao = new UsuarioDAO(JPA.em());
    }

    @Override
    public void salvar(Usuario usuario) {
        try {
            dao.beginTransaction();
            usuario.validate();
            dao.save(usuario);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void deletar(Usuario usuario) {
        try {
            dao.beginTransaction();
            usuario.validate();
            dao.delete(usuario);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    @Override
    public void editar(Usuario usuario) {
        try {
            dao.beginTransaction();
            usuario.validate();
            dao.update(usuario);
            dao.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dao.rollBack();
        }
    }

    public ObservableList<Usuario> findAll() {
        return dao.findAll();
    }

    public Usuario login(String login, String senha) {
        return dao.login(login, senha);
    }

    @Override
    public EntityManager getEm() {
        return dao.getEntityManager();
    }

}
