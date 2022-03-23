package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.WishListDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.model.WishList;
import com.bridgelabz.bookstore.repo.WishListRepository;
import com.bridgelabz.bookstore.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListService implements IWishListService{

    @Autowired
    WishListRepository wishlistRepo;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    Token myToken;

    @Override
    public WishList addWishList(WishListDTO wishListDTO) {
        Long user_id = myToken.decodeToken(wishListDTO.user_id);
        Optional<User> user = userService.getUserById(user_id);
        if(user.isPresent()) {
            Book book = bookService.getBookById(wishListDTO.book_id);
            WishList wishList = new WishList(user.get(), book, wishListDTO.quantity);
            return wishlistRepo.save(wishList);

        }
        return null;
    }

    @Override
    public void removeFromWishList(Long wishlist_id) {
        System.out.println("Deleted");
        wishlistRepo.deleteById(wishlist_id);
    }

    @Override
    public void removeAllWishList(String token) {
        Long user_id = myToken.decodeToken(token);
        Optional<User> user = userService.getUserById(user_id);
        if(user.isPresent()) {
            List<WishList> wishLists = wishlistRepo.findAllWishListsByUserId(user_id);
            wishlistRepo.deleteAll(wishLists);
        }
    }

    @Override
    public List<WishList> findAllWishList(String token) {
        Long user_id = myToken.decodeToken(token);
        Optional<User> user = userService.getUserById(user_id);
        if (user.isPresent()) {
            List<WishList> wishLists = wishlistRepo.findAllWishListsByUserId(user_id);
            return wishLists;
        }
        return null;
    }
}
