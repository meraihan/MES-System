package com.wulf.systems.mes.MESSystem.repo;

import com.wulf.systems.mes.MESSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}
