package com.example.groupassessment.enitity.projection;

public interface AddressProjection {
    Long getId();
    String getProvinceOrCity();
    String getDistrict();
    String getCommune();
    String getVillage();
    String getStreetNumber();
    String getHouseNumber();
}
