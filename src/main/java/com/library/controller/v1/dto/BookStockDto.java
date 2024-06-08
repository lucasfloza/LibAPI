package com.library.controller.v1.dto;

import com.library.entity.Book;
import com.library.entity.BookStock;

public record BookStockDto(Long idBook, Integer quantity) {

    public BookStock toBookStock(Book book){
        return new BookStock(book,quantity);
    }

}
