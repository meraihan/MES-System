package com.wulf.systems.mes.MESSystem.repo;

import com.wulf.systems.mes.MESSystem.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepo extends JpaRepository<OrderProducts, Integer> {
}
