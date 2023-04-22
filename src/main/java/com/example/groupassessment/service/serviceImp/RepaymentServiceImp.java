package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.PaymentMethod;
import com.example.groupassessment.enitity.Repayment;
import com.example.groupassessment.repository.BorrowerRepo;
import com.example.groupassessment.repository.LoanRepo;
import com.example.groupassessment.repository.PaymentMethodRepo;
import com.example.groupassessment.repository.RepaymentRepo;
import com.example.groupassessment.request_param.repayment.CreateReqParam;
import com.example.groupassessment.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class RepaymentServiceImp implements RepaymentService {
    private RepaymentRepo repaymentRepo;
    private BorrowerRepo borrowerRepo;
    private PaymentMethodRepo paymentMethodRepo;
    private LoanRepo loanRepo;
    @Autowired
    public RepaymentServiceImp(RepaymentRepo repaymentRepo, BorrowerRepo borrowerRepo, PaymentMethodRepo paymentMethodRepo, LoanRepo loanRepo){
        this.borrowerRepo = borrowerRepo;
        this.loanRepo = loanRepo;
        this.paymentMethodRepo = paymentMethodRepo;
        this.repaymentRepo = repaymentRepo;
    }

    @Override
    public List<Repayment> index() {
        return repaymentRepo.findAll();
    }

    @Override
    public Repayment show(Long id) {
        Repayment repayment = repaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        return repayment;
    }

    @Override
    public Repayment create(CreateReqParam repayment) {
        System.out.println(repayment);
        Repayment repayment1 = new Repayment();
        System.out.println("/////////======");
        Borrower borrower = borrowerRepo.findById(repayment.getBorrowerId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (No Borrower resource found!)"));
        Loan loan = loanRepo.findById(repayment.getLoanId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (No Loan resource found!)"));
        PaymentMethod paymentMethod = paymentMethodRepo.findById(repayment.getPaymentMethodId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (No Payment-Method resource found!)"));

        System.out.println("borrower=====");

        repayment1.setAmount(repayment.getAmount());
        repayment1.setDate(repayment.getDate());
        repayment1.setInterestDue(repayment.getInterestDue());
        repayment1.setLoan(loan);
        repayment1.setBorrower(borrower);
        repayment1.setPaymentMethod(paymentMethod);
        Repayment repayment2 = repaymentRepo.save(repayment1);

        Float amountLeft = loan.getAmountLeft() - repayment.getAmount();
        if (amountLeft > 0){
            loan.setAmountLeft(amountLeft);
            loanRepo.save(loan);
        }
        return repayment2;
    }

    @Override
    public String delete(Long id) {
        Repayment repayment = repaymentRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        repaymentRepo.delete(repayment);
        return "Deleted";
    }
}
