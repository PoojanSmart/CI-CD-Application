/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.ProjectMaster;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author smart
 */
@Stateless
public class Timers {
    @Resource
    private TimerService timerService;

    @PersistenceContext(name="mypu")
    EntityManager em;
    
    @EJB Build b;
    
    
    @Timeout
    public void runJobs(Timer timer) throws GitAPIException,IOException
    {
        Collection<ProjectMaster> pj = em.createNamedQuery("ProjectMaster.findScheduled").getResultList();
        for(ProjectMaster p:pj)
        {
            if(timer.getInfo().equals(p.getProjectName()))
            {
                b.setProject(p).BuildSequance();
            }
        }
    }
    
    public void startJobs()
    {
        Collection<ProjectMaster> pj = em.createNamedQuery("ProjectMaster.findScheduled").getResultList();
        for(ProjectMaster p:pj)
        {
            final TimerConfig tconf =  new TimerConfig(p.getProjectName(),false);
            String parts[] = p.getTimeInterval().split(" ");
            ScheduleExpression sc = new ScheduleExpression();
            if(parts.length==7)
            {
                sc = new ScheduleExpression()
                        .second(parts[0])
                        .minute(parts[1])
                        .hour(parts[2])
                        .dayOfMonth(parts[3])
                        .month(parts[4])
                        .dayOfWeek(parts[5])
                        .year(parts[6]);
            }
            else if(parts.length==6)
            {
                sc = new ScheduleExpression()
                        .second(parts[0])
                        .minute(parts[1])
                        .hour(parts[2])
                        .dayOfMonth(parts[3])
                        .month(parts[4])
                        .dayOfWeek(parts[5]);
            }
            timerService.createCalendarTimer(sc , tconf);
        }
    }
    
    public void startJob(String projectName)
    {
         Collection<ProjectMaster> pj = em.createNamedQuery("ProjectMaster.findScheduled").getResultList();
            for(ProjectMaster p:pj)
            {
                if(p.getProjectName().equals(projectName))
                {
                    final TimerConfig tconf =  new TimerConfig(p.getProjectName(),false);
                    String parts[] = p.getTimeInterval().split(" ");
                    ScheduleExpression sc = new ScheduleExpression();
                    if(parts.length==7)
                    {
                        sc = new ScheduleExpression()
                                .second(parts[0])
                                .minute(parts[1])
                                .hour(parts[2])
                                .dayOfMonth(parts[3])
                                .month(parts[4])
                                .dayOfWeek(parts[5])
                                .year(parts[6]);
                    }
                    else if(parts.length==6)
                    {
                        sc = new ScheduleExpression()
                                .second(parts[0])
                                .minute(parts[1])
                                .hour(parts[2])
                                .dayOfMonth(parts[3])
                                .month(parts[4])
                                .dayOfWeek(parts[5]);
                    }
                    timerService.createCalendarTimer(sc , tconf);
                }
            }
    }
    
    
    public void stopJobs()
    {
        Collection<ProjectMaster> pj = em.createNamedQuery("ProjectMaster.findScheduled").getResultList();
        Collection<Timer> ts =  timerService.getAllTimers();

        for(ProjectMaster p:pj)
        {
            for(Timer t:ts)
            {
                if(t.getInfo().equals(p.getProjectName()))
                {
                    t.cancel();
                }
            }
        }
    }
    public void stopJob(String projectName)
    {
        Collection<ProjectMaster> pj = em.createNamedQuery("ProjectMaster.findScheduled").getResultList();
        Collection<Timer> ts =  timerService.getAllTimers();


        for(Timer t:ts)
        {
            if(t.getInfo().equals(projectName))
            {
                t.cancel();
            }
        }
        
    }
    
    public Boolean getState(String projectName)
    {
        Collection<Timer> ts =  timerService.getAllTimers();


        for(Timer t:ts)
        {
            if(t.getInfo().equals(projectName))
            {
                return true;
            }
        }
        return false;
    }
    
}
