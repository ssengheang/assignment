package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.repository.BorrowerRepo;
import com.example.groupassessment.request_param.borrower.CreateReqParam;
import com.example.groupassessment.request_param.borrower.UpdateReqParam;
import com.example.groupassessment.response.BorrowerView;
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
        return borrowerRepo.findAll();
    }

    public Borrower show(Long id){
        return borrowerRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    public Borrower create(CreateReqParam borrower){
        Borrower borrower1 = new Borrower();
        borrower1.setFullName(borrower.getFullName());
        borrower1.setAge(borrower.getAge());
        borrower1.setPhone(borrower.getPhone());
        if (borrower.getPidType() != null){
            borrower1.setPidType(borrower.getPidType());
        }
        borrower1.setPidNumber(borrower.getPidNumber());
        return borrowerRepo.save(borrower1);
    }

    public Borrower update(Long id, UpdateReqParam borrower){
        Borrower update_borrower = borrowerRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        update_borrower.setFullName(borrower.getFullName());
        update_borrower.setAge(borrower.getAge());
        update_borrower.setPhone(borrower.getPhone());
        return borrowerRepo.save(update_borrower);
    }

    public BorrowerView<Borrower> delete(Long id){
        Borrower delete_borrower = borrowerRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        borrowerRepo.delete(delete_borrower);
        return new BorrowerView<>("200", "Deleted");
    }
}
