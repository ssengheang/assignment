package com.example.groupassessment.enitity;

import com.example.groupassessment.enitity.address.ProvinceOrCity;
import com.example.groupassessment.enitity.enum_data_type.PidType;
import com.example.groupassessment.utils.BaseEntity;
import com.example.groupassessment.utils.Person;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(name = "pid_type", length = 32, columnDefinition = "varchar(32) default 'IDENTITY_CARD'")
    @Enumerated(value = EnumType.STRING)
    private PidType pidType = PidType.IDENTITY_CARD;

    @JsonManagedReference
    @OneToMany(mappedBy = "borrower", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Loan> loan;

    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY)
    private List<Repayment> repayment;

    @JsonManagedReference
    @OneToMany(mappedBy = "borrower", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BorrowerImage> borrowerImages;

    @JsonManagedReference
    @OneToMany(mappedBy = "borrower", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contract> contract;

    @JsonManagedReference
    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "address_id", nullable = true)
    private ProvinceOrCity provinceOrCity;
}
