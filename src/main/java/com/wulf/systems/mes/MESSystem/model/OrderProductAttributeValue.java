package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_product_attribute_value")
public class OrderProductAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_product_attribute", nullable = false)
    private ProductAttribute productAttribute;
    private BigDecimal value;

}
