package com.bridgelabz.bookstore.controller;

import java.util.List;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    IBookService bookService;

    /*
       Purpose : Get all the books from the database
     */

    @GetMapping(value = {"","/"})
    List<Book> getBooks(){
//		return new ResponseEntity<ResponseDTO>(new ResponseDTO("Get call success",
//							bookService.getBooks()), HttpStatus.OK);
        return bookService.getBooks();
    }

    /*
       Purpose : Get all the books from the database based on Book_id
     */

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable("bookId") Long Book_id) {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Get call success",
                bookService.getBookById(Book_id)), HttpStatus.OK);
    }

    /*
       Purpose : Get all the books from the database based on book name
     */

    @GetMapping(value = "bookname/{name}")
    public List<Book> getBookByName(@PathVariable("name") String name) {
        return bookService.getBookByName(name);
    }

    /*
       Purpose : Sort all the books based on the price
     */

    @GetMapping(value = "sort/{name}")
    public List<Book> sortHighToLow(@PathVariable("name") int name) {
        if(name == 1) {
            return bookService.sortByPriceHighToLOw("price");
        }else if (name == 2) {
            return bookService.sortByPriceLowToHigh("price");
        }else {
            return bookService.sortById();
        }
    }

    /*
       Purpose : Add new book to the database
     */

    @PostMapping("/add")
    ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDTO){

        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Post call success",
                bookService.addBook(bookDTO)), HttpStatus.OK);
    }

    /*
       Purpose : Update the book details based on book_id
     */

    @PutMapping("/update/{bookId}")
    ResponseEntity<ResponseDTO> updateBook(@PathVariable("bookId") Long Book_id, @RequestBody BookDTO bookDTO){

        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Put call success",
                bookService.updateBook(Book_id, bookDTO)), HttpStatus.OK);
    }

    /*
       Purpose : Delete the book using book_id
     */

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("deleted adressBook data with personId :", bookId),
                HttpStatus.OK);
    }

    /*
       Purpose : Change the price of the book using token
     */

    @PostMapping("/changeprice/{token}")
    public ResponseEntity<ResponseDTO> changePrice(@PathVariable("token") String token, @RequestParam(name = "book_id") Long book_id, @RequestParam Float price) {
        bookService.changeBookPrice(token, book_id, price);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("deleted adressBook data with personId :", bookService.getBookById(book_id)),
                HttpStatus.OK);
    }

    /*
       Purpose : Change the Quantity of the book using token
     */

    @PostMapping("/changequantity/{token}")
    public ResponseEntity<ResponseDTO> changeQuantity(@PathVariable("token") String token, @RequestParam(name = "book_id") Long book_id, @RequestParam Long quantity) {
        bookService.changeBookQuantity(token, book_id, quantity);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("deleted adressBook data with personId :", bookService.getBookById(book_id)),
                HttpStatus.OK);
    }

}

