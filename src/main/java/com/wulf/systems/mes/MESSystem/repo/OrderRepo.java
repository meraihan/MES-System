package com.wulf.systems.mes.MESSystem.repo;

import com.wulf.systems.mes.MESSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
