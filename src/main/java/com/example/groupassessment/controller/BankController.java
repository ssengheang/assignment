package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.exception.ResourceNotFoundException;
import com.example.groupassessment.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class BankController {
    private BankRepo bankRepo;
    @Autowired
    private BankController(BankRepo bankRepo){
        this.bankRepo = bankRepo;
    }

    @GetMapping("")
    public List<Bank> getAll(){
        return bankRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getOne(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Bank bank = bankRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));

        return ResponseEntity.ok().body(bank);
    }

    @PostMapping("")
    public ResponseEntity<Bank> create(@Validated @RequestBody Bank bank_params){
        Long id = bank_params.getBankAccounts().get(0).getId();
        Bank bank = new Bank();
        System.out.println(bank_params);
        System.out.println(id);
        System.out.println(bank_params.getBankAccounts());
        bank.setBankAccounts(bank_params.getBankAccounts());
        Bank bank_inserted = bankRepo.save(bank);
        return ResponseEntity.status(201).body(bank_inserted);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> update(@PathVariable(value = "id") Long id, @Validated @RequestBody Bank bank_params) throws ResourceNotFoundException {
        Bank bank = bankRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));
        bank.setBankAccounts(bank_params.getBankAccounts());
        Bank updated_bank = bankRepo.save((bank));
        return ResponseEntity.ok().body(updated_bank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bank> delete(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Bank bank = bankRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));
        bankRepo.delete(bank);
        return ResponseEntity.ok().body(bank);
    }


}
