package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.Asset;
import com.example.groupassessment.request_param.asset.CreateReqParam;
import com.example.groupassessment.request_param.asset.UpdateReqParam;
import com.example.groupassessment.service.serviceImp.AssetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/assets")
public class AssetController {
    private AssetServiceImp assetServiceImp;
    @Autowired
    public AssetController(AssetServiceImp assetServiceImp){
        this.assetServiceImp = assetServiceImp;
    }

    @PostMapping("")
    public Asset createAsset(@Validated @RequestBody CreateReqParam asset){
        return assetServiceImp.create(asset);
    }

    @GetMapping("")
    public List<Asset> listAsset(){
        return assetServiceImp.index();
    }

    @GetMapping("/{id}")
    public Asset getAssetById(@PathVariable(name = "id") Long id){
        return assetServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public Asset updateAsset(@PathVariable(name = "id") Long id, @Validated @RequestBody UpdateReqParam asset){
        return assetServiceImp.update(id, asset);
    }

    @DeleteMapping("/{id}")
    public String deleteAsset(@PathVariable(name = "id") Long id){
        return assetServiceImp.delete(id);
    }
}
