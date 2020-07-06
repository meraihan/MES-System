package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_products_workstations")
public class OrderProductWorkstation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "id_order_product", nullable = false)
    private OrderProducts orderProducts;
    @JoinColumn(name = "id_workstation", nullable = false)
    private WorkStation workStation;
    @Column(name = "order_number")
    private Integer orderNumber;
}
