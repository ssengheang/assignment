package com.example.groupassessment.repository;

import com.example.groupassessment.enitity.Asset;
import com.example.groupassessment.enitity.projection.AssetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Long> {
    Optional<AssetProjection> findTypeProjectionById(Long id);
    List<AssetProjection> findAllBy();
}
