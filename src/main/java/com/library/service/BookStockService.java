package com.library.service;

import com.library.controller.v1.dto.BookStockDto;
import com.library.entity.Book;
import com.library.entity.BookStock;
import com.library.exeptions.InvalidMoveException;
import com.library.exeptions.NotFoundException;
import com.library.repository.BookStockRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookStockService {

    private BookStockRepository bookStockRepository;

    public BookStockService(BookStockRepository bookStockRepository) {
        this.bookStockRepository = bookStockRepository;
    }

    public BookStock get(Long id) {
        Optional<BookStock> bookStock = bookStockRepository.findById(id);

        if (bookStock.isEmpty())
            throw new NotFoundException("There is no book in the Stock with this ID. Please try again with a valid ID");

        return bookStock.get();
    }

    public void create(Book book) {
        BookStock bookStock = new BookStock(book);
        bookStockRepository.save(bookStock);
    }

    public BookStock entry(BookStockDto bookStockDto) {
        BookStock bookStock = bookStockRepository.findByBookId(bookStockDto.idBook());

        var totalQuantity = bookStockDto.quantity() != null ? bookStock.getTotalQuantity() + bookStockDto.quantity() :
                bookStock.getTotalQuantity();
        var availableQuantity = bookStockDto.quantity() != null ? bookStock.getAvailableQuantity() +
                bookStockDto.quantity() : bookStock.getAvailableQuantity();

        bookStock.setTotalQuantity(totalQuantity);
        bookStock.setAvailableQuantity(availableQuantity);
        return bookStockRepository.save(bookStock);
    }

    public BookStock removal(BookStockDto bookStockDto) {
        BookStock bookStock = bookStockRepository.findByBookId(bookStockDto.idBook());

        var totalAvailable = 0;

        if (bookStockDto.quantity() != null) {

            var total = bookStock.getAvailableQuantity() - bookStockDto.quantity();

            if (total < 0)
                throw new InvalidMoveException(STR."This movement exceeds the value of books available to remove. (Maximum value of items to remove from stock: \{bookStock.getAvailableQuantity()}");

            totalAvailable = total;
        }

        var totalQuantity = bookStockDto.quantity() != null ? bookStock.getTotalQuantity() - bookStockDto.quantity() :
                bookStock.getTotalQuantity();

        bookStock.setTotalQuantity(totalQuantity);
        bookStock.setAvailableQuantity(totalAvailable);
        return bookStockRepository.save(bookStock);
    }
}
