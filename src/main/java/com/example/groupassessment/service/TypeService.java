package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.enitity.projection.TypeProjection;
import com.example.groupassessment.enitity.response.Pagination;

import java.util.List;

public interface TypeService {
    List<TypeProjection> index(Pagination pagination);
    TypeProjection show(Long id);
}
