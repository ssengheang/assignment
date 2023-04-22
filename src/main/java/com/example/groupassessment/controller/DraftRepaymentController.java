//package com.example.groupassessment.controller;
//
//import com.example.groupassessment.enitity.Repayment;
//import com.example.groupassessment.exception.ResourceNotFoundException;
//import com.example.groupassessment.repository.RepaymentRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.ResourceAccessException;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/repayments")
//public class RepaymentController {
//    private RepaymentRepo repaymentRepo;
//    @Autowired
//    private RepaymentController(RepaymentRepo repaymentRepo){
//        this.repaymentRepo = repaymentRepo;
//    }
//
//    @GetMapping("")
//    public List<Repayment> getAll(){
//        return repaymentRepo.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Repayment> getOne(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
//        Repayment repayment = repaymentRepo.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));
//
//        return ResponseEntity.ok().body(repayment);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<Repayment> create(@Validated @RequestBody Repayment repayment_params){
////        Long id = repayment_params.getBorrower().getId();
//        Repayment repayment = new Repayment();
//        System.out.println(repayment_params);
////        System.out.println(id);
//        System.out.println(repayment_params.getBorrower());
//        repayment.setBorrower(repayment_params.getBorrower());
//        repayment.setLoan(repayment_params.getLoan());
//        Repayment repayment_inserted = repaymentRepo.save(repayment);
//        return ResponseEntity.status(201).body(repayment_inserted);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Repayment> update(@PathVariable(value = "id") Long id, @Validated @RequestBody Repayment repayment_params) throws ResourceNotFoundException {
//        Repayment repayment = repaymentRepo.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));
//        repayment.setAmount(repayment_params.getAmount());
//        repayment.setDate(repayment_params.getDate());
//        repayment.setInterestDue(repayment_params.getInterestDue());
//        Repayment updated_repayment = repaymentRepo.save((repayment));
//        return ResponseEntity.ok().body(updated_repayment);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity delete(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
//        Repayment repayment = repaymentRepo.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::" + id));;
//        repaymentRepo.delete(repayment);
//        return ResponseEntity.ok().body("record deleted!");
//    }
//}
