package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Asset;
import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.Loan;
import com.example.groupassessment.enitity.Type;
import com.example.groupassessment.repository.AssetRepo;
import com.example.groupassessment.repository.LoanRepo;
import com.example.groupassessment.repository.TypeRepo;
import com.example.groupassessment.request_param.asset.CreateReqParam;
import com.example.groupassessment.request_param.asset.UpdateReqParam;
import com.example.groupassessment.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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
    public List<Asset> index() {
        return assetRepo.findAll();
    }

    @Override
    public Asset show(Long id){
        return assetRepo.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    @Override
    public Asset create(CreateReqParam asset){
        Asset asset1 = new Asset();
        asset1.setName(asset.getName());
        asset1.setEstimateValue(asset.getEstimateValue());

        Loan loan = loanRepo.findById(asset.getLoanId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (Loan not found)"));

        asset1.setLoan(loan);

        Type type = typeRepo.findById(asset.getTypeId())
                .orElseThrow(() -> new ResourceAccessException("Constrain error (Type not found)"));

        asset1.setType(type);
        return assetRepo.save(asset1);
    }

    @Override
    public Asset update(Long id, UpdateReqParam asset){
        Asset update_asset = assetRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        update_asset.setName(asset.getName());
        update_asset.setEstimateValue(asset.getEstimateValue());
        return assetRepo.save(update_asset);
    }

    @Override
    public String delete(Long id){
        Asset delete_asset = assetRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        assetRepo.delete(delete_asset);
        return "Deleted!";
    }
}
