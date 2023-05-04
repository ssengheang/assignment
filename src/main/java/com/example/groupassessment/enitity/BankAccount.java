package com.example.groupassessment.enitity;

import com.example.groupassessment.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "bank_accounts")
public class BankAccount extends BaseEntity {
    @Column(name = "key_id", length = 25, nullable = false, unique = true)
    private String keyId;
    @Column(name = "account_name", length = 100, nullable = false)
    private String accountName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false, updatable = false)
    private Borrower borrower;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false, updatable = false)
    private Bank bank;
}
