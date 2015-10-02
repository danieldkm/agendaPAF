package com.unifil.agendapaf.interfaces;

import javafx.collections.ObservableList;
import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
public interface InterfaceCrud<T> {

    void salvar(T t);

    void deletar(T t);

    void editar(T t);

    public ObservableList<T> findAll();

    public T findById(Long id);

    public EntityManager getEm();

}
