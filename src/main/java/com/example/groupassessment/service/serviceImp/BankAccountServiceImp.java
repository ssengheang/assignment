package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Bank;
import com.example.groupassessment.enitity.BankAccount;
import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.repository.BankAccountRepo;
import com.example.groupassessment.repository.BankRepo;
import com.example.groupassessment.repository.BorrowerRepo;
import com.example.groupassessment.request_param.bank_account.CreateReqParam;
import com.example.groupassessment.request_param.bank_account.UpdateReqParam;
import com.example.groupassessment.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class BankAccountServiceImp implements BankAccountService {
    private BankAccountRepo bankAccountRepo;
    private BorrowerRepo borrowerRepo;
    private BankRepo bankRepo;
    @Autowired
    public BankAccountServiceImp(BankAccountRepo bankAccountRepo, BorrowerRepo borrowerRepo, BankRepo bankRepo){
        this.bankAccountRepo = bankAccountRepo;
        this.borrowerRepo = borrowerRepo;
        this.bankRepo = bankRepo;
    }
    @Override
    public List<BankAccount> index() {
        return bankAccountRepo.findAll();
    }

    @Override
    public BankAccount show(Long id) {
        return bankAccountRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    @Override
    public BankAccount update(Long id, UpdateReqParam bankAccount) {
        BankAccount bankAccount1 = bankAccountRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        bankAccount1.setAccountName(bankAccount.getAccountName());
        bankAccount1.setKeyId(bankAccount.getKeyId());
        return bankAccountRepo.save(bankAccount1);
    }

    @Override
    public BankAccount create(CreateReqParam bankAccount) {
        BankAccount bankAccount1 = new BankAccount();
        Bank bank = bankRepo.findById(bankAccount.getBankId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (No Bank resource found!)"));
        Borrower borrower = borrowerRepo.findById(bankAccount.getBankId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (No Borrower resource found!)"));
        bankAccount1.setBank(bank);
        bankAccount1.setBorrower(borrower);
        bankAccount1.setKeyId(bankAccount.getKeyId());
        bankAccount1.setAccountName(bankAccount.getAccountName());
        return bankAccountRepo.save(bankAccount1);
    }

    @Override
    public String delete(Long id) {
        BankAccount bankAccount1 = bankAccountRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
        bankAccountRepo.delete(bankAccount1);
        return "Deleted";
    }
}
