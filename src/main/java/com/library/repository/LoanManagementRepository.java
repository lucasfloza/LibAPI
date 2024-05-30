package com.library.repository;

import com.library.entity.LoanManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanManagementRepository extends JpaRepository<LoanManagement, Long> {
}
