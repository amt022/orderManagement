package com.order.management.system.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_email",unique = true, nullable = false)
    private String email;
    @Column(name = "customer_mobile",unique = true, nullable = false)
    private String mobile;
    @Column(name = "order_count")
    private int orderCount;
    @Column(name = "customer_type")
    private String customerType = "regular";
}
