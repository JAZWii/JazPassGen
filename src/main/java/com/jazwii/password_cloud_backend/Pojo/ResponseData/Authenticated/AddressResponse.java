package com.jazwii.password_cloud_backend.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.password_cloud_backend.Entity.Model.Address;
import com.jazwii.password_cloud_backend.Singleton.RestViews;

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