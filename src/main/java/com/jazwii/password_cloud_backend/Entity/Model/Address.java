package com.jazwii.password_cloud_backend.Entity.Model;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.password_cloud_backend.Singleton.DatabaseConstants;
import com.jazwii.password_cloud_backend.Singleton.RestViews;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = DatabaseConstants.TABLE_ADDRESS,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"account_id", "address_name"})
        })
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(RestViews.AddressPublicMinimal.class)
    private int id;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    })
    private Account account;

    @Column(name = "address_name", length = 128, nullable = false, unique = true)
    @JsonView(RestViews.AddressPublicMinimal.class)
    private String addressName;

    @Column(name = "former_address_name", length = 256)
    @JsonView(RestViews.AddressPublicCriticalMinimal.class)
    private String formerAddressName;
    
    @Column(name = "full_name", length = 128, nullable = false)
    @JsonView(RestViews.AddressPublicDetailed.class)
    private String fullName;

    @Column(name = "address1", length = 128, nullable = false)
    @JsonView(RestViews.AddressPublicDetailed.class)
    private String address1;

    @Column(name = "address2", length = 128)
    @JsonView(RestViews.AddressPublicDetailed.class)
    private String address2;

    @Column(name = "city", length = 128, nullable = false)
    @JsonView(RestViews.AddressPublicDetailed.class)
    private String city;

    @Column(name = "state", length = 128)
    @JsonView(RestViews.AddressPublicDetailed.class)
    private String state;

    @Column(name = "zip", length = 16, nullable = false)
    @JsonView(RestViews.AddressPublicDetailed.class)
    private String zip;

    @Column(name = "phone", length = 16)
    @JsonView(RestViews.AddressPublicDetailed.class)
    private String phone;

    @Column(name = "country", length = 128)
    @JsonView(RestViews.AddressPublicDetailed.class)
    private String country;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.AddressCriticalDetailed.class)
    private Date createDate = new Date();

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "removed_by", referencedColumnName = "id")
    })
    @JsonView(RestViews.AddressPublicCriticalMinimal.class)
    private Account removedBy;

    @Column(name = "remove_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.AddressPublicCriticalMinimal.class)
    private Date removeDate;

    @Column(name = "removed", nullable = false)
    @JsonView(RestViews.AddressPublicCriticalMinimal.class)
    private boolean removed = false;

    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "former_address_id", referencedColumnName = "id")
    })
    private Address formerAddress;

    public Address() {
    }

    public Address(Account account,String addressName, String fullName, String address1, String address2, String city, String state, String zip, String phone, String country, Date createDate) {
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
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFormerAddressName() {
        return formerAddressName;
    }

    public void setFormerAddressName(String formerAddressName) {
        this.formerAddressName = formerAddressName;
    }

    public Account getRemovedBy() {
        return removedBy;
    }

    public void setRemovedBy(Account removedBy) {
        this.removedBy = removedBy;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public Address getFormerAddress() {
        return formerAddress;
    }

    public void setFormerAddress(Address formerAddress) {
        this.formerAddress = formerAddress;
    }
}
