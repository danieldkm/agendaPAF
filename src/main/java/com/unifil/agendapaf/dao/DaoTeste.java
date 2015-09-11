package com.unifil.agendapaf.dao;

import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.service.AgendaService;
import com.unifil.agendapaf.service.EmpresaService;
import java.time.LocalDate;

/**
 *
 * @author danielmorita
 */
public class DaoTeste {

    public static void main(String[] args) {

        String persistenceUnitName = "agendaPAFECFPU";

        SimpleEntityManager simpleEntityManager = new SimpleEntityManager(persistenceUnitName);

//        /**
//         * THE SERVICE LAYER ENCAPSULATES EVERY BEGIN/COMMIT/ROLLBACK
//         */
//        AgendaService costumerService = new AgendaService(simpleEntityManager);

//        costumerService.save(new Agenda("test", "test"));
//        costumerService.save(new Agenda("test", "test"));
//        costumerService.save(new Agenda("test", "test"));
//        costumerService.save(new Agenda("test", "test"));
//
//        for (Costumer c : costumerService.findAll()) {
//            System.out.println(c.getName());
//        }
//
//        productService.save(new Product("test", "test"));
//        productService.save(new Product("test", "test"));
//        productService.save(new Product("test", "test"));
//        productService.save(new Product("test", "test"));
//
//        for (Product p : productService.findAll()) {
//            System.out.println(p.getName());
//        }
        /**
         * ALWAYS NEED TO BE CALLED!
         */
//        Agenda a = new Agenda();
//        a.setDataFinal(LocalDate.now());
//        a.setDataInicial(LocalDate.now());
//        a.setDataVencimentoBoleto(LocalDate.now());
//        a.setDiaSemana("diasemana");
//        EmpresaService es = new EmpresaService(simpleEntityManager);
//        System.out.println("es.findAll().get(0) " + es.findAll().get(0).toString2());
//        a.setIdEmpresa(es.findAll().get(0));
//        a.setResponsavel("Resposavel");
//        a.setStatusAgenda("Status agenda");
//        a.setStatusBoleto("Status Boleto");
//        a.setTipo("Tipo");
//
//        
//        AgendaService as = new AgendaService(simpleEntityManager);
//        as.salvar(a);
//        System.out.println(as.findLast());
//        simpleEntityManager.close();
//
//        AgendaControllerJPA dao = new AgendaControllerJPA();
////        2015-08-10
//        LocalDate ld = LocalDate.of(2015, Month.AUGUST, 10);
////        System.out.println("dao.findlast() " + dao.findByDate(ld));
//
//        for (Agenda a : dao.findAll()) {
//            System.out.println("A " + a.getIdEmpresa().getDescricao());
//        }
//
//        dao.fechar();
//
//        EmpresaControllerJPA daoE = new EmpresaControllerJPA();
//
//        for (Empresa empresa : daoE.findAll()) {
//            System.out.println("E " + empresa.getDescricao());
//        }
//        daoE.fechar();
        
        Regra objetoRegra = new Regra();
        ProcessoThread thread1 = new ProcessoThread();
        thread1.processo = "A1";
        thread1.r = objetoRegra;
        
        ProcessoThread thread2 = new ProcessoThread();
        thread2.processo = "B2";
        thread2.r = objetoRegra;
        
        ProcessoThread thread3 = new ProcessoThread();
        thread3.processo = "X3";
        thread3.r = objetoRegra;
        
        ProcessoThread thread4 = new ProcessoThread();
        thread4.processo = "H5";
        thread4.r = objetoRegra;
        
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}

class ClasseThreaLocal {

    public static ThreadLocal<String> t1 = new ThreadLocal<String>();
}

class Regra {

    public void gravar() {
        System.out.println(ClasseThreaLocal.t1.get());
    }
}

class ProcessoThread extends Thread {

    public Regra r;
    public String processo;
    public void run(){
        ClasseThreaLocal.t1.set(processo);
        r.gravar();
    }
}

