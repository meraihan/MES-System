package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.WorkStation;
import com.wulf.systems.mes.MESSystem.repo.WorkStationRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workstation")
@Api(value = "/workstation", consumes = "application/json")
public class WorkStationController {

    @Autowired
    private WorkStationRepo workStationRepo;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the workstation", notes = "find all the workstation in a moment")
    public ResponseEntity<List<WorkStation>> list() {
        try {
            List<WorkStation> workStations = workStationRepo.findAll();
            if (workStations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(workStations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr workstation", notes = "Saving workstations")
    public ResponseEntity<WorkStation> createTutorial(@RequestBody WorkStation workStation) {
        try {
            WorkStation station = workStationRepo.save(workStation);
            return new ResponseEntity<>(station, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{wId}")
    @ApiOperation(value = "Get details of a Workstation by Id")
    public ResponseEntity<WorkStation> getWorkstationDetails(@PathVariable("wId") Integer wId) {
        Optional<WorkStation> workStation = workStationRepo.findById(wId);
        if(!workStation.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(workStation.get());
    }

    @PostMapping("/update/{wId}")
    @ApiOperation(value = "Update workstation by id", notes = "updates of workstation")
    public ResponseEntity<WorkStation> update(@PathVariable("wId") Integer wId, @RequestBody WorkStation workStation) {
        Optional<WorkStation> optionalWorkStation = workStationRepo.findById(wId);
        if(optionalWorkStation.isPresent()){
            WorkStation station = optionalWorkStation.get();
            station.setName(workStation.getName());
            return new ResponseEntity<>(workStationRepo.save(station), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{wId}")
    @ApiOperation(value = "Delete workstation by Id", notes = "deletion of workstation")
    public ResponseEntity<HttpStatus> deleteWorkStation(@PathVariable("wId") Integer wId) {
        try {
            workStationRepo.deleteById(wId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
