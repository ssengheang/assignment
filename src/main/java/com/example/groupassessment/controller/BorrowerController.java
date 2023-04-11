package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.request_param.borrower.CreateReqParam;
import com.example.groupassessment.request_param.borrower.UpdateReqParam;
import com.example.groupassessment.response.BorrowerView;
import com.example.groupassessment.service.serviceImp.BorrowerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    private BorrowerServiceImp borrowerServiceImp;
    @Autowired
    public BorrowerController(BorrowerServiceImp borrowerServiceImp){
        this.borrowerServiceImp = borrowerServiceImp;
    }

    @PostMapping("")
    public Borrower createBorrower(@Validated @RequestBody CreateReqParam borrower_params){
        return borrowerServiceImp.create(borrower_params);
    }

    @GetMapping("")
    public List<Borrower> listBorrower(){
        return borrowerServiceImp.index();
    }

    @GetMapping("/{id}")
    public Borrower getBorrowerById(@PathVariable(name = "id") Long id){
        return borrowerServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Borrower updateBorrower(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam borrower_params){
        return borrowerServiceImp.update(id, borrower_params);
    }

    @DeleteMapping("/{id}")
    public BorrowerView<Borrower> deleteBorrower(@PathVariable(name = "id") Long id){
        return borrowerServiceImp.delete(id);
    }
}
