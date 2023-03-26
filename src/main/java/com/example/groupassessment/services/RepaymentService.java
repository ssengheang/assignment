package com.example.groupassessment.services;

import com.example.groupassessment.repository.RepaymentRepo;

public class RepaymentService {
    private RepaymentRepo repaymentRepo;

    public RepaymentService(RepaymentRepo repaymentRepo){
        this.repaymentRepo = repaymentRepo;
    }
}
