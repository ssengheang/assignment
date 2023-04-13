
package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.account.Log ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LogsRepo extends JpaRepository<Log, Long> {

}