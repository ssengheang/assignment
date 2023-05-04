package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.enitity.projection.BankProjection;
import com.example.groupassessment.enitity.projection.BorrowerImageProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.repository.BankRepo;
import com.example.groupassessment.request_param.bank.ReqParam;
import com.example.groupassessment.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class BankServiceImp implements BankService {
    private BankRepo bankRepo;

    @Autowired
    public BankServiceImp(BankRepo bankRepo){this.bankRepo = bankRepo;}


    @Override
    public List<BankProjection> index(Pagination pagination) {
        Page<BankProjection> bankProjections = bankRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(bankProjections.getTotalElements());
        return bankProjections.getContent();
    }

    @Override
    public BankProjection show(Long id) {
        return bankRepo.findBankProjectionById(id)
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
    public Boolean delete(Long id) {
        Bank bank1 = bankRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        bankRepo.delete(bank1);
        return true;
    }
}
