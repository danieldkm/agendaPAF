package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.view.util.enums.EnumGeral;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.jpa.HibernateEntityManager;

/**
 *
 * @author danielmorita
 */
/**
 * Rotinas gerais de interface com o JPA
 */
public class JPA {

    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<EntityManager>();
    private static EntityManagerFactory factory;
    private static Map<Object, Object> connectionProperties = new HashMap<Object, Object>();

    /**
     * Cria uma entity manager factory única e o retorna em todas as demais
     * chamadas
     *
     * @return factory
     */
    public static EntityManagerFactory getFactory() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory(EnumGeral.NomeUnidadePersistencia.getGeral(), connectionProperties);
        }
        return factory;
    }

    /**
     * Cria um entity manager único (se criar = true) para a thread e o retorna
     * em todas as demais chamadas
     *
     * @param criar - necessario para verificar a criação ou não da
     * EntityManager
     * @return em
     */
    public static EntityManager em(boolean criar) {

        EntityManager em = (EntityManager) threadLocal.get();

        if (em == null || !em.isOpen()) {

            if (criar) {
                em = getFactory().createEntityManager();
                threadLocal.set(em);
            }
        }

        return em;

    }

    /**
     * Cria um entity manager único para a thread e o retorna em todas as demais
     * chamadas
     *
     * @return em
     */
    public static EntityManager em() {
        return em(true);
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            HibernateEntityManager hem = (HibernateEntityManager) em();
            SessionImplementor sim = (SessionImplementor) hem.getSession();
            return sim.connection();

//            Session session = em().unwrap(Session.class);
//            SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
//            ConnectionProvider cp = sfi.getConnectionProvider();
////            ConnectionProvider cp = ((SessionFactoryImplementor) session).getConnectionProvider();
//            conn = cp.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(JPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        em(false).close();
        return conn;
    }
}
