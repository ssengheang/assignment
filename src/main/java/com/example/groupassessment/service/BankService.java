package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.enitity.projection.BankProjection;
import com.example.groupassessment.request_param.bank.ReqParam;

import java.util.List;

public interface BankService {
    List<BankProjection> index();
    BankProjection show(Long id);
    Bank update(Long id, ReqParam bank);
    Bank create(ReqParam bank);
    Boolean delete(Long id);
}
