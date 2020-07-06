package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "order_products_workstations")
public class OrderProductWorkstation {
    private Integer id;
    @JoinColumn(name = "id_order_product", nullable = false)
    private OrderProducts orderProducts;
    @JoinColumn(name = "id_workstation", nullable = false)
    private WorkStation workStation;
    private Integer orderNumber;
}
