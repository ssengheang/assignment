package com.example.groupassessment.request_param.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqParam {
    private String feature;
    private List<String> actions;
}
