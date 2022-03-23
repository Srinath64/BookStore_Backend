package com.bridgelabz.bookstore.repo;

import com.bridgelabz.bookstore.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList , Long> {

    @Query(value = "SELECT * FROM wishlist_service where user_id = :user_id", nativeQuery = true)
    List<WishList> findAllWishListsByUserId(Long user_id);

}
