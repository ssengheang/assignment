package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.account.Role ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepo extends JpaRepository<Role, Long> {

}