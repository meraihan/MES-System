package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_attribute")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
    @ManyToMany
    @JoinColumn(name = "id_attribute", nullable = false)
    private Attribute attribute;
}
