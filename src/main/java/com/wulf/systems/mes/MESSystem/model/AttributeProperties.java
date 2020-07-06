package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "attribute_properties")
public class AttributeProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    @JoinColumn(name = "id_attribute", nullable = false)
    private Attribute attribute;
    @JoinColumn(name = "id_property", nullable = false)
    private Property property;
}
