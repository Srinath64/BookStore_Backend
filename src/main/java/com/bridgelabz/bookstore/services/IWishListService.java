package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.WishListDTO;
import com.bridgelabz.bookstore.model.WishList;

import java.util.List;

public interface IWishListService {

    WishList addWishList(WishListDTO wishListDTO);

    void removeFromWishList(Long wishlist_id);

    void removeAllWishList(String token);

    List<WishList> findAllWishList(String token);

}
