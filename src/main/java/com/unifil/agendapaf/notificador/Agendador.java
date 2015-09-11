/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unifil.agendapaf.notificador;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Esta � a classe que deve ser invocada e ir� disparar a tarefa nos momentos
 * determinados
 *
 *
 */
public class Agendador {

    public static void inicia() throws Exception {
        // Detalhes da tarefa
        JobDetail job = JobBuilder.newJob(TarefaExemplo.class).withIdentity(
                "tarefaAloMundo", "group1").build();
        // Gatilho - ou seja, quando ir� chamar, neste caso, a cada 5 segundos ou todos os dias as 8:30 am
        Trigger trigger;
        trigger = TriggerBuilder.newTrigger().withIdentity(
                "gatilhoAloMundo", "group1").withSchedule(
//                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build(); // a cada 5 min
//                        CronScheduleBuilder.cronSchedule("0 42 10 * * ?")).build(); // 10 e 42
//                        CronScheduleBuilder.cronSchedule("0 42 10 ? * WED").build(); // 10 e 42 de quartas
                        CronScheduleBuilder.dailyAtHourAndMinute(16, 38)).build();
        

        // Agenda e voa l�!
//        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        scheduler.start();
//        scheduler.scheduleJob(job, trigger);

        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.scheduleJob(job, trigger);
        sched.start();

    }

}
