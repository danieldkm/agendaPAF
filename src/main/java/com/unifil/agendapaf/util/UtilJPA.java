package com.unifil.agendapaf.util;

import java.sql.Connection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author danielmorita
 */
public class UtilJPA {

    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("agendaPAFECFPU");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static Connection getConnection() {
        return getEntityManager().unwrap(java.sql.Connection.class);
    }

}
