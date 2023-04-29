package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.enitity.projection.TypeProjection;

import java.util.List;

public interface TypeService {
    List<TypeProjection> index();
    TypeProjection show(Long id);
}
