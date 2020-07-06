package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_product_attribute_value")
public class OrderProductAttributeValue {
    private Integer id;
    @JoinColumn(name = "id_order_product_detail", nullable = false)
    private OrderProductDetail orderProductDetail;
    @JoinColumn(name = "id_product_attribute", nullable = false)
    private ProductAttribute productAttribute;
    private BigDecimal value;

}
