package com.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_book_stock")
public class BookStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="book_id", unique = true)
    private Book book;

    @Column(name = "total_quantity")
    private Integer totalQuantity = 0;

    @Column(name = "available_quantity")
    private Integer availableQuantity = 0;

    public BookStock() {
    }

    public BookStock(Book book, Integer totalQuantity) {
        this.book = book;
        this.totalQuantity = totalQuantity;
    }

    public BookStock(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
