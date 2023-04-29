package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Repayment;
import com.example.groupassessment.enitity.projection.RepaymentProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepaymentRepo extends JpaRepository<Repayment, Long> {
    Optional<RepaymentProjection> findRepaymentProjectionById(Long id);
    List<RepaymentProjection> findAllBy();
}
