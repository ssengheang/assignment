package com.example.groupassessment.services;

import com.example.groupassessment.repository.RepaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class RepaymentService {
    private RepaymentRepo repaymentRepo;

    @Autowired
    public RepaymentService(RepaymentRepo repaymentRepo){
        this.repaymentRepo = repaymentRepo;
    }
}
