package com.jazwii.jazpassgen.Entity.Model;

import com.jazwii.jazpassgen.Singleton.ConfigConstants;
import com.jazwii.jazpassgen.Singleton.DatabaseConstants;
import com.jazwii.jazpassgen.Singleton.RestViews;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.TABLE_ACCOUNT)
public class Account implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(RestViews.AccountPublicMinimal.class)
    private long id;

    @Column(name = "username", length = 64, nullable = false, unique = true)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String username;

    @Column(name = "email", length = 128, nullable = false, unique = true)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String email;

    @Column(name = "password", length = 128, nullable = false)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private String password;

    @Column(name = "phone_number")
    @JsonView(RestViews.AccountPublicDetailed.class)
    private long phoneNumber;

    @Column(name = "first_name", length = 32)
    @JsonView(RestViews.AccountPublicMinimal.class)
    private String firstName;

    @Column(name = "last_name", length = 32)
    @JsonView(RestViews.AccountPublicMinimal.class)
    private String lastName;

    @Column(name = "enabled", nullable = false)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private int enabled = 0;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_roles", joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Column(name = "last_login_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private Date lastLoginDate = new Date();

    @Column(name = "create_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private Date createDate = new Date();

    @Column(name = "update_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private Date updateDate = new Date();

    @Column(name = "removed", nullable = false)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private boolean removed = false;

    public Account() {
    }

    public Account(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled == 1;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlainPassword(String password) {
        this.password = ConfigConstants.passwordEncoder().encode(password);
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean getRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public boolean isRemoved() {
        return removed;
    }
}
