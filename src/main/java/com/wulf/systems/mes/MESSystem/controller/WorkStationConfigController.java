package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.WorkstationConfiguration;
import com.wulf.systems.mes.MESSystem.repo.WorkStationConfigRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr workstation configuration", notes = "Saving workstation configurations")
    public ResponseEntity<WorkstationConfiguration> createTutorial(@RequestBody WorkstationConfiguration workstationConfiguration) {
        try {
            WorkstationConfiguration wsConfig = workStationConfigRepo.save(workstationConfiguration);
            return new ResponseEntity<>(wsConfig, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{wcId}")
    @ApiOperation(value = "Get details of a Workstation Config by Id")
    public ResponseEntity<WorkstationConfiguration> getWorkstationDetails(@PathVariable Integer wcId) {
        Optional<WorkstationConfiguration> wsConfig = workStationConfigRepo.findById(wcId);
        if(!wsConfig.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(wsConfig.get());
    }

    @PostMapping("/update/{wcId}")
    @ApiOperation(value = "Update workstation config by id", notes = "updates of workstation config")
    public ResponseEntity<WorkstationConfiguration> update(@PathVariable("wId") Integer wId, @RequestBody WorkstationConfiguration workstationConfiguration) {
        Optional<WorkstationConfiguration> optionalWorkStation = workStationConfigRepo.findById(wId);
        if(optionalWorkStation.isPresent()){
            WorkstationConfiguration wsConfig = optionalWorkStation.get();
            wsConfig.setWorkStation(workstationConfiguration.getWorkStation());
            wsConfig.setProductAttribute(workstationConfiguration.getProductAttribute());
            wsConfig.setPermission(workstationConfiguration.isPermission());
            return new ResponseEntity<>(workStationConfigRepo.save(wsConfig), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{wcId}")
    @ApiOperation(value = "Delete workstation config by Id", notes = "deletion of workstation config")
    public ResponseEntity<HttpStatus> deleteWorkStationConfig(@PathVariable("wcId") Integer wcId) {
        try {
            workStationConfigRepo.deleteById(wcId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
