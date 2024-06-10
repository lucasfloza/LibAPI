package com.library.service;

import com.library.controller.v1.dto.LendAndReturnBookDto;
import com.library.controller.v1.dto.ResponseLeanDto;
import com.library.controller.v1.dto.ResponseReturnBookDto;
import com.library.entity.Book;
import com.library.entity.Customer;
import com.library.entity.LoanManagement;
import com.library.entity.Status;
import com.library.exeptions.LoanManagementException;
import com.library.repository.LoanManagementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanManagementService {

    public LoanManagementRepository repository;

    public CustomerService customerService;

    public BookStockService bookStockService;

    public BookService bookService;

    public LoanManagementService(LoanManagementRepository repository, CustomerService customerService, BookStockService bookStockService, BookService bookService) {
        this.repository = repository;
        this.customerService = customerService;
        this.bookStockService = bookStockService;
        this.bookService = bookService;
    }

    public ResponseLeanDto lend(LendAndReturnBookDto lendBookDto) {
        Customer customer = customerService.getById(lendBookDto.customerId());
        Book book = bookService.getById(lendBookDto.bookId());

        if (customer.getStatus() == Status.LOCKED)
            throw new LoanManagementException("Customer with Loked status. ");
        else if (repository.countByCustomerId(customer.getId()) >= 5)
            throw new LoanManagementException("Customer has reached the limit of borrowed books.");
        else if (repository.existsByCustomerIdAndBookId(customer.getId(), book.getId())) {
            throw new LoanManagementException("The Customer already has a loan with that book, and he cannot borrow" +
                    " two or more of the same books.");
        } else if (bookStockService.getById(book.getId()).getAvailableQuantity() <= 0)
            throw new LoanManagementException("No copies are available in this book. ");

        bookStockService.removingBookAvailability(book);
        LoanManagement loan = repository.save(new LoanManagement(customer, book));

        return new ResponseLeanDto(customer.getFullName(), book.getTitle(), loan.getLoanDate(),
                loan.getReturnDate());
    }

    public ResponseReturnBookDto returnBook(LendAndReturnBookDto returnBookDto) {
        Customer customer = customerService.getById(returnBookDto.customerId());
        Book book = bookService.getById(returnBookDto.bookId());

        Optional<LoanManagement> loan = repository.findByCustomerIdAndBookId(customer.getId(), book.getId());
        loan.ifPresent(loanManagement -> repository.delete(loanManagement));
        return new ResponseReturnBookDto(customer.getFullName(), book.getTitle(), LocalDate.now());
    }
}
