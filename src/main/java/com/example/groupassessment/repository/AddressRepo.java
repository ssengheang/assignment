package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
