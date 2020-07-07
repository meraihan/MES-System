package com.wulf.systems.mes.MESSystem.repo;

import com.wulf.systems.mes.MESSystem.model.WorkstationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkStationConfigRepo extends JpaRepository<WorkstationConfiguration, Integer> {

}
