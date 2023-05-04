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
@Table(name = "contract_images")
public class Contract extends BaseEntity {
    @Column(name = "path", length = 255, nullable = false)
    private String path;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false, updatable = false)
    private Borrower borrower;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false, updatable = false)
    private Loan loan;
}
