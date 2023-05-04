//package com.example.groupassessment.enitity.address;
//
//import com.example.groupassessment.utils.BaseEntity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "communes")
//public class Commune extends BaseEntity {
//    @Column(name = "commune_name", length = 100, nullable = false)
//    private String communeName;
//
//    @ManyToOne
//    @JoinColumn(name = "province_or_city_id", nullable = false, insertable = false, updatable = false)
//    private ProvinceOrCity provinceOrCity;
//
//    @OneToMany(mappedBy = "commune")
//    private List<Village> villages;
//}
