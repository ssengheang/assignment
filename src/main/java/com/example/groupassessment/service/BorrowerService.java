package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.repository.BorrowerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class BorrowerService {
    private BorrowerRepo borrowerRepo;
    @Autowired
    public BorrowerService(BorrowerRepo borrowerRepo){
        this.borrowerRepo = borrowerRepo;
    }

    public List<Borrower> index() {
        List<Borrower> borrowers = null;
        try {
            borrowers = borrowerRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return borrowers;
    }

    public Borrower show(Long id){
        return borrowerRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    public Borrower create(Borrower borrower){
        return borrowerRepo.save(borrower);
    }

    public Borrower update(Long id, Borrower borrower){
        Borrower update_borrower = borrowerRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        update_borrower.setFullName(borrower.getFullName());
        update_borrower.setAge(borrower.getAge());
        update_borrower.setPhone(borrower.getPhone());
        return borrowerRepo.save(update_borrower);
    }

    public String delete(Long id){
        Borrower delete_borrower = borrowerRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        borrowerRepo.delete(delete_borrower);
        return "Deleted";
    }
}
