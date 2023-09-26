package com.specification.scheduler;

import com.specification.entities.SchedulerTimer;
import com.specification.repo.SchedulerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TickService {

    @Autowired
    private SchedulerRepo schedulerRepo;

    private long delay = 3000; // Initial delay in milliseconds (5 seconds)

    public void tick() {
        // Your scheduled task logic here
        Date date = new Date();
        System.out.println("Scheduled task running..." + delay + "     second delay ->   " + date.getSeconds());
    }

    public long getDelay() {
        return delay;
    }

    public String setDelayFromDb(String name) {
        SchedulerTimer schedulerTimer = this.schedulerRepo.findByName(name);
        long delay_time = Long.parseLong(schedulerTimer.getCronExpression());
        setDelay(delay_time);
        return schedulerTimer.getCronExpression();
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
}
