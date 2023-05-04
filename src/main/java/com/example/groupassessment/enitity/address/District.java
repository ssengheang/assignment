//package com.example.groupassessment.enitity.address;
//
//import com.example.groupassessment.utils.BaseEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "districts")
//public class District extends BaseEntity {
//    @Column(name = "district_name", length = 100, nullable = false)
//    private String districtName;
//
//    @ManyToOne
//    @JoinColumn(name = "province_or_city_id", nullable = false, insertable = false, updatable = false)
//    private ProvinceOrCity provinceOrCity;
//}
