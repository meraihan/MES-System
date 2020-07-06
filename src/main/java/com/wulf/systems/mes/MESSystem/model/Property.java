package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "property")
public class Property {
    private Integer id;
    private BigDecimal value;
}
