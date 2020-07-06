package com.wulf.systems.mes.MESSystem.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "workstation_configuration")
public class WorkstationConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany
    @JoinColumn(name = "id_workstation", nullable = false)
    private WorkStation workStation;
    @JoinColumn(name = "id_product_attribute", nullable = false)
    private ProductAttribute productAttribute;
    @Column(columnDefinition="tinyint(1) default 1")
    private boolean permission;

}
