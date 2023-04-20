package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Asset;
import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.Remark;
import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.repository.LoanRepo;
import com.example.groupassessment.repository.RemarkRepo;
import com.example.groupassessment.request_param.remark.CreateReqParam;
import com.example.groupassessment.request_param.remark.UpdateReqParam;
import com.example.groupassessment.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class RemarkServiceImp implements RemarkService {
    private RemarkRepo remarkRepo;
    private LoanRepo loanRepo;
    @Autowired
    public RemarkServiceImp(RemarkRepo remarkRepo, LoanRepo loanRepo){
        this.remarkRepo = remarkRepo;
        this.loanRepo = loanRepo;
    }

    @Override
    public List<Remark> index() {
        return remarkRepo.findAll();
    }

    @Override
    public Remark show(Long id){
        return remarkRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    @Override
    public Remark create(CreateReqParam remark){
        Remark remark1 = new Remark();
        remark1.setRemark(remark.getRemark());
        remark1.setReason(remark.getReason());

        Loan loan = loanRepo.findById(remark.getLoanId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (Loan not found)"));

        remark1.setLoan(loan);

        return remarkRepo.save(remark1);
    }

    @Override
    public Remark update(Long id, UpdateReqParam remark){
        Remark remark1 = remarkRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        remark1.setReason(remark.getReason());
        remark1.setRemark(remark.getRemark());
        return remarkRepo.save(remark1);
    }

    @Override
    public String delete(Long id){
        Remark remark = remarkRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        remarkRepo.delete(remark);
        return "Deleted!";
    }
}
