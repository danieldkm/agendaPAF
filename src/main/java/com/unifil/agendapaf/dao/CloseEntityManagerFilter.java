package com.unifil.agendapaf.dao;

import javax.persistence.EntityManager;

/**
 *
 * @author danielmorita
 */
/**
 * Fecha os entity managers da thread ao final da requisição, se houver
 */
public class CloseEntityManagerFilter {

    public void destroy() {
        EntityManager em = JPA.em(false);
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        try {
//
//            chain.doFilter(request, response);
//
//        } finally {
//
//            //filtra urls com extensão 
//            String path = ((HttpServletRequest) request).getRequestURI();
//            String resource = path.substring(path.lastIndexOf('/'));
//            if (resource.indexOf(".") <= 0) {
//
//                EntityManager em = JPA.em(false);
//                if (em != null && em.isOpen()) {
//
//                    em.close();
//
//                }
//
//            }
//
//        }
//
//    }
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//    @Override
//    public boolean isLoggable(LogRecord record) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
