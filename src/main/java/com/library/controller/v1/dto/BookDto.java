package com.library.controller.v1.dto;

import com.library.entity.Book;

public record BookDto(String publisher, String publishDate, String category, String author, String title) {

    public Book toBook(){
        return new Book(publisher, publishDate, category, author, title);
    }
}
