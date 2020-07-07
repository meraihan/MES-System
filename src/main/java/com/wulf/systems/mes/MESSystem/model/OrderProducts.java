package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_products")
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    @JoinColumn(name = "workstation", nullable = false)
    private WorkStation workStation;
    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
    private Integer quantity;
    @Column(columnDefinition="tinyint(1) default 1")
    private boolean status;
}
