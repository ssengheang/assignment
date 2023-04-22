package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
}
