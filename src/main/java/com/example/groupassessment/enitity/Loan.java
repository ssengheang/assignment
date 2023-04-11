package com.example.groupassessment.enitity;

import com.example.groupassessment.enitity.enum_data_type.LoanStatus;
import com.example.groupassessment.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "loans")
public class Loan extends BaseEntity {
    @Column(name = "loan_amount", nullable = false)
    private Float loanAmount;
    @Column(name = "date", nullable = false)
//    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "interest", nullable = false)
    private Integer interest;
    @Column(name = "amount_left", nullable = false)
    private Float amountLeft;

    @Column(name = "status", nullable = false, length = 32, columnDefinition = "varchar(32) default 'PENDING'")
    @Enumerated(value = EnumType.STRING)
    private LoanStatus status = LoanStatus.PENDING;

    //relationship (borrower_id)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false, updatable = false)
    private Borrower borrower;
    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY)
    private List<Asset> asset;

    @JsonManagedReference
    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY)
    private List<Contract> contracts;

//    @ManyToOne
//    @JoinColumn(name = "owner_id", nullable = false, insertable = false, updatable = false)
//    private Owner owner;

    @OneToMany(mappedBy = "loan", fetch = FetchType.LAZY)
    private List<Remark> remarks;
}


