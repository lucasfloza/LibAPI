package com.library.service;

import com.library.controller.v1.dto.BookDto;
import com.library.entity.Book;
import com.library.exeptions.MissingDataException;
import com.library.exeptions.NotFoundException;
import com.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book getById(Long idBook) {
        Optional<Book> book = repository.findById(idBook);

        if (book.isEmpty())
            throw new NotFoundException("There is no book with this ID. Please try again with a valid ID");

        return book.get();
    }

    public List<Book> getAll() {
        List<Book> allBooks = repository.findAll();

        return allBooks;
    }

    public Book create(BookDto bookDto) {
        Book book = bookDto.toBook();

        if ((book.getAuthor() == null || book.getAuthor().isEmpty()) ||
                (book.getTitle() == null || book.getTitle().isEmpty()) ||
                (book.getPublishDate() == null))
            throw new MissingDataException("The request came with the mandatory book data missing or empty, " + "please review the request. (Mandatory attributes: publishDate, author and title)");

        return repository.save(book);
    }

    public Book update(Long idBook, BookDto bookDto) {
        Optional<Book> book = repository.findById(idBook);

        if (book.isEmpty())
            throw new NotFoundException("This book ID is not saved in our system. " + "Please enter a valid ID");

        Book oldBook = book.get();
        Book newBook = bookDto.toBook();

        String textError = "The request came with the mandatory book data missing or empty";
        if (newBook.getAuthor() != null) {
            if (newBook.getAuthor().isEmpty()) throw new MissingDataException(textError);
            else oldBook.setAuthor(newBook.getAuthor());
        }

        if (newBook.getCategory() != null) {
            if (newBook.getCategory().isEmpty()) throw new MissingDataException(textError);
            else oldBook.setCategory(newBook.getCategory());
        }

        if (newBook.getPublisher() != null) {
            if (newBook.getPublisher().isEmpty()) throw new MissingDataException(textError);
            else oldBook.setPublisher(newBook.getPublisher());
        }

        if (newBook.getPublishDate() != null) oldBook.setPublishDate(newBook.getPublishDate());

        if (newBook.getTitle() != null) {
            if (newBook.getTitle().isEmpty()) throw new MissingDataException(textError);
            else oldBook.setTitle(newBook.getTitle());
        }

        return oldBook;
    }

    public void delete(Long idBook) {
        Optional<Book> book = repository.findById(idBook);

        if (book.isEmpty())
            throw new NotFoundException("This book ID is not saved in our system. Please enter a valid ID");

        repository.delete(book.get());
    }
}
