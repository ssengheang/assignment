package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.enitity.projection.TypeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepo extends JpaRepository<Type, Long> {
    Type findByName(String name);
    Optional<TypeProjection> findTypeProjectionById(Long id);
    List<TypeProjection> findAllBy();
}
