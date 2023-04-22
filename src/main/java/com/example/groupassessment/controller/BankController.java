package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.request_param.bank.ReqParam;
import com.example.groupassessment.service.serviceImp.BankServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/banks")
public class BankController {
    private BankServiceImp bankServiceImp;
    @Autowired
    public BankController(BankServiceImp bankServiceImp){
        this.bankServiceImp = bankServiceImp;
    }

    @PostMapping("")
    public Bank createBank(@Validated @RequestBody ReqParam bank){
        return bankServiceImp.create(bank);
    }

    @GetMapping("")
    public List<Bank> listBank(){
        return bankServiceImp.index();
    }

    @GetMapping("/{id}")
    public Bank getBankById(@PathVariable(name = "id") Long id){
        return bankServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Bank updateBank(@PathVariable(name = "id") Long id, @Validated @RequestBody ReqParam bank){
        return bankServiceImp.update(id, bank);
    }

    @DeleteMapping("/{id}")
    public String deleteBank(@PathVariable(name = "id") Long id){
        return bankServiceImp.delete(id);
    }
}
