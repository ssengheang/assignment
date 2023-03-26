package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Repayment;
import com.example.groupassessment.repository.RepaymentRepo;
import com.example.groupassessment.services.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/repayments")
public class RepaymentController {
    private RepaymentRepo repaymentRepo;
    @Autowired
    private RepaymentController(RepaymentRepo repaymentRepo){
        this.repaymentRepo = repaymentRepo;
    }

    @GetMapping("")
    public List<Repayment> getAll(){
        return repaymentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Repayment> getOne(@PathVariable(value = "id") Long id){
        return repaymentRepo.findById(id);
    }

    @PostMapping("")
    public Repayment create(@Validated @RequestBody Repayment repayment_params){
        return repaymentRepo.save(repayment_params);
    }

    @PutMapping("/{id}")
    public Repayment update(@PathVariable(value = "id") Long id, @Validated @RequestBody Repayment repayment_params){
        Repayment repayment = repaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Employee not found for this id ::" + id));;
        repayment.setAmount(repayment_params.getAmount());
        repayment.setDate(repayment_params.getDate());
        repayment.setInterestDue(repayment_params.getInterestDue());
        return repaymentRepo.save((repayment));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        Repayment repayment = repaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Employee not found for this id ::" + id));;
        repaymentRepo.delete(repayment);
    }
}
