package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.ProductAttribute;
import com.wulf.systems.mes.MESSystem.model.Property;
import com.wulf.systems.mes.MESSystem.repo.ProductAttributeRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product-attribute")
@Api(value = "/product-attribute", consumes = "application/json")
public class ProductAttributeController {

    @Autowired
    private ProductAttributeRepo productAttributeRepo;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the product attribute", notes = "find all the product attribute in a moment")
    public ResponseEntity<List<ProductAttribute>> list() {
        try {
            List<ProductAttribute> productAttributes = productAttributeRepo.findAll();
            if (productAttributes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productAttributes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr product attribute", notes = "Saving product attribute")
    public ResponseEntity<ProductAttribute> createProductAttribute(@RequestBody ProductAttribute productAttribute) {
        try {
            ProductAttribute attribute = productAttributeRepo.save(productAttribute);
            return new ResponseEntity<>(attribute, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{paId}")
    @ApiOperation(value = "Get details of a Product Attribute by Id")
    public ResponseEntity<ProductAttribute> getProductAttributeById(@PathVariable Integer paId) {
        Optional<ProductAttribute> optionalProductAttribute = productAttributeRepo.findById(paId);
        if(!optionalProductAttribute.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalProductAttribute.get());
    }

    @PostMapping("/update/{paId}")
    @ApiOperation(value = "Update Product Attribute by id", notes = "updates of Product Attribute")
    public ResponseEntity<ProductAttribute> update(@PathVariable("paId") Integer paId, @RequestBody ProductAttribute productAttribute) {
        Optional<ProductAttribute> pAttribute = productAttributeRepo.findById(paId);
        if(pAttribute.isPresent()){
            ProductAttribute attribute = pAttribute.get();
            attribute.setAttribute(productAttribute.getAttribute());
            attribute.setProduct(productAttribute.getProduct());
            return new ResponseEntity<>(productAttributeRepo.save(attribute), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{paId}")
    @ApiOperation(value = "Delete product attribute by Id", notes = "deletion of product attribute")
    public ResponseEntity<HttpStatus> deleteProductAttribute(@PathVariable("paId") Integer paId) {
        try {
            productAttributeRepo.deleteById(paId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
