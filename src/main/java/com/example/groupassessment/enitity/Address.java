package com.example.groupassessment.enitity;

import com.example.groupassessment.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "addresses")
public class Address extends BaseEntity {
    @Column(name = "province_or_city", nullable = false, length = 100)
    private String provinceOrCity;

    @Column(name = "district", nullable = false, length = 100)
    private String district;

    @Column(name = "commune", nullable = false, length = 100)
    private String commune;

    @Column(name = "village", nullable = false, length = 100)
    private String village;

    @Column(name = "street_number", nullable = false, length = 10)
    private String streetNumber;

    @Column(name = "house_number", nullable = false, length = 10)
    private String houseNumber;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "borrower_id", nullable = false, updatable = false)
    private Borrower borrower;
}
