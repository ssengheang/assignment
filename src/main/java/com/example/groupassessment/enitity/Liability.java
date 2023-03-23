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
@Table(name = "liabilities")
public class Liability extends BaseEntity {
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
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private Borrower borrower;
    @OneToMany(mappedBy = "liability")
    private List<Asset> asset;
}
