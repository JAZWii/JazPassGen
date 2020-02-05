package com.jazwii.password_cloud_backend.Entity.Model;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.password_cloud_backend.Singleton.ConfigConstants;
import com.jazwii.password_cloud_backend.Singleton.DatabaseConstants;
import com.jazwii.password_cloud_backend.Singleton.RestViews;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by root on 16/07/17.
 */
@Entity
@Table(name = DatabaseConstants.TABLE_PASSWORD_RECOVERY_ATTEMPT)
public class PasswordRecoveryAttempt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "code", length = 128, nullable = false)
    private String code = randomCode();

    @Column(name = "failed_attempts")
    @JsonView(RestViews.AccountPublicDetailed.class)
    private int attempts = 0;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "creator_id", referencedColumnName = "id", nullable = false)
    })
    private Account creator;

    @Column(name = "create_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    @Column(name = "removed", nullable = false)
    private boolean removed = false;

    public PasswordRecoveryAttempt() {
    }

    public PasswordRecoveryAttempt(Account creator, String password) {
        this.creator = creator;
        setPlainPassword(password);
    }

    private String randomCode() {
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i< ConfigConstants.RECOVER_PASSWORD_CODE_LENGTH; i++)
            temp.append(ConfigConstants.RECOVER_PASSWORD_CODE_CHARACTERSET.charAt(ThreadLocalRandom.current().nextInt(0, ConfigConstants.RECOVER_PASSWORD_CODE_CHARACTERSET.length())));
        return temp.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
