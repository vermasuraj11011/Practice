package com.specification.controller;

import com.specification.entities.SchedulerTimer;
import com.specification.repo.SchedulerRepo;
import com.specification.scheduler.TickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerRepo schedulerRepo;

    @Autowired
    private TickService tickService;

    @PostMapping("/")
    public ResponseEntity<SchedulerTimer> createScheduler(@RequestBody SchedulerTimer schedulerTimer) {
        SchedulerTimer schedulerTimer1 = this.schedulerRepo.save(schedulerTimer);
        return ResponseEntity.ok(schedulerTimer1);
    }

    @GetMapping("/")
    public ResponseEntity<List<SchedulerTimer>> schedulers() {
        List<SchedulerTimer> schedulers = this.schedulerRepo.findAll();
        return ResponseEntity.ok(schedulers);
    }

    @GetMapping("/set-delay/{name}")
    public ResponseEntity<String> settingDelay(@PathVariable String name) {
        return ResponseEntity.ok(tickService.setDelayFromDb(name));
    }
}
