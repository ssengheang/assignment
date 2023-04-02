package com.example.groupassessment.enitity;

import com.example.groupassessment.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date date;
    @Column(name = "interest", nullable = false)
    private Integer interest;
    @Column(name = "amount_left", nullable = false)
    private Float amountLeft;

    //relationship (borrower_id)
    @ManyToOne
    @JoinColumn(name = "borrower_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Borrower borrower;
    @OneToMany(mappedBy = "loan")
    private List<Asset> asset;

    @OneToMany(mappedBy = "loan")
    private List<Contract> contracts;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false, insertable = false, updatable = false)
    private Owner owner;

    @OneToMany(mappedBy = "loan")
    private List<Remark> remarks;
}
