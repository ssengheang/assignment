package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.BankAccount;
import com.example.groupassessment.exception.ResourceNotFoundException;
import com.example.groupassessment.repository.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {
    private BankAccountRepo bankAccountRepo;
    @Autowired
    private BankAccountController(BankAccountRepo bankAccountRepo){
        this.bankAccountRepo = bankAccountRepo;
    }

    @GetMapping("")
    public List<BankAccount> getAll(){
        return bankAccountRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getOne(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        BankAccount bankAccount = bankAccountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));

        return ResponseEntity.ok().body(bankAccount);
    }

    @PostMapping("")
    public ResponseEntity<BankAccount> create(@Validated @RequestBody BankAccount bankAccount_params){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setCustomer(bankAccount_params.getCustomer());
        BankAccount bankAccount_inserted = bankAccountRepo.save(bankAccount);
        return ResponseEntity.status(201).body(bankAccount_inserted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> update(@PathVariable(value = "id") Long id, @Validated @RequestBody BankAccount bankAccount_params) throws ResourceNotFoundException {
        BankAccount bankAccount = bankAccountRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));
        bankAccount.setCustomer(bankAccount_params.getCustomer());
        BankAccount updated_bankAccount = bankAccountRepo.save((bankAccount));
        return ResponseEntity.ok().body(updated_bankAccount);
    }
}
