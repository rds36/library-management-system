package com.rds.librarymanagementsystem.controller;

import com.rds.librarymanagementsystem.entity.Book;
import com.rds.librarymanagementsystem.model.create.CreateBookRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookDataRequest;
import com.rds.librarymanagementsystem.model.update.UpdateBookStatusDataRequest;
import com.rds.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBook();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/books/filter", produces = "application/json")
    public ResponseEntity<List<Book>> getAllBooksWithFilterStatus(@RequestParam("status") int status){
        List<Book> books = bookService.getAllBook(status);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(value = "/books/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createBook(@Valid @RequestBody CreateBookRequest createBookRequest){
        bookService.createBook(createBookRequest);
        return new ResponseEntity<>("Book with id " + createBookRequest.getBookId() + " has been created successfully.", HttpStatus.CREATED);
    }

    @PutMapping(value = "/books/data/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateBookData(@Valid @RequestBody UpdateBookDataRequest updateBookDataRequest){
        bookService.updateBookData(updateBookDataRequest);
        return new ResponseEntity<>("Book data with id " + updateBookDataRequest.getBookId() + " has been updated successfully.", HttpStatus.OK);
    }

    @PutMapping(value = "/books/status/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateBookStatusData(@Valid @RequestBody UpdateBookStatusDataRequest updateBookStatusDataRequest){
        bookService.updateBookStatusData(updateBookStatusDataRequest);
        return new ResponseEntity<>("Book status with id " + updateBookStatusDataRequest.getBookId() + " has been updated successfully.", HttpStatus.OK);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") String bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book with id " + bookId + " has been deleted successfully", HttpStatus.OK);
    }
}
