package com.example.groupassessment.enitity;

import com.example.groupassessment.utils.BaseEntity;
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
public class Borrower extends BaseEntity {
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "phone", length = 10, nullable = false)
    private String phone;
    @Column(name = "address", length = 200, nullable = false)
    private String address;
    @Column(name = "employment_status", length = 50, nullable = false)
    private String employmentStatus;
    @Column(name = "marital_status", length = 30, nullable = false)
    private String maritalStatus;

    @OneToMany(mappedBy = "borrower")
    private List<Liability> liability;

    @OneToMany(mappedBy = "borrower")
    private List<Repayment> repayment;
}
