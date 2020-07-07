package com.wulf.systems.mes.MESSystem.controller;

import com.wulf.systems.mes.MESSystem.model.OrderProductAttributeValue;
import com.wulf.systems.mes.MESSystem.repo.OrderProductAttributeValueRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-products-attribute-value")
@Api(value = "/order-products-attribute-value", consumes = "application/json")
public class OrderProductAttributeValueController {

    @Autowired
    private OrderProductAttributeValueRepo orderProductAttributeValueRepo;

    @GetMapping(path = "/list", produces = "application/json")
    @ApiOperation(value = "Api will get all the Order Products attribute value", notes = "find all the Order Products attribute value in a moment")
    public ResponseEntity<List<OrderProductAttributeValue>> list() {
        try {
            List<OrderProductAttributeValue> orderProductAttributeValues = orderProductAttributeValueRepo.findAll();
            if (orderProductAttributeValues.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderProductAttributeValues, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "Api will save thr order products attribute value value", notes = "Saving order products attribute value")
    public ResponseEntity<OrderProductAttributeValue> createOrderProductAttribute(@RequestBody OrderProductAttributeValue orderProductAttributeValue) {
        try {
            OrderProductAttributeValue orderProductAttributeValueSave = orderProductAttributeValueRepo.save(orderProductAttributeValue);
            return new ResponseEntity<>(orderProductAttributeValueSave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/find/{opaId}")
    @ApiOperation(value = "Get details of a Order Products attribute value by Id")
    public ResponseEntity<OrderProductAttributeValue> getOrderProductsAttributeById(@PathVariable("opaId") Integer opaId) {
        Optional<OrderProductAttributeValue> orderProductAttributeValue = orderProductAttributeValueRepo.findById(opaId);
        if(!orderProductAttributeValue.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderProductAttributeValue.get());
    }

    @PostMapping("/update/{opaId}")
    @ApiOperation(value = "Update order products attribute value by id", notes = "updates of order products attribute value")
    public ResponseEntity<OrderProductAttributeValue> update(@PathVariable("opaId") Integer opaId, @RequestBody OrderProductAttributeValue orderProductAttributeValue) {
        Optional<OrderProductAttributeValue> optionalOrderProductAttributeValue = orderProductAttributeValueRepo.findById(opaId);
        if(optionalOrderProductAttributeValue.isPresent()){
            OrderProductAttributeValue newOrderProductAttributeValue = optionalOrderProductAttributeValue.get();
            newOrderProductAttributeValue.setProductAttribute(orderProductAttributeValue.getProductAttribute());
            newOrderProductAttributeValue.setValue(orderProductAttributeValue.getValue());
            return new ResponseEntity<>(orderProductAttributeValueRepo.save(newOrderProductAttributeValue), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{opaId}")
    @ApiOperation(value = "Delete order products attribute value by Id", notes = "deletion of order products attribute value")
    public ResponseEntity<HttpStatus> deleteOrderProductAttributeValue(@PathVariable("opaId") Integer opaId) {
        try {
            orderProductAttributeValueRepo.deleteById(opaId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
