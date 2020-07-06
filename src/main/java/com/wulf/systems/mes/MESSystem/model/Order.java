package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "order")
public class Order {
    private Integer id;
    private String name;
}
