package com.unir.operator.service;

import com.unir.operator.controller.model.OperatorRequest;
import com.unir.operator.data.model.Order;

import java.util.List;

public interface OperatorService {
    Order createOrder(OperatorRequest operator);

    Order getOrder(String id);

    List<Order> getOrders();
}
