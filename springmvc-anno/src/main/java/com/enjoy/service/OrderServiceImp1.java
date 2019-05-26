package com.enjoy.service;

import org.springframework.stereotype.Service;

@Service(value = "OrderServiceImp1")
public class OrderServiceImp1 implements OrderService {
    public String goBuy(String orderId){
        return "OrderServiceImp1 orderId===="+orderId;
    }
}
