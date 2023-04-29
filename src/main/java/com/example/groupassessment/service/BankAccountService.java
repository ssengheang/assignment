package com.example.groupassessment.service;

import com.example.groupassessment.enitity.BankAccount;
import com.example.groupassessment.enitity.projection.BankAccountProjection;
import com.example.groupassessment.request_param.bank_account.*;

import java.util.List;

public interface BankAccountService {
    List<BankAccountProjection> index();
    BankAccountProjection show(Long id);
    BankAccount update(Long id, UpdateReqParam bankAccount);
    BankAccount create(CreateReqParam bankAccount);
    Boolean delete(Long id);
}
