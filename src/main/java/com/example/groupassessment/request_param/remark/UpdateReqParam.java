package com.example.groupassessment.request_param.remark;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReqParam {
    private String remark;
    private String reason;
}
