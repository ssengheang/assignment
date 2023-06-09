package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.projection.BorrowerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowerRepo extends JpaRepository<Borrower, Long> {
    Optional<BorrowerProjection> findBorrowerProjectionById(Long id);
    Page<BorrowerProjection> findAllBy(Pageable pageable);
}
