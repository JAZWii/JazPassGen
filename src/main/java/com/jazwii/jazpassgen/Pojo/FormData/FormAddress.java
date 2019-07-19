package com.jazwii.jazpassgen.Pojo.FormData;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Entity.Model.Account;
import com.jazwii.jazpassgen.Singleton.RestViews;

import javax.persistence.*;

public class FormAddress {
    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "report_id", referencedColumnName = "id", nullable = false)
    })
    private Account account;

    @Column(name = "address_name", length = 128, nullable = false, unique = true)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String addressName;

    @Column(name = "full_name", length = 128, nullable = false)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String fullName;

    @Column(name = "address1", length = 128, nullable = false)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String address1;

    @Column(name = "address2", length = 128)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private String address2;

    @Column(name = "city", length = 128, nullable = false)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String city;

    @Column(name = "state", length = 128)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private String state;

    @Column(name = "zip", length = 5, nullable = false)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private int zip;

    @Column(name = "phone", length = 128)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private String phone;

    @Column(name = "country", length = 128)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private String country;

    public FormAddress() {
    }

    public FormAddress(Account account, String addressName, String fullName, String address1, String address2, String city, String state, int zip, String phone, String country) {
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
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
