package com.bridgelabz.bookstore.repo;

import java.util.List;

import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book_store where name = :name", nativeQuery = true)
    public List<Book> getBookByName(String name);

    @Query(value = "select * from bookstore_db.book_store order by book_id desc", nativeQuery = true)
    public List<Book> sortByIDDesc();

}

