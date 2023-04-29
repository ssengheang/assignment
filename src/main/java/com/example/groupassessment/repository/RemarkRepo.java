package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Remark;
import com.example.groupassessment.enitity.projection.RemarkProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RemarkRepo extends JpaRepository<Remark, Long> {
    Optional<RemarkProjection> findRemarkProjectionById(Long id);
    Page<RemarkProjection> findAllBy(Pageable pageable);
}
