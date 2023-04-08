package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<Loan, Long> {
}
