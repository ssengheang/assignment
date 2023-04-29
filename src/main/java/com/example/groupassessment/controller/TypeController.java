package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.enitity.projection.TypeProjection;
import com.example.groupassessment.enitity.projection.UserProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.service.serviceImp.TypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/types")
public class TypeController {
    private TypeServiceImp typeServiceImp;
    @Autowired
    public TypeController(TypeServiceImp typeServiceImp){
        this.typeServiceImp = typeServiceImp;
    }

    @GetMapping("")
    public Map<String, Object> listType(Pagination pagination){
        List<TypeProjection> type = typeServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", type);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getTypeById(@PathVariable(name = "id") Long id){
        TypeProjection typeProjection = typeServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                typeProjection
        );
    }
}
