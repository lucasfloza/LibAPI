package com.library.controller.v1.dto;

import com.library.entity.Book;
import com.library.exeptions.InvalidFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public record BookDto(String publisher, String publishDate, String category, String author, String title) {

    public Book toBook(){
        LocalDate publishLocalDate;
        try {
            publishLocalDate = LocalDate.parse(publishDate);
        }catch (DateTimeParseException e){
            throw new InvalidFormatException(e.getMessage());
        }
        return new Book(publisher, publishLocalDate, category, author, title);
    }
}
