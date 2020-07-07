package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_attribute")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_attribute")
    private Attribute attribute;
}
