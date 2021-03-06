package com.bridgelabz.bookstore.repo;

import com.bridgelabz.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM order_details where cancel = false", nativeQuery = true)
    public List<Order> getAllOrders();

    @Query(value = "SELECT * FROM order_details where user_id = :user_id", nativeQuery = true)
    public List<Order> getAllOrdersByUserId(Long user_id);

    @Modifying(clearAutomatically = true)
    @Query(value = "Update order_details set cancel = true where user_id =:user_id", nativeQuery = true)
    public Order calcelOrder(Long user_id);
}
