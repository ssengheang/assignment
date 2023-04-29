package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.projection.LoanProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepo extends JpaRepository<Loan, Long> {
    Optional<LoanProjection> findLoanProjectionById(Long id);
    Page<LoanProjection> findAllBy(Pageable pageable);
}
