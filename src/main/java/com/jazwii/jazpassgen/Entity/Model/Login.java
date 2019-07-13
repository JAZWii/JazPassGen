package com.jazwii.jazpassgen.Entity.Model;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Singleton.DatabaseConstants;
import com.jazwii.jazpassgen.Singleton.RestViews;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.TABLE_LOGIN)
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(RestViews.AccountPublicMinimal.class)
    private long id;

    @Column(name = "email", length = 128, nullable = false)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String email;

    @Column(name = "username", length = 64, nullable = false)
    @JsonView(RestViews.AccountPublicDetailed.class)
    private String username;

    @Column(name = "password", length = 128, nullable = false)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private String password;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(RestViews.AccountCriticalDetailed.class)
    private Date createDate = new Date();

    public Login() {
    }

    public Login(String email, String username, String password, Date createDate) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
