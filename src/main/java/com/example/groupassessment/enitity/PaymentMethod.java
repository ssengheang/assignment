package com.example.groupassessment.enitity;

import com.example.groupassessment.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "payment_methods")
public class PaymentMethod extends BaseEntity {
    @Column(name = "method_name", nullable = false)
    private String methodName;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = true)
    private Bank bank;

    @JsonManagedReference
    @OneToMany(mappedBy = "paymentMethod")
    private List<Repayment> repayment;

}
