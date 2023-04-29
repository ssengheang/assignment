package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Contract;
import com.example.groupassessment.enitity.projection.ContractProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepo extends JpaRepository<Contract, Long> {
    Optional<ContractProjection> findContractProjectionById(Long id);
    List<ContractProjection> findAllBy();
}
