package com.example.groupassessment.request_param.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqParam {
    private String provinceOrCity;
    private String district;
    private String commune;
    private String village;
    private String houseNumber;
    private String streetNumber;
}
