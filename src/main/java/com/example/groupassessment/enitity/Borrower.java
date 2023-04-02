package com.example.groupassessment.enitity;

import com.example.groupassessment.enitity.address.ProvinceOrCity;
import com.example.groupassessment.utils.BaseEntity;
import com.example.groupassessment.utils.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "borrowers")
public class Borrower extends Person {
    @Column(name = "phone", length = 10, nullable = false)
    private String phone;
    @Column(name = "pid_number", length = 50, nullable = false)
    private String pidNumber;
    @Column(name = "pid_type", length = 2, nullable = false)
    private String pidType;

    @OneToMany(mappedBy = "borrower")
    private List<Loan> loan;

    @OneToMany(mappedBy = "borrower")
    private List<Repayment> repayment;

    @OneToMany(mappedBy = "borrower")
    private List<BorrowerImage> borrowerImages;

    @OneToMany(mappedBy = "borrower")
    private List<Contract> contract;

    @OneToMany(mappedBy = "borrower")
    private List<BankAccount> bankAccounts;

    @OneToOne
    @JoinColumn(name = "address_id")
    private ProvinceOrCity provinceOrCity;
}
