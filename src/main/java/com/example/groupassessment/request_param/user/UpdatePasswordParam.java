package com.example.groupassessment.request_param.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordParam {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
