package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.BankAccount;
import com.example.groupassessment.request_param.bank_account.*;
import com.example.groupassessment.service.serviceImp.BankAccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {
    private BankAccountServiceImp bankAccountServiceImp;
    @Autowired
    public BankAccountController(BankAccountServiceImp bankAccountServiceImp){
        this.bankAccountServiceImp = bankAccountServiceImp;
    }

    @PostMapping("")
    public BankAccount createBankAccount(@Validated @RequestBody CreateReqParam bankAccount){
        return bankAccountServiceImp.create(bankAccount);
    }

    @GetMapping("")
    public List<BankAccount> listBankAccount(){
        return bankAccountServiceImp.index();
    }

    @GetMapping("/{id}")
    public BankAccount getBankById(@PathVariable(name = "id") Long id){
        return bankAccountServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public BankAccount updateBankAccount(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam bankAccount){
        return bankAccountServiceImp.update(id, bankAccount);
    }

    @DeleteMapping("/{id}")
    public String deleteBankAccount(@PathVariable(name = "id") Long id){
        return bankAccountServiceImp.delete(id);
    }
}
