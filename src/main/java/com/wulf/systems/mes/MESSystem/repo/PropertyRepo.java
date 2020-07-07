package com.wulf.systems.mes.MESSystem.repo;

import com.wulf.systems.mes.MESSystem.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepo extends JpaRepository<Property, Integer> {
}
