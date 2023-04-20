package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.service.serviceImp.TypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    private TypeServiceImp typeServiceImp;
    @Autowired
    public TypeController(TypeServiceImp typeServiceImp){
        this.typeServiceImp = typeServiceImp;
    }

    @GetMapping("")
    public List<Type> listType(){
        return typeServiceImp.index();
    }

    @GetMapping("/{id}")
    public Type getTypeById(@PathVariable(name = "id") Long id){
        return typeServiceImp.show(id);
    }
}
