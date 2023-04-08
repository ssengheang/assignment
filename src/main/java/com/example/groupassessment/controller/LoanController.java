package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.request_param.loan.CreateReqParam;
import com.example.groupassessment.request_param.loan.StatusParam;
import com.example.groupassessment.request_param.loan.UpdateReqParam;
import com.example.groupassessment.response.LoanView;
import com.example.groupassessment.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/loans")
public class LoanController {
    private LoanService loanService;
    @Autowired
    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @PostMapping("")
    public Loan createLoan(@Validated @RequestBody CreateReqParam loan_params){
        return loanService.create(loan_params);
    }

    @GetMapping("")
    public List<Loan> listLoan(){
        return loanService.index();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable(name = "id") Long id){
        return loanService.show(id);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam loan_params){
        return loanService.update(id, loan_params);
    }

    @PutMapping("/{id}/approve")
    public LoanView<Loan> approve(@PathVariable(name = "id") Long id){
        return loanService.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LoanView<Loan> reject(@PathVariable(name = "id") Long id){
        return loanService.reject(id);
    }

    @DeleteMapping("/{id}")
    public LoanView<Loan> deleteLoan(@PathVariable(name = "id") Long id){
        return loanService.delete(id);
    }
}
