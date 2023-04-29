package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Asset;
import com.example.groupassessment.enitity.projection.AssetProjection;
import com.example.groupassessment.enitity.projection.BankAccountProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.asset.CreateReqParam;
import com.example.groupassessment.request_param.asset.UpdateReqParam;
import com.example.groupassessment.service.serviceImp.AssetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {
    private AssetServiceImp assetServiceImp;
    @Autowired
    public AssetController(AssetServiceImp assetServiceImp){
        this.assetServiceImp = assetServiceImp;
    }

    @PostMapping("")
    public ApiResponse createAsset(@Validated @RequestBody CreateReqParam asset){
        Asset asset1 = assetServiceImp.create(asset);
        AssetProjection assetProjection = assetServiceImp.show(asset1.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                assetProjection
        );
    }

    @GetMapping("")
    public Map<String, Object> listAsset(Pagination pagination){
        List<AssetProjection> assetProjections = assetServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", assetProjections);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getAssetById(@PathVariable(name = "id") Long id){
        AssetProjection assetProjection = assetServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                assetProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse updateAsset(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam asset){
        Asset asset1 = assetServiceImp.update(id, asset);
        AssetProjection assetProjection = assetServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                assetProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteAsset(@PathVariable(name = "id") Long id){
        Boolean isDeleted = assetServiceImp.delete(id);
        if (!isDeleted){
            return new ApiResponse<>(
                    ApiStatus.FAI_DELETED.getCode(),
                    ApiStatus.FAI_DELETED.getMessage()
            );
        }
        return new ApiResponse<>(
                ApiStatus.SUC_DELETED.getCode(),
                ApiStatus.SUC_DELETED.getMessage()
        );
    }
}
