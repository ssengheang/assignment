package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.enum_data_type.LoanStatus;
import com.example.groupassessment.enitity.projection.LoanProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.repository.BorrowerRepo;
import com.example.groupassessment.repository.LoanRepo;
import com.example.groupassessment.request_param.loan.CreateReqParam;
import com.example.groupassessment.request_param.loan.UpdateReqParam;
import com.example.groupassessment.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanServiceImp implements LoanService {
    private LoanRepo loanRepo;
    private BorrowerRepo borrowerRepo;
    @Autowired
    public LoanServiceImp(LoanRepo loanRepo, BorrowerRepo borrowerRepo){
        this.loanRepo = loanRepo;
        this.borrowerRepo = borrowerRepo;
    }

    @Override
    public List<LoanProjection> index(Pagination pagination) {
        Page<LoanProjection> loanProjections = loanRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(loanProjections.getTotalElements());
        return loanProjections.getContent();
    }
    @Override
    public LoanProjection show(Long id){
        return loanRepo.findLoanProjectionById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }
    @Override
    public Loan create(CreateReqParam loan_req){
        Borrower borrower = borrowerRepo.findById(loan_req.getBorrowerId())
                .orElseThrow(() -> new ResourceAccessException("No resource constrain found!"));
        Loan loan = new Loan();
        loan.setLoanAmount(loan_req.getLoanAmount());
        loan.setAmountLeft(loan_req.getLoanAmount());
        loan.setDate(loan_req.getDate());
        loan.setInterest(loan_req.getInterest());
        loan.setBorrower(borrower);
        return loanRepo.save(loan);
    }
    @Override
    public Loan update(Long id, UpdateReqParam loan){
        Loan update_loan = loanRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        update_loan.setLoanAmount(loan.getLoanAmount());
        update_loan.setAmountLeft(loan.getLoanAmount());
        update_loan.setInterest(loan.getInterest());
        return loanRepo.save(update_loan);
    }
    @Override
    public Map<String, Boolean> approve(Long id){
        Loan update_loan = loanRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        boolean is_pending = update_loan.getStatus() == LoanStatus.PENDING;
        Map<String, Boolean> map = new HashMap<>();
        System.out.println(is_pending);
        System.out.println(!is_pending);
        if (!is_pending){
            map.put("isPending", false);
        }else{
            update_loan.setStatus(LoanStatus.REJECTED);
            loanRepo.save(update_loan);
            map.put("isPending", true);
        }
        return map;
    }
    @Override
    public Map<String, Boolean> reject(Long id){
        Loan update_loan = loanRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        boolean is_pending = update_loan.getStatus() == LoanStatus.PENDING;
        Map<String, Boolean> map = new HashMap<>();
        if (!is_pending){
            map.put("isPending", false);
        }else{
            update_loan.setStatus(LoanStatus.REJECTED);
            loanRepo.save(update_loan);
            map.put("isPending", true);
        }
        return map;
    }
    @Override
    public Boolean delete(Long id){
        Loan loan = loanRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        loanRepo.delete(loan);
        return true;
    }
}
