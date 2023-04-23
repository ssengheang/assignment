package com.example.groupassessment.request_param.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReqParam {
    private String email;
    private String username;
    private String password;
    private Long roleId;
}
