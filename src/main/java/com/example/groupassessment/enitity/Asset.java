package com.example.groupassessment.enitity;

import com.example.groupassessment.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "assets")
public class Asset extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "estimate_value", nullable = false)
    private Float estimateValue;

    //Two relationships (liability_id, type_id)

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false, updatable = false)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false, updatable = false)
    private Loan loan;
}
