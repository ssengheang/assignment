package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.account.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}