package com.jazwii.password_cloud_backend.Pojo.FormData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jazwii.password_cloud_backend.Singleton.MessageConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormAddress {
    @NotNull(message = MessageConstants.INVALID_ACCOUNT_ID)
    @Size(min = 1, max = 64, message = MessageConstants.INVALID_ADDRESS_NAME)
    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("full_name")
    private String fullName;

    @NotNull(message = MessageConstants.INVALID_ADDRESS_ADDRESS1)
    @JsonProperty("address1")
    private String address1;

    @JsonProperty("address2")
    private String address2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @NotNull(message = MessageConstants.INVALID_ADDRESS_ZIP)
    @JsonProperty("zip")
    private String zip;

    @JsonProperty("phone")
    private String phone;

    @NotNull(message = MessageConstants.INVALID_ADDRESS_COUNTRY)
    @JsonProperty("country")
    private String country;

    public FormAddress() {
    }

    public FormAddress(String addressName, String fullName, String address1, String address2, String city, String state, String zip, String phone, String country) {
        this.addressName = addressName;
        this.fullName = fullName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.country = country;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
