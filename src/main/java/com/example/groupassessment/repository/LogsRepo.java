package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LogsRepo extends JpaRepository<Logs, Long> {
}