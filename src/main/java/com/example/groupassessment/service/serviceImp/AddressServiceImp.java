package com.example.groupassessment.service.serviceImp;

import com.example.groupassessment.enitity.Address;
import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.repository.AddressRepo;
import com.example.groupassessment.request_param.address.ReqParam;
import com.example.groupassessment.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class AddressServiceImp implements AddressService {
    private AddressRepo addressRepo;
    @Autowired
    public AddressServiceImp(AddressRepo addressRepo){
        this.addressRepo = addressRepo;
    }


    @Override
    public void create(ReqParam address, Borrower borrower) {
        Address address1 = new Address();
        address1.setDistrict(address.getDistrict());
        address1.setCommune(address.getCommune());
        address1.setVillage(address.getVillage());
        address1.setProvinceOrCity(address.getProvinceOrCity());
        address1.setHouseNumber(address.getHouseNumber());
        address1.setStreetNumber(address.getStreetNumber());
        address1.setBorrower(borrower);
        addressRepo.save(address1);
    }

    @Override
    public void update(Long id, ReqParam address) {
        Address address1 = addressRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));

        address1.setDistrict(address.getDistrict());
        address1.setCommune(address.getCommune());
        address1.setVillage(address.getVillage());
        address1.setProvinceOrCity(address.getProvinceOrCity());
        address1.setHouseNumber(address.getHouseNumber());
        address1.setStreetNumber(address.getStreetNumber());
        addressRepo.save(address1);
    }
}
