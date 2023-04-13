package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RepaymentRepo extends JpaRepository<Repayment, Long> {

}