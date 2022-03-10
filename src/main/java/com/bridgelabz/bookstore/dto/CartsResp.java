package com.bridgelabz.bookstore.dto;

import java.util.List;

import com.bridgelabz.bookstore.model.Cart;
import lombok.Data;

@Data
public class CartsResp {

    private String message;
    private List<Cart> data;

    public CartsResp(String message, List<Cart> data) {
        this.message = message;
        this.data = data;
    }

}
