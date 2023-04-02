package com.example.groupassessment.enitity.address;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.Owner;
import com.example.groupassessment.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "province_or_city")
public class ProvinceOrCity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "provinceOrCity")
    private List<District> districts;

    @OneToMany(mappedBy = "provinceOrCity")
    private List<Commune> communes;

    @OneToOne(mappedBy = "provinceOrCity")
    private Borrower borrower;

    @OneToOne(mappedBy = "provinceOrCity")
    private Owner owner;
}
