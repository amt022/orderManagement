package com.order.management.system.order.controller;

import com.order.management.system.order.entity.Orders;
import com.order.management.system.order.model.OrderRequest;
import com.order.management.system.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/customer/{customerId}/order")
    public Orders createOrder(@RequestBody OrderRequest order,@PathVariable(name = "customerId") Long customerId
    ) {
        return orderService.createOrder(order,customerId);
    }

    @GetMapping("/customer/{customerId}/orders")
    public List<Orders> getCustomerOrders(@PathVariable(name = "customerId") Long customerId) {
        return orderService.getCustomerOrders(customerId);
    }

}
