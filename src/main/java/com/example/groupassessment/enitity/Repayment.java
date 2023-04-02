package com.example.groupassessment.enitity;

import com.example.groupassessment.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "repayments")
public class Repayment extends BaseEntity {
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "amount", nullable = false)
    private Float amount;
    @Column(name = "interest_due", nullable = false)
    private Float interestDue;

    //relationships (borrower_id, liability_id)

    @ManyToOne
    @JoinColumn(name = "borrower_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Borrower borrower;

    @ManyToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private PaymentMethod paymentMethod;
}
