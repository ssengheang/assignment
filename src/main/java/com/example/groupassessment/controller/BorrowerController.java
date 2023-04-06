package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    private BorrowerService borrowerService;
    @Autowired
    public BorrowerController(BorrowerService borrowerService){
        this.borrowerService = borrowerService;
    }

    @PostMapping("")
    public Borrower createBorrower(@Validated @RequestBody Borrower borrower_params){
        return borrowerService.create(borrower_params);
    }

    @GetMapping("")
    public List<Borrower> listBorrower(){
        return borrowerService.index();
    }

    @GetMapping("/{id}")
    public Borrower getBorrowerById(@PathVariable(name = "id") Long id){
        return borrowerService.show(id);
    }

    @PutMapping("/{id}")
    public Borrower updateBorrower(@PathVariable(name = "id") Long id, @Validated @RequestBody Borrower borrower_params){
        return borrowerService.update(id, borrower_params);
    }

    @DeleteMapping("/{id}")
    public String updateBorrower(@PathVariable(name = "id") Long id){
        return borrowerService.delete(id);
    }
}
