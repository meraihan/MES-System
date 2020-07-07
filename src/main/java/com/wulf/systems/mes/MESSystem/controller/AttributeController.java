package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.Attribute;
import com.wulf.systems.mes.MESSystem.repo.AttributeRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attribute")
@Api(value = "/attribute", consumes = "application/json")
public class AttributeController {

    @Autowired
    private AttributeRepo attributeRepo;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the attribute ", notes = "find all the attribute in a moment")
    public ResponseEntity<List<Attribute>> list() {
        try {
            List<Attribute> attributes = attributeRepo.findAll();
            if (attributes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(attributes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr attribute ", notes = "Saving attribute")
    public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute attribute) {
        try {
            Attribute attributeSave = attributeRepo.save(attribute);
            return new ResponseEntity<>(attributeSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{aId}")
    @ApiOperation(value = "Get details of a attribute by Id")
    public ResponseEntity<Attribute> getAttributeById(@PathVariable("aId") Integer aId) {
        Optional<Attribute> attribute = attributeRepo.findById(aId);
        if(!attribute.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(attribute.get());
    }

    @PostMapping("/update/{aId}")
    @ApiOperation(value = "Update attribute by id", notes = "updates of attribute")
    public ResponseEntity<Attribute> update(@PathVariable("aId") Integer aId, @RequestBody Attribute attribute) {
        Optional<Attribute> optionalAttribute = attributeRepo.findById(aId);
        if(optionalAttribute.isPresent()){
            Attribute newAttribute = optionalAttribute.get();
            newAttribute.setName(attribute.getName());
            newAttribute.setAttributeType(attribute.getAttributeType());
            return new ResponseEntity<>(attributeRepo.save(newAttribute), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{aId}")
    @ApiOperation(value = "Delete attribute by Id", notes = "deletion of attribute")
    public ResponseEntity<HttpStatus> deleteAttribute(@PathVariable("aId") Integer aId) {
        try {
            attributeRepo.deleteById(aId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
