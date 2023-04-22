package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Contract;
import com.example.groupassessment.request_param.contract.ReqBorrowerId;
import com.example.groupassessment.request_param.contract.ReqLoanId;
import com.example.groupassessment.service.serviceImp.ContractServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    private ContractServiceImp contractServiceImp;
    @Autowired
    public ContractController(ContractServiceImp contractServiceImp){
        this.contractServiceImp = contractServiceImp;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("borrowerId") ReqBorrowerId borrowerId, @RequestParam("loanId") ReqLoanId loanId) {
        String message = "";
        try {
            contractServiceImp.save(file, borrowerId, loanId);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return message;
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return message;
        }
    }

    @GetMapping(value = "/load/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> loadFile(@PathVariable String filename) {
        Resource file = contractServiceImp.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("")
    public List<Contract> getAll(){
        return contractServiceImp.index();
    }

    @GetMapping("/{id}")
    public Contract getOne(@PathVariable(value = "id") Long id){
        return contractServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Contract update(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile file){
        return contractServiceImp.update(id, file);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        return contractServiceImp.delete(id);
    }
}