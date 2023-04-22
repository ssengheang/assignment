package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Long> {
}
