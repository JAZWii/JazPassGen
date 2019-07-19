package com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Entity.Model.Address;
import com.jazwii.jazpassgen.Singleton.RestViews;

import java.util.List;

public class AddressListResponse {
    @JsonProperty("addresses")
    @JsonView(RestViews.PublicMinimal.class)
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
