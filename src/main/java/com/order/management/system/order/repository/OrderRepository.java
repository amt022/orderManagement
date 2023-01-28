package com.order.management.system.order.repository;

import com.order.management.system.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("Select o from Orders o where o.customer.id = :id")
    List<Orders> findByCustomer(Long id);
}
