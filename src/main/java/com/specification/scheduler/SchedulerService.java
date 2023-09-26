package com.specification.scheduler;

import com.specification.entities.SchedulerTimer;
import com.specification.repo.SchedulerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
public class SchedulerService {

    @Autowired
    private static SchedulerRepo schedulerRepo;

//    final static private String timeExpression =  "#{@applicationPropertyService.getApplicationProperty()}";
    final static private String timeExpression =  "* * * * * *";

//    @Scheduled(cron  = timeExpression)
    public void runScheduledTask() {
//        String cronExpression = getCronExpressionFromDatabase(1L);
//        getCronExpressionFromDatabase(11L);
//        if(timeExpression ){
            System.out.println("Hiiii,    Scheduling task with cron expression: " + timeExpression);
            Date date = new Date();
            System.out.println(date.getSeconds());
        System.out.println("sys env -> "+System.getenv().replace(",","\n"));
//        }

    }

//    private static String getCronExpressionFromDatabase(long id) {
//        // Fetch the cron expression from the database
//        SchedulerTimer entity = schedulerRepo.findById(id).orElse(null);
////        timeExpression = entity != null ? entity.getCronExpression() : "*/6 * * * * *";
//        return entity != null ? entity.getCronExpression() : "*/6 * * * * *";
//    }

}

