package com.library.repository;

import com.library.entity.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStockRepository extends JpaRepository<BookStock, Long> {
}
