
package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.account.Permission ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PermissionRepo extends JpaRepository<Permission, Long> {

}