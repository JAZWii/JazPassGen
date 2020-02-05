package com.jazwii.password_cloud_backend.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.password_cloud_backend.Entity.Model.Address;
import com.jazwii.password_cloud_backend.Singleton.RestViews;

import java.util.List;

public class AddressListResponse {
    @JsonProperty("addresses")
    @JsonView(RestViews.AddressPublicMinimal.class)
    private List<Address> addresses;

    public AddressListResponse() {
    }

    public AddressListResponse(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
