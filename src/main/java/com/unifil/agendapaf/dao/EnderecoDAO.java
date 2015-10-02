package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.Endereco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.avalon.framework.parameters.ParameterException;

/**
 *
 * @author danielmorita
 */
public class EnderecoDAO extends GenericDAO<Long, Endereco> {

    private EntityManager entityManager;

    public EnderecoDAO(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public Endereco findById(Long id) {
        try {
            Query query = entityManager.createNamedQuery("Endereco.findByID");
            query.setParameter("id", id);
            return (Endereco) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Endereco findLast() {
        try {
            Query query = entityManager.createNamedQuery("Endereco.findLast");
            return (Endereco) query.setMaxResults(1).getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Endereco> findByIdEmpresa(Empresa idEmpresa) {
        try {
            Query query = entityManager.createNamedQuery("Endereco.findByIDEmpresa");
            query.setParameter("idEmpresa", idEmpresa);
            return (ObservableList<Endereco>) FXCollections.observableArrayList(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
