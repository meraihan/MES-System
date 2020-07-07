package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.Property;
import com.wulf.systems.mes.MESSystem.repo.PropertyRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/property")
@Api(value = "/property", consumes = "application/json")
public class PropertyController {

    @Autowired
    private PropertyRepo propertyRepo;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the property", notes = "find all the property in a moment")
    public ResponseEntity<List<Property>> list() {
        try {
            List<Property> properties = propertyRepo.findAll();
            if (properties.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(properties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr property", notes = "Saving property")
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        try {
            Property station = propertyRepo.save(property);
            return new ResponseEntity<>(station, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{pId}")
    @ApiOperation(value = "Get details of a Property by Id")
    public ResponseEntity<Property> getPropertyById(@PathVariable("pId") Integer pId) {
        Optional<Property> property = propertyRepo.findById(pId);
        if(!property.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(property.get());
    }

    @PostMapping("/update/{pId}")
    @ApiOperation(value = "Update property by id", notes = "updates of property")
    public ResponseEntity<Property> update(@PathVariable("pId") Integer pId, @RequestBody Property property) {
        Optional<Property> properties = propertyRepo.findById(pId);
        if(properties.isPresent()){
            Property newProperty = properties.get();
            newProperty.setAttribute(property.getAttribute());
            newProperty.setValue(property.getValue());
            return new ResponseEntity<>(propertyRepo.save(newProperty), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{pId}")
    @ApiOperation(value = "Delete property by Id", notes = "deletion of property")
    public ResponseEntity<HttpStatus> deleteProperty(@PathVariable("pId") Integer pId) {
        try {
            propertyRepo.deleteById(pId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
