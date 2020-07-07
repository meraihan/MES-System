package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.WorkstationConfiguration;
import com.wulf.systems.mes.MESSystem.repo.WorkStationConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/workstation-config")
public class WorkStationConfigController {

    @Autowired
    private WorkStationConfigRepo workStationConfigRepo;

    @GetMapping(path = "/list", produces = "application/json")
    public ResponseEntity<List<WorkstationConfiguration>> list() {
        try {
            List<WorkstationConfiguration> wConfigs = workStationConfigRepo.findAll();
            if (wConfigs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(wConfigs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
