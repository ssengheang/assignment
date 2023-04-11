package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.BorrowerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerImageRepo extends JpaRepository<BorrowerImage, Long> {
}
