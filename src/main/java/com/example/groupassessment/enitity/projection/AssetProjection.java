package com.example.groupassessment.enitity.projection;

public interface AssetProjection {
    Long getId();
    String getName();
    Float getEstimateValue();
    TypeProjection getType();
}
