package com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Entity.Model.Address;
import com.jazwii.jazpassgen.Singleton.RestViews;

public class AddressResponse {
    @JsonProperty("address")
    @JsonView(RestViews.AddressPublicDetailed.class)
    private Address address;

    public AddressResponse() {
    }

    public AddressResponse(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}