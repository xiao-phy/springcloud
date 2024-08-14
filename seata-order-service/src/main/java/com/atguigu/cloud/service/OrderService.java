package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Order;
import org.springframework.stereotype.Service;


public interface OrderService {

    /**
     * 创建订单
     * @param order
     */
    void create(Order order);

}
