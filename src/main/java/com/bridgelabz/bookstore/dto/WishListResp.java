package com.bridgelabz.bookstore.dto;


import com.bridgelabz.bookstore.model.WishList;
import lombok.Data;

import java.util.List;

@Data
public class WishListResp {

    private String message;
    private List<WishList> data;

    public WishListResp(String message, List<WishList> data) {
        this.message = message;
        this.data = data;
    }
}
