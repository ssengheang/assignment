package com.example.groupassessment.enitity;

import com.example.groupassessment.enitity.address.ProvinceOrCity;
import com.example.groupassessment.utils.BaseEntity;
import com.example.groupassessment.utils.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "owners")
public class Owner extends Person {
    @Column(name = "phone", length = 10, nullable = false)
    private String phone;
    @Column(name = "pid_number", length = 50, nullable = false)
    private String pidNumber;
    @Column(name = "pid_type", length = 2, nullable = false)
    private String pidType;

    @OneToMany(mappedBy = "owner")
    private List<Loan> loans;

    @OneToOne
    @JoinColumn(name = "address_id")
    private ProvinceOrCity provinceOrCity;
}
