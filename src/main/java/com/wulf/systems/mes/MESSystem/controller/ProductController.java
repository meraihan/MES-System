package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.Product;
import com.wulf.systems.mes.MESSystem.repo.ProductRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Api(value = "/product", consumes = "application/json")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;


    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the product", notes = "find all the product in a moment")
    public ResponseEntity<List<Product>> list() {
        try {
            List<Product> products = productRepo.findAll();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr product", notes = "Saving product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product prodSave = productRepo.save(product);
            return new ResponseEntity<>(prodSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{pId}")
    @ApiOperation(value = "Get details of a Product by Id")
    public ResponseEntity<Product> getProductById(@PathVariable("pId") Integer paId) {
        Optional<Product> optionalProduct = productRepo.findById(paId);
        if(!optionalProduct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalProduct.get());
    }

    @PostMapping("/update/{pId}")
    @ApiOperation(value = "Update Product by id", notes = "updates of Product")
    public ResponseEntity<Product> update(@PathVariable("pId") Integer paId, @RequestBody Product product) {
        Optional<Product> optionalProduct = productRepo.findById(paId);
        if(optionalProduct.isPresent()){
            Product newProduct = optionalProduct.get();
            newProduct.setName(product.getName());
            return new ResponseEntity<>(productRepo.save(newProduct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{pId}")
    @ApiOperation(value = "Delete product by Id", notes = "deletion of product")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("pId") Integer pId) {
        try {
            productRepo.deleteById(pId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
