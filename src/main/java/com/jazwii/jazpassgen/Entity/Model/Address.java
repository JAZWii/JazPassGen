package com.jazwii.jazpassgen.Entity.Model;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Singleton.DatabaseConstants;
import com.jazwii.jazpassgen.Singleton.RestViews;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = DatabaseConstants.TABLE_LOGIN)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(RestViews.AccountPublicMinimal.class)
    private long id;

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

    @Column(name = "zip", length = 128, nullable = false)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String zip;

    @Column(name = "phone", length = 128)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private String phone;

    @Column(name = "country", length = 128)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private String country;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private Date createDate = new Date();

    public Address() {
    }

    public Address(String fullName, String address1, String address2, String city, String state, String zip, String phone, String country, Date createDate) {
        this.fullName = fullName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.country = country;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
