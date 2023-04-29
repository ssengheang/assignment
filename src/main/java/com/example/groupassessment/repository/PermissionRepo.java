package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.account.Permission;
import com.example.groupassessment.enitity.projection.PermissionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepo extends JpaRepository<Permission, Long> {
    Permission findByFeature(String feature);
    Optional<PermissionProjection> findPermissionProjectionById(Long id);
    Page<PermissionProjection> findAllBy(Pageable pageable);
}
