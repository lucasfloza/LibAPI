package com.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_loan_management")
public class LoanManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="custumer_id")
    private Customer custumer;

    @ManyToOne
    @JoinColumn(name ="book_id")
    private Book book;

    @Column(name = "loan_date")
    private String loanDate;

    @Column(name = "returnDate")
    private String returnDate;

    public LoanManagement() {
    }

    public LoanManagement(Customer custumer, Book book, String loanDate, String returnDate) {
        this.custumer = custumer;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustumer() {
        return custumer;
    }

    public void setCustumer(Customer custumer) {
        this.custumer = custumer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
