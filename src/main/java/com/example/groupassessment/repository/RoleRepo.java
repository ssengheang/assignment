package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.account.Role;
import com.example.groupassessment.enitity.projection.RoleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<RoleProjection> findRoleProjectionById(Long id);
    Page<RoleProjection> findAllBy(Pageable pageable);

    Role findByTitle(String title);
}
