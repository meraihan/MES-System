package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.Orders;
import com.wulf.systems.mes.MESSystem.repo.OrderRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@Api(value = "/order", consumes = "application/json")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the Order", notes = "find all the order in a moment")
    public ResponseEntity<List<Orders>> list() {
        try {
            List<Orders> orders = orderRepo.findAll();
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr order", notes = "Saving order")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) {
        try {
            Orders orderSave = orderRepo.save(orders);
            return new ResponseEntity<>(orderSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{oId}")
    @ApiOperation(value = "Get details of a Order by Id")
    public ResponseEntity<Orders> getOrderById(@PathVariable("oId") Integer oId) {
        Optional<Orders> optionalProduct = orderRepo.findById(oId);
        if(!optionalProduct.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalProduct.get());
    }

    @PostMapping("/update/{oId}")
    @ApiOperation(value = "Update order by id", notes = "updates of order")
    public ResponseEntity<Orders> update(@PathVariable("oId") Integer oId, @RequestBody Orders orders) {
        Optional<Orders> optionalProduct = orderRepo.findById(oId);
        if(optionalProduct.isPresent()){
            Orders newOrders = optionalProduct.get();
            newOrders.setName(orders.getName());
            return new ResponseEntity<>(orderRepo.save(newOrders), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{oId}")
    @ApiOperation(value = "Delete order by Id", notes = "deletion of order")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("oId") Integer oId) {
        try {
            orderRepo.deleteById(oId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
