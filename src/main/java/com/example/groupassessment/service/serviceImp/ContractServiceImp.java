package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.Contract;
import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.projection.ContractProjection;
import com.example.groupassessment.enitity.projection.LoanProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.repository.BorrowerRepo;
import com.example.groupassessment.repository.ContractRepo;
import com.example.groupassessment.repository.LoanRepo;
import com.example.groupassessment.request_param.contract.ReqBorrowerId;
import com.example.groupassessment.request_param.contract.ReqLoanId;
import com.example.groupassessment.service.ContractService;
import com.example.groupassessment.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.List;

@Service
public class ContractServiceImp implements ContractService {
    private ContractRepo contractRepo;
    private BorrowerRepo borrowerRepo;
    private LoanRepo loanRepo;
    @Autowired
    public ContractServiceImp(ContractRepo contractRepo, BorrowerRepo borrowerRepo, LoanRepo loanRepo){
        this.contractRepo = contractRepo;
        this.borrowerRepo = borrowerRepo;
        this.loanRepo = loanRepo;
    }

    @Override
    public void save(MultipartFile file, ReqBorrowerId borrowerId, ReqLoanId loanId) {
        try {
            //Upload file
            UploadFile uploadFile = new UploadFile(file, "/contracts/load/");
            String path = uploadFile.uploadFile();

            // Add to DB
            Long borrower_id = Long.parseLong(borrowerId.getBorrowerId());
            Borrower borrower = borrowerRepo.findById(borrower_id)
                    .orElseThrow(() -> new ResourceAccessException("Constrain error (No resource borrower found!)"));
            Long loan_id = Long.parseLong(loanId.getLoanId());
            Loan loan = loanRepo.findById(loan_id)
                    .orElseThrow(() -> new ResourceAccessException("constrain error (No resource loan found!)"));
            Contract contract = new Contract();
            contract.setPath(path);
            contract.setBorrower(borrower);
            contract.setLoan(loan);
            contractRepo.save(contract);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public Resource load(String filename) {
        try {
            UploadFile uploadFile = new UploadFile();
            Path file = uploadFile.getRoot().resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    @Override
    public List<ContractProjection> index(Pagination pagination){
        Page<ContractProjection> contractProjections = contractRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(contractProjections.getTotalElements());
        return contractProjections.getContent();
    }
    @Override
    public ContractProjection show(Long id){
        return contractRepo.findContractProjectionById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }
    @Override
    public Contract update(Long id, MultipartFile file){
        Contract contract;
        try {
            Contract contract1 = contractRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
            //Upload file
            UploadFile uploadFile = new UploadFile(file, "/contracts/load/");
            String path = uploadFile.uploadFile();

            // Update record
            contract1.setPath(path);
            contract = contractRepo.save(contract1);
        }catch (Exception e){throw new RuntimeException(e.getMessage());}

        return contract;
    }
    @Override
    public Boolean delete(Long id){
        Contract contract = contractRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        contractRepo.delete(contract);
        return true;
    }
}
