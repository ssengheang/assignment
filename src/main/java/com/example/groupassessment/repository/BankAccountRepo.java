package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.BankAccount;
import com.example.groupassessment.enitity.projection.BankAccountProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
    Optional<BankAccountProjection> findBankAccountProjectionById(Long id);
    Page<BankAccountProjection> findAllBy(Pageable pageable);
}
