package com.enjoy.service;

import org.springframework.stereotype.Service;

@Service(value = "OrderServiceImp2")
public class OrderServiceImp2 implements OrderService{
    public String goBuy(String orderId){
        return "OrderServiceImp2 orderId===="+orderId;
    }
}
