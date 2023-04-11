package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.request_param.loan.CreateReqParam;
import com.example.groupassessment.request_param.loan.UpdateReqParam;
import com.example.groupassessment.response.LoanView;
import com.example.groupassessment.service.serviceImp.LoanServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/loans")
public class LoanController {
    private LoanServiceImp loanServiceImp;
    @Autowired
    public LoanController(LoanServiceImp loanServiceImp){
        this.loanServiceImp = loanServiceImp;
    }

    @PostMapping("")
    public Loan createLoan(@Validated @RequestBody CreateReqParam loan_params){
        return loanServiceImp.create(loan_params);
    }

    @GetMapping("")
    public List<Loan> listLoan(){
        return loanServiceImp.index();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable(name = "id") Long id){
        return loanServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam loan_params){
        return loanServiceImp.update(id, loan_params);
    }

    @PutMapping("/{id}/approve")
    public LoanView<Loan> approve(@PathVariable(name = "id") Long id){
        return loanServiceImp.approve(id);
    }

    @PutMapping("/{id}/reject")
    public LoanView<Loan> reject(@PathVariable(name = "id") Long id){
        return loanServiceImp.reject(id);
    }

    @DeleteMapping("/{id}")
    public LoanView<Loan> deleteLoan(@PathVariable(name = "id") Long id){
        return loanServiceImp.delete(id);
    }
}
