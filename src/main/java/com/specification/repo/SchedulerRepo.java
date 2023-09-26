package com.specification.repo;

import com.specification.entities.SchedulerTimer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepo extends JpaRepository<SchedulerTimer, Long> {

    public SchedulerTimer findByName(String name);

}
