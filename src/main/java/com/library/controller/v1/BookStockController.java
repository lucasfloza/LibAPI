package com.library.controller.v1;

import com.library.controller.v1.dto.BookStockDto;
import com.library.entity.BookStock;
import com.library.service.BookStockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book-stock")
public class BookStockController {

    public BookStockService stockService;

    public BookStockController(BookStockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{idStock}")
    public ResponseEntity<BookStock> getBookInStock(@PathVariable Long idStock){
        BookStock bookStock = stockService.get(idStock);
        return new ResponseEntity<>(bookStock, HttpStatus.OK);
    }

    @PostMapping("/entry")
    public ResponseEntity<BookStock> entryBook(@RequestBody BookStockDto bookStockDto){

        BookStock bookStock = stockService.entry(bookStockDto);

        return new ResponseEntity<>(bookStock, HttpStatus.OK);
    }

    @PostMapping("/removal")
    public ResponseEntity<BookStock> removalBook(@RequestBody BookStockDto bookStockDto){

        BookStock bookStock = stockService.removal(bookStockDto);

        return new ResponseEntity<>(bookStock, HttpStatus.OK);
    }
}
