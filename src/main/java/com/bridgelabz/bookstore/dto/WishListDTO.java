package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class WishListDTO {

    public String user_id;

    public Long book_id;

    public Long quantity;
}
