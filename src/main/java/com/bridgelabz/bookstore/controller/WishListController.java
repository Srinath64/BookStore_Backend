package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.WishListDTO;
import com.bridgelabz.bookstore.model.WishList;
import com.bridgelabz.bookstore.services.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WishListController {

    @Autowired
    IWishListService wishListService;

    /*
       Purpose : To add a book to the wishlist
     */

    @PostMapping("/add")
    ResponseEntity<ResponseDTO> addToWishList(@RequestBody WishListDTO wishListDTO){

        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Post call success",
                wishListService.addWishList(wishListDTO)), HttpStatus.OK);
    }

    /*
       Purpose : To remove a book from the WishList using wishlist_id
     */

    @DeleteMapping("/remove/{wishlist_id}")
    ResponseEntity<ResponseDTO> removeFromWishList(@PathVariable("wishlist_id") Long wishlist_id){
        wishListService.removeFromWishList(wishlist_id);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Delete call success",""), HttpStatus.OK);
    }

    /*
       Purpose : To remove all the books from the WishList
     */

    @DeleteMapping("/removeall/{token}")
    ResponseEntity<ResponseDTO> removeAllWishList(@PathVariable("token") String token){
        wishListService.removeAllWishList(token);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Delete call success",""), HttpStatus.OK);
    }

    /*
       Purpose : To get all the books added in the wishlist
     */

    @GetMapping("get/{token}")
    List<WishList> findAllWishList(@PathVariable("token") String token){
        List<WishList> wishLists = wishListService.findAllWishList(token);
        return wishLists;
    }

}
