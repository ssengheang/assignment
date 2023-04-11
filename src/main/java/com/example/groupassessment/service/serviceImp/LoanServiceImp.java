package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.enum_data_type.LoanStatus;
import com.example.groupassessment.repository.BorrowerRepo;
import com.example.groupassessment.repository.LoanRepo;
import com.example.groupassessment.request_param.loan.CreateReqParam;
import com.example.groupassessment.request_param.loan.UpdateReqParam;
import com.example.groupassessment.response.LoanView;
import com.example.groupassessment.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

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
    public List<Loan> index() {
        List<Loan> loans = null;
        try {
            loans = loanRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loans;
    }
    @Override
    public Loan show(Long id){
        return loanRepo.findById(id)
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
    public LoanView<Loan> approve(Long id){
        Loan update_loan = loanRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        boolean is_pending = update_loan.getStatus() == LoanStatus.PENDING;
        if (!is_pending){
            return new LoanView<>("404", "Please make sure status is 'PENDING'");
        }else{
            update_loan.setStatus(LoanStatus.APPROVED);
            return new LoanView<>("200", "success", loanRepo.save(update_loan));
        }
    }
    @Override
    public LoanView<Loan> reject(Long id){
        Loan update_loan = loanRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        boolean is_pending = update_loan.getStatus() == LoanStatus.PENDING;
        if (!is_pending){
            return new LoanView<>("404", "Please make sure status is 'PENDING'");
        }else{
            update_loan.setStatus(LoanStatus.REJECTED);
            return new LoanView<>("200", "success", loanRepo.save(update_loan));
        }
    }
    @Override
    public LoanView<Loan> delete(Long id){
        Loan loan = loanRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        loanRepo.delete(loan);
        return new LoanView<>("200", "Deleted");
    }
}
