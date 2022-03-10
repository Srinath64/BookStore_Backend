package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class CartServiceDTO {

    public String user_id;

    public Long book_id;

    public Long quantity;

}
