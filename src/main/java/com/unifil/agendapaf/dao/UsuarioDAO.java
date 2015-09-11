package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danielmorita
 */
public class UsuarioDAO extends GenericDAO<Long, Usuario> {

    private EntityManager entityManager;

    public UsuarioDAO(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public Usuario login(String login, String senha) {
        try {
            Query query = entityManager.createNamedQuery("Usuario.Login");
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
