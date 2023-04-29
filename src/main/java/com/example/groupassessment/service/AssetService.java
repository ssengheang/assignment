package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Asset;
import com.example.groupassessment.enitity.projection.AssetProjection;
import com.example.groupassessment.request_param.asset.CreateReqParam;
import com.example.groupassessment.request_param.asset.UpdateReqParam;

import java.util.List;

public interface AssetService {
    List<AssetProjection> index();
    AssetProjection show(Long id);
    Asset update(Long id, UpdateReqParam asset);
    Asset create(CreateReqParam asset);
    Boolean delete(Long id);
}
