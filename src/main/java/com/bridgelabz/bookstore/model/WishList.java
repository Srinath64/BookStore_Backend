package com.bridgelabz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wishlist_service")
public @Data class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wishlist_id;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private Long quantity;

    public WishList() {
    }

    public WishList(User user, Book book, Long quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }

}
