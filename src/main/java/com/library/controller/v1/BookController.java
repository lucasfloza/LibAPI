package com.library.controller.v1;

import com.library.controller.v1.dto.BookDto;
import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    public BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<Book> getAllBooks(@PathVariable Long idBook) {
        Book book = bookService.getById(idBook);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        Book book = bookService.create(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idBook}")
    public ResponseEntity<Book> updateBook(@PathVariable Long idBook, @RequestBody BookDto bookDto) {
        Book book = bookService.update(idBook, bookDto);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idBook}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long idBook) {
        bookService.delete(idBook);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
