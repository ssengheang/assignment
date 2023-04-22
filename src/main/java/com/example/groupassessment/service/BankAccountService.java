package com.example.groupassessment.service;

import com.example.groupassessment.enitity.BankAccount;
import com.example.groupassessment.request_param.bank_account.*;

import java.util.List;

public interface BankAccountService {
    List<BankAccount> index();
    BankAccount show(Long id);
    BankAccount update(Long id, UpdateReqParam bankAccount);
    BankAccount create(CreateReqParam bankAccount);
    String delete(Long id);
}
