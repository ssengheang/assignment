package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Remark;
import com.example.groupassessment.request_param.remark.CreateReqParam;
import com.example.groupassessment.request_param.remark.UpdateReqParam;
import com.example.groupassessment.service.serviceImp.RemarkServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remarks")
public class RemarkController {
    private RemarkServiceImp remarkServiceImp;
    @Autowired
    public RemarkController(RemarkServiceImp remarkServiceImp){
        this.remarkServiceImp = remarkServiceImp;
    }

    @PostMapping("")
    public Remark createRemark(@Validated @RequestBody CreateReqParam remark){
        return remarkServiceImp.create(remark);
    }

    @GetMapping("")
    public List<Remark> listRemark(){
        return remarkServiceImp.index();
    }

    @GetMapping("/{id}")
    public Remark getRemarkById(@PathVariable(name = "id") Long id){
        return remarkServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Remark updateRemark(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam remark){
        return remarkServiceImp.update(id, remark);
    }

    @DeleteMapping("/{id}")
    public String deleteRemark(@PathVariable(name = "id") Long id){
        return remarkServiceImp.delete(id);
    }
}
