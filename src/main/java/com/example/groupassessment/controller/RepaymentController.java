package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Repayment;
import com.example.groupassessment.request_param.repayment.CreateReqParam;
import com.example.groupassessment.service.serviceImp.RepaymentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/repayments")
public class RepaymentController {
    private RepaymentServiceImp repaymentServiceImp;
    @Autowired
    public RepaymentController(RepaymentServiceImp repaymentServiceImp){
        this.repaymentServiceImp = repaymentServiceImp;
    }

    @PostMapping("")
    public Repayment createRepayment(@Validated @RequestBody CreateReqParam repayment){
        return repaymentServiceImp.create(repayment);
    }

    @GetMapping("")
    public List<Repayment> listRepayment(){
        return repaymentServiceImp.index();
    }

    @GetMapping("/{id}")
    public Repayment getRepaymentById(@PathVariable(name = "id") Long id){
        return repaymentServiceImp.show(id);
    }

    @DeleteMapping("/{id}")
    public String deleteRepayment(@PathVariable(name = "id") Long id){
        return repaymentServiceImp.delete(id);
    }

}
