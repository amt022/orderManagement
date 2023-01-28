package com.order.management.system.order.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "discount_amount")
    private double discountAmount;
    @Column(name = "discount_percentage")
    private float discountPercentage;
    @Column(name = "created_on")
    private Instant createdOn;
    @Column(name = "discount_claimed")
    private boolean discountClaimed = false;

}
