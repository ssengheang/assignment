package com.example.groupassessment.enitity.projection;

import com.example.groupassessment.enitity.enum_data_type.PidType;

public interface BorrowerProjection {
    Long getId();
    String getFullName();
    Integer getAge();
    String getPhone();
    String getPidNumber();
    PidType getPidType();
    AddressProjection getAddress();
}
