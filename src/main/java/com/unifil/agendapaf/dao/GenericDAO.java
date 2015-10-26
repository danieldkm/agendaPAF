package com.unifil.agendapaf.dao;

import java.lang.reflect.ParameterizedType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author danielmorita
 */
@SuppressWarnings("unchecked")
public class GenericDAO<PK, T> {

    private EntityManager entityManager;

    public GenericDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T getById(PK pk) {
        return (T) entityManager.find(getTypeClass(), pk);
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
//        entityManager.merge(entity);
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        entityManager.flush();
//        entityManager.remove(entity);
    }

    public ObservableList<T> findAll() {
        try {
            Query query = entityManager.createNamedQuery(getTypeClass().getSimpleName() + ".findAll");
            return FXCollections.observableArrayList(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public T findById(Long id) {
        try {
            Query query = entityManager.createNamedQuery(getTypeClass().getSimpleName() + ".findByID");
            query.setParameter("id", id);
            return (T) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<?> getTypeClass() {
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }

    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    public void commit() {
        entityManager.getTransaction().commit();
    }

    /**
     * THIS METHOD NEEDS TO BE ALWAYS CALLED
     */
    public void close() {
        entityManager.close();
        JPA.getFactory().close();
    }

    public void rollBack() {
        entityManager.getTransaction().rollback();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public boolean isOpen() {
        if (JPA.getFactory().isOpen() == true && entityManager.isOpen() == true) {
            return true;
        }
        return false;
    }
}
