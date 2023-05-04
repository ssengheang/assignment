package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.BorrowerImage;
import com.example.groupassessment.enitity.projection.BorrowerImageProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowerImageRepo extends JpaRepository<BorrowerImage, Long> {
    Optional<BorrowerImageProjection> findBorrowerImageProjectionById(Long id);
    Page<BorrowerImageProjection> findAllBy(Pageable pageable);
}
