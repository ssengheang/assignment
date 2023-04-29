package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.account.User;
import com.example.groupassessment.enitity.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<UserProjection> findUserProjectionById(Long id);
    Page<UserProjection> findAllBy(Pageable pageable);
}
