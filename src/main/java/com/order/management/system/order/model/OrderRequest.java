package com.order.management.system.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private long customerId;
    private double totalAmount;

}
