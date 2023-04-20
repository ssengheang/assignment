package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Type;

import java.util.List;

public interface TypeService {
    List<Type> index();
    Type show(Long id);
}
