package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.repository.BorrowerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    private BorrowerRepo borrowerRepo;
    @Autowired
    public BorrowerController(BorrowerRepo borrowerRepo){
        this.borrowerRepo = borrowerRepo;
    }

    @GetMapping("")
    public List<Borrower> getAllBorrower() {
        List<Borrower> borrowers = null;
        try {
            borrowers = borrowerRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return borrowers;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Borrower>> getSingleBorrower(@PathVariable(value = "id") Long id){
        Optional<Borrower> borrower = null;
        try {
            borrower = borrowerRepo.findById(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok().body(borrower);
    }

    @PostMapping("")
    public Borrower createBorrower(@Validated @RequestBody Borrower borrower){
        return borrowerRepo.save(borrower);
    }

    @PutMapping("/{id}")
    public Borrower updateBorrower(@PathVariable(value = "id") Long id, @Validated @RequestBody Borrower borrower){
        Borrower update_borrower = borrowerRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        update_borrower.setName(borrower.getName());
        update_borrower.setAge(borrower.getAge());
        update_borrower.setAddress(borrower.getAddress());
        update_borrower.setPhone(borrower.getPhone());
        update_borrower.setEmploymentStatus(borrower.getEmploymentStatus());
        update_borrower.setMaritalStatus(borrower.getMaritalStatus());
        return borrowerRepo.save(update_borrower);
    }

    @DeleteMapping("/{id}")
    public String deleteBorrower(@PathVariable(value = "id") Long id){
        Borrower delete_borrower = borrowerRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        borrowerRepo.delete(delete_borrower);
        return "Deleted";
    }
}
