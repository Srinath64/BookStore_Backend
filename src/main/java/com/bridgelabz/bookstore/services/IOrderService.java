package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.model.Order;

import java.util.List;

public interface IOrderService {

    Order getBookById(Long order_id);

    Order placeOrder(String token, OrderDTO orderDTO);

    Order updateOrder(Long order_id, OrderDTO orderDTO);

    List<Order> getAllOrders();

    List<Order> getAllOrderForUser(String token);

    String cancelOrder(Long order_id);

}
