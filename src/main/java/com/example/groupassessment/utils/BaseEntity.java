package com.example.groupassessment.utils;

import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP",  updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

//    @Column(name = "updated_at", columnDefinition = "TIMESTAMP",  updatable = true, nullable = false)
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

}


