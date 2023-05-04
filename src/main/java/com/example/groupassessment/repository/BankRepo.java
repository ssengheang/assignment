package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.enitity.projection.BankProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankRepo extends JpaRepository<Bank, Long> {
    Optional<BankProjection> findBankProjectionById(Long id);
    Page<BankProjection> findAllBy(Pageable pageable);
}
