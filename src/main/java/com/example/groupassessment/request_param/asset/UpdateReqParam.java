package com.example.groupassessment.request_param.asset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReqParam {
    private String name;
    private Float estimateValue;
}
