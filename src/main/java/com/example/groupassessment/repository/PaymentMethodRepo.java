package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.PaymentMethod;
import com.example.groupassessment.enitity.projection.PaymentMethodProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethodProjection> findPaymentMethodProjectionById(Long id);
    Page<PaymentMethodProjection> findAllBy(Pageable pageable);
}
