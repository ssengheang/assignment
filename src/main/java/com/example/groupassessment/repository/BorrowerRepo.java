package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepo extends JpaRepository<Borrower, Long> {
}
