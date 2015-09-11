package com.unifil.agendapaf.testes;

import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Empresa;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author danielmorita
 */
public class Testes {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("agendaPAFECFPU");
        EntityManager em = factory.createEntityManager();
//        Testes t = new Testes();
//        Long l = new Long(1);
//        Empresa save = t.findByIdEmpresa(l, em);
//        for (Empresa es : t.findAllEmpresa(em)) {
//            System.out.println("EMPRESA: " + es.getDescricao() + " id " + es.getId());
//        }
//
//        Agenda a = new Agenda();
//        a.setCodigoInterno(1);
//        a.setDataFinal(LocalDate.of(2015, Month.AUGUST, 10));
//        a.setDataInicial(LocalDate.of(2015, Month.AUGUST, 10));
//        a.setDataReagendada(LocalDate.of(2015, Month.AUGUST, 10));
//        a.setDataVencimentoBoleto(LocalDate.of(2015, Month.AUGUST, 10));
//        a.setDiaSemana("Segunda");
//        a.setId(Long.MIN_VALUE);
//
////        Empresa e = new Empresa();
////        e.setBairro("centro2");
////        e.setCategoria(2);
////        e.setCelular("1123123322");
////        e.setCep("000000002");
////        e.setCidade("Londrina2");
////        e.setCnpj("000000000002");
////        e.setCodigoInterno(1);
////        e.setCpf("000000000002");
////        e.setDataCadastro(LocalDate.of(2015, Month.AUGUST, 17));
////        e.setDescricao("descricao2");
////        e.setEmail("email2");
////        e.setEndereco("enderecço2");
////        e.setEstado("PR2");
////        e.setFax("fax2");
////        Long id = new Long(2);
////        System.out.println("ID " + id);
//////        e.setId(id);
////        e.setInscricaoEstadual("incricaoE2");
////        e.setInscricaoMunicipal("IncricaoM2");
////        e.setNomeContato("contato2");
////        e.setNomeFantasia("nome fantasia2");
////        e.setObservacao("observação2");
////        e.setResponsavelTeste("Responsavel2");
////        e.setTelefone("Telafone2");
//        a.setIdEmpresa(save);
//        a.setResponsavel("Kamelo");
//        a.setStatusAgenda("Agendada");
//        a.setStatusBoleto("Enviado");
//        a.setTipo("Avaliação");
//        em.getTransaction().begin();
//        em.merge(a);
//        em.getTransaction().commit();
//        em.close();
        factory.close();
    }

    public Agenda findAllAgenda(Integer id, EntityManager em) {
        try {
            Query query = em.createNamedQuery("Agenda.findByID");
            query.setParameter("id", id);
            Agenda lista = (Agenda) query.getSingleResult();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Empresa findByIdEmpresa(Long id, EntityManager em) {
        try {
            Query query = em.createNamedQuery("Empresa.findByID");
            query.setParameter("id", id);
            Empresa lista = (Empresa) query.getSingleResult();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Empresa> findAllEmpresa(EntityManager em) {
        try {
            Query query = em.createNamedQuery("Empresa.findAll");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
