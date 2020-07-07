package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.WorkstationConfiguration;
import com.wulf.systems.mes.MESSystem.repo.WorkStationConfigRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/workstation-config")
@Api(value = "/workstation-config", consumes = "application/json")
public class WorkStationConfigController {

    @Autowired
    private WorkStationConfigRepo workStationConfigRepo;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the workstation configuration", notes = "find all the workstation configuration in a moment")
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
