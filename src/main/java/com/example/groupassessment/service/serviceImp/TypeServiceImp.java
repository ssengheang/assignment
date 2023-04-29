package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.enitity.projection.TypeProjection;
import com.example.groupassessment.enitity.projection.UserProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.repository.TypeRepo;
import com.example.groupassessment.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<TypeProjection> index(Pagination pagination) {
        Page<TypeProjection> typeProjections = typeRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(typeProjections.getTotalElements());
        return typeProjections.getContent();
    }

    @Override
    public TypeProjection show(Long id){
        return typeRepo.findTypeProjectionById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }
}
