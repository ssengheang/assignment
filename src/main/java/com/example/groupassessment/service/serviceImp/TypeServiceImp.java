package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.repository.TypeRepo;
import com.example.groupassessment.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@Service
public class TypeServiceImp implements TypeService {
    private TypeRepo typeRepo;
    @Autowired
    public TypeServiceImp(TypeRepo typeRepo){
        this.typeRepo = typeRepo;
    }

    @Override
    public List<Type> index() {
        return typeRepo.findAll();
    }

    @Override
    public Type show(Long id){
        return typeRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }
}
