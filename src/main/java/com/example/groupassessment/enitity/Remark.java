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
@Table(name = "remarks")
public class Remark extends BaseEntity {
    @Column(name = "remark", length = 255, nullable = true)
    private String remark;
    @Column(name = "reason", length = 100, nullable = false)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false, insertable = false, updatable = false)
    private Loan loan;
}
