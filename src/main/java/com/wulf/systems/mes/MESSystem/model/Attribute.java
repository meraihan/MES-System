package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "attribute")
public class Attribute {
    private Integer id;
    private String attributeType;
}
