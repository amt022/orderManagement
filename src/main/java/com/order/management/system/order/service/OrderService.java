package com.order.management.system.order.service;


import com.order.management.system.order.entity.Customer;
import com.order.management.system.order.entity.Orders;
import com.order.management.system.order.model.OrderRequest;
import com.order.management.system.order.repository.CustomerRepository;
import com.order.management.system.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.MissingFormatArgumentException;

@Service
public class OrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Orders createOrder(OrderRequest orderRequest, Long customerId) {
        // logic for promoting customers based on order count
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new MissingFormatArgumentException("Invalid Customer Id!"));
        int orderCount = customer.getOrderCount();
        if (orderCount >= 9 && orderCount < 19) {
            customer.setCustomerType("gold");
        } else if (orderCount >= 19) {
            customer.setCustomerType("platinum");
        } else {
            customer.setCustomerType("regular");
        }

        if(orderCount+1 == 9){
            sendMail("You have placed 9 orders with us. Buy one more stuff and you will be promoted to Gold customer and enjoy 10% discounts!");
        } else if (orderCount+1 == 19) {
            sendMail("You have placed 19 orders with us. Buy one more stuff and you will be promoted to Platinum customer and enjoy 20% discounts!");
        }
        customerRepository.save(customer);

        // logic for applying discounts
        Orders order = new Orders();
        int ordersCount = customer.getOrderCount();
        double totalAmount = orderRequest.getTotalAmount();
        double discountAmount = 0.0;
        if ( ordersCount >= 10 && orderCount < 20) {
            discountAmount = totalAmount * 0.1;
            order.setDiscountPercentage(0.1F);

        } else if (ordersCount >= 20) {
            discountAmount = totalAmount * 0.2;
            order.setDiscountPercentage(0.2F);

        } else{
            discountAmount = 0;
            order.setDiscountPercentage(0.0F);
        }
        customer.setOrderCount(ordersCount +1);
        order.setCreatedOn(Instant.now());
        order.setCustomer(customer);
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(totalAmount);
        // save order to repository
        orderRepository.save(order);
        return  order;
    }

    void sendMail(String s) {
        System.out.println(s);
    }

    public List<Orders> getCustomerOrders(Long customerId) {
        return orderRepository.findByCustomer(customerId);
    }
}
