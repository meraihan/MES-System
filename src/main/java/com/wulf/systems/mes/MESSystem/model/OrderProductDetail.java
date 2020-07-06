package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "order_product_detail")
public class OrderProductDetail {
    private Integer id;
    @JoinColumn(name = "id_order_products")
    private OrderProducts orderProducts;
    @JoinColumn(name = "id_order_product_attribute_value", nullable = false)
    private OrderProductAttributeValue orderProductAttributeValue;
    @JoinColumn(name = "id_workstation", nullable = false)
    private WorkStation workStation;
}
