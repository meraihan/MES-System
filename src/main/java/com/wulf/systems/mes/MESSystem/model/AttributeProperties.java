package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "attribute_properties")
public class AttributeProperties {
    private Integer id;
    @JoinColumn(name = "id_attribute", nullable = false)
    private Attribute attribute;
    @JoinColumn(name = "id_property", nullable = false)
    private Property property;
}
