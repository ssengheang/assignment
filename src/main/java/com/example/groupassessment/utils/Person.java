package com.example.groupassessment.utils;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Person extends BaseEntity{
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    @Column(name = "age", nullable = false)
    private Integer age;
}
