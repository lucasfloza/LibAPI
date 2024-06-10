package com.library.repository;

import com.library.entity.LoanManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanManagementRepository extends JpaRepository<LoanManagement, Long> {

    int countByCustomerId(Long aLong);

    boolean existsByCustomerIdAndBookId(Long customerId, Long bookId);

    Optional<LoanManagement> findByCustomerIdAndBookId(Long customerId, Long bookId);
}
