package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_products")
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workstation")
    private WorkStation workStation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Orders orders;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;
    private Integer quantity;
    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status;
}
