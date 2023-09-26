package com.specification.scheduler;

import com.specification.entities.SchedulerTimer;
import com.specification.repo.SchedulerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationPropertyService {
    @Autowired
    private SchedulerRepo schedulerRepo;

    public String getApplicationProperty(){
        SchedulerTimer schedulerTimer = this.schedulerRepo.findById(11L).orElseThrow(()-> new RuntimeException("Scheduler not found with id: "+ 11));
        return schedulerTimer.getCronExpression();
    }
}