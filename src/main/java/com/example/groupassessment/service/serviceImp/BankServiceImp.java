package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.repository.BankRepo;
import com.example.groupassessment.request_param.bank.ReqParam;
import com.example.groupassessment.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class BankServiceImp implements BankService {
    private BankRepo bankRepo;

    @Autowired
    public BankServiceImp(BankRepo bankRepo){this.bankRepo = bankRepo;}


    @Override
    public List<Bank> index() {
        return bankRepo.findAll();
    }

    @Override
    public Bank show(Long id) {
        return bankRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    @Override
    public Bank update(Long id, ReqParam bank) {
        Bank bank1 = bankRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        bank1.setName(bank.getName());
        return bankRepo.save(bank1);
    }

    @Override
    public Bank create(ReqParam bank) {
        Bank bank1 = new Bank();
        bank1.setName(bank.getName());
        return bankRepo.save(bank1);
    }

    @Override
    public String delete(Long id) {
        Bank bank1 = bankRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        bankRepo.delete(bank1);
        return "Deleted";
    }
}
