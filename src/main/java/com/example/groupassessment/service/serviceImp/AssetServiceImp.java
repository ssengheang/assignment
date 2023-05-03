package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Asset;
import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.enitity.projection.AssetProjection;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.exception.NotFoundException;
import com.example.groupassessment.repository.AssetRepo;
import com.example.groupassessment.repository.LoanRepo;
import com.example.groupassessment.repository.TypeRepo;
import com.example.groupassessment.request_param.asset.CreateReqParam;
import com.example.groupassessment.request_param.asset.UpdateReqParam;
import com.example.groupassessment.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssetServiceImp implements AssetService {
    private AssetRepo assetRepo;
    private LoanRepo loanRepo;
    private TypeRepo typeRepo;
    @Autowired
    public AssetServiceImp(AssetRepo assetRepo, LoanRepo loanRepo, TypeRepo typeRepo){
        this.assetRepo = assetRepo;
        this.loanRepo = loanRepo;
        this.typeRepo = typeRepo;
    }

    @Override
    public List<AssetProjection> index(Pagination pagination) {
        Page<AssetProjection> assetProjections = assetRepo.findAllBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize())
        );

        pagination.setTotalCounts(assetProjections.getTotalElements());
        return assetProjections.getContent();
    }

    @Override
    public AssetProjection show(Long id){
        return assetRepo.findTypeProjectionById(id)
                .orElseThrow(() -> new NotFoundException(
                        ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
    }

    @Override
    public Asset create(CreateReqParam asset){
        Asset asset1 = new Asset();
        asset1.setName(asset.getName());
        asset1.setEstimateValue(asset.getEstimateValue());

        Loan loan = loanRepo.findById(asset.getLoanId())
                .orElseThrow(() -> new NotFoundException(ApiStatus.NOT_FOUND.getCode(), "Constrain error (Loan not found)"));

        asset1.setLoan(loan);

        Type type = typeRepo.findById(asset.getTypeId())
                .orElseThrow(() -> new NotFoundException(ApiStatus.NOT_FOUND.getCode(), "Constrain error (Type not found)"));

        asset1.setType(type);
        return assetRepo.save(asset1);
    }

    @Override
    public Asset update(Long id, UpdateReqParam asset){
        Asset update_asset = assetRepo.findById(id).orElseThrow(() -> new NotFoundException(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        update_asset.setName(asset.getName());
        update_asset.setEstimateValue(asset.getEstimateValue());
        return assetRepo.save(update_asset);
    }

    @Override
    public Boolean delete(Long id){
        Asset delete_asset = assetRepo.findById(id).orElseThrow(() -> new NotFoundException(ApiStatus.NOT_FOUND.getCode(), ApiStatus.NOT_FOUND.getMessage()));
        assetRepo.delete(delete_asset);
        return true;
    }
}
