package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.OrderProducts;
import com.wulf.systems.mes.MESSystem.repo.OrderProductRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-products")
@Api(value = "/order-products", consumes = "application/json")
public class OrderProductsController {

    @Autowired
    private OrderProductRepo orderProductRepo;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the Order Products", notes = "find all the Order Products in a moment")
    public ResponseEntity<List<OrderProducts>> list() {
        try {
            List<OrderProducts> orderProducts = orderProductRepo.findAll();
            if (orderProducts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderProducts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr order products", notes = "Saving order products")
    public ResponseEntity<OrderProducts> createOrderProduct(@RequestBody OrderProducts orderProducts) {
        try {
            OrderProducts orderProductsSave = orderProductRepo.save(orderProducts);
            return new ResponseEntity<>(orderProductsSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{opId}")
    @ApiOperation(value = "Get details of a Order Products by Id")
    public ResponseEntity<OrderProducts> getOrderProductsById(@PathVariable("opId") Integer opId) {
        Optional<OrderProducts> optionalOrderProduct = orderProductRepo.findById(opId);
        if(!optionalOrderProduct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalOrderProduct.get());
    }

    @PostMapping("/update/{opId}")
    @ApiOperation(value = "Update order products by id", notes = "updates of order products")
    public ResponseEntity<OrderProducts> update(@PathVariable("opId") Integer opId, @RequestBody OrderProducts orderProducts) {
        Optional<OrderProducts> optionalOrderProducts = orderProductRepo.findById(opId);
        if(optionalOrderProducts.isPresent()){
            OrderProducts newOrderProducts = optionalOrderProducts.get();
            newOrderProducts.setOrders(orderProducts.getOrders());
            newOrderProducts.setProduct(orderProducts.getProduct());
            newOrderProducts.setStatus(orderProducts.isStatus());
            newOrderProducts.setWorkStation(newOrderProducts.getWorkStation());
            newOrderProducts.setQuantity(orderProducts.getQuantity());
            return new ResponseEntity<>(orderProductRepo.save(newOrderProducts), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{opId}")
    @ApiOperation(value = "Delete order products by Id", notes = "deletion of order products")
    public ResponseEntity<HttpStatus> deleteOrderProducts(@PathVariable("opId") Integer opId) {
        try {
            orderProductRepo.deleteById(opId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
