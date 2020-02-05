package com.jazwii.password_cloud_backend.Entity.Model;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.password_cloud_backend.Singleton.DatabaseConstants;
import com.jazwii.password_cloud_backend.Singleton.RestViews;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = DatabaseConstants.TABLE_LOGIN,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"account_id", "login_name"})
        })
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(RestViews.LoginPublicMinimal.class)
    private int id;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    })
    private Account account;

    @Column(name = "login_name", length = 128, nullable = false, unique = true)
    @JsonView(RestViews.LoginPublicMinimal.class)
    private String loginName;

    @Column(name = "former_login_name", length = 256)
    @JsonView(RestViews.LoginPublicCriticalMinimal.class)
    private String formerLoginName;

    @Column(name = "website", length = 128, nullable = false)
    @JsonView(RestViews.LoginPublicMinimal.class)
    private String website;

    @Column(name = "email", length = 128, nullable = false)
    @JsonView(RestViews.LoginPublicDetailed.class)
    private String email;

    @Column(name = "username", length = 64, nullable = false)
    @JsonView(RestViews.LoginPublicDetailed.class)
    private String username;

    @Column(name = "password", length = 128, nullable = false)
    @JsonView(RestViews.LoginCriticalDetailed.class)
    private String password;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.LoginCriticalDetailed.class)
    private Date createDate = new Date();

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "removed_by", referencedColumnName = "id")
    })
    @JsonView(RestViews.LoginPublicCriticalMinimal.class)
    private Account removedBy;

    @Column(name = "remove_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.LoginPublicCriticalMinimal.class)
    private Date removeDate;

    @Column(name = "removed", nullable = false)
    @JsonView(RestViews.LoginPublicCriticalMinimal.class)
    private boolean removed = false;

    @ManyToOne(targetEntity = Login.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "former_login_id", referencedColumnName = "id")
    })
    private Login formerLogin;
    
    public Login() {
    }

    public Login(Account account, String loginName, String website, String email, String username, String password, Date createDate) {
        this.account = account;
        this.loginName = loginName;
        this.website = website;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFormerLoginName() {
        return formerLoginName;
    }

    public void setFormerLoginName(String formerLoginName) {
        this.formerLoginName = formerLoginName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public Login getFormerLogin() {
        return formerLogin;
    }

    public void setFormerLogin(Login formerLogin) {
        this.formerLogin = formerLogin;
    }
}
