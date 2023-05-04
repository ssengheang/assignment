package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.projection.BorrowerProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.repository.BorrowerRepo;
import com.example.groupassessment.request_param.borrower.CreateReqParam;
import com.example.groupassessment.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class BorrowerServiceImp implements BorrowerService {
    private BorrowerRepo borrowerRepo;
    @Autowired
    public BorrowerServiceImp(BorrowerRepo borrowerRepo){
        this.borrowerRepo = borrowerRepo;
    }

    @Override
    public List<BorrowerProjection> index(Pagination pagination) {
        Page<BorrowerProjection> borrowerProjections = borrowerRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(borrowerProjections.getTotalElements());
        return borrowerProjections.getContent();
    }

    @Override
    public BorrowerProjection show(Long id){
        return borrowerRepo.findBorrowerProjectionById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    @Override
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

    @Override
    public Borrower update(Long id, Borrower borrower){
        Borrower update_borrower = borrowerRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        update_borrower.setFullName(borrower.getFullName());
        update_borrower.setAge(borrower.getAge());
        update_borrower.setPhone(borrower.getPhone());
        return borrowerRepo.save(update_borrower);
    }

    @Override
    public Boolean delete(Long id){
        Borrower delete_borrower = borrowerRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        borrowerRepo.delete(delete_borrower);
        return true;
    }
}
