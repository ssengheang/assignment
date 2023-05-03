package com.example.groupassessment.service;

import com.example.groupassessment.enitity.account.User;
import com.example.groupassessment.enitity.response.AuthenticationResponse;
import com.example.groupassessment.request_param.auth.AuthenticationRequest;
import com.example.groupassessment.request_param.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
//    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;
}
