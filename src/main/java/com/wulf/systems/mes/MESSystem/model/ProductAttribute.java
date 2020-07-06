package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product_attribute")
public class ProductAttribute {
    private Integer id;
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
    @JoinColumn(name = "id_attribute", nullable = false)
    private Attribute attribute;
}
