package com.jazwii.jazpassgen.Entity.Model;

import com.jazwii.jazpassgen.Singleton.DatabaseConstants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = DatabaseConstants.TABLE_OAUTH_REFRESH_TOKEN)
public class OAuthRefreshToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "token_id", length = 128, nullable = false, unique = true)
    private String tokenId;

    @Lob
    @Column(name = "token", nullable = false, length=100000)
    private byte[] token;

    @Lob
    @Column(name = "authentication", nullable = false, length=100000)
    private byte[] authentication;

    public OAuthRefreshToken() {
    }

    public OAuthRefreshToken(String tokenId, byte[] token, byte[] authentication) {
        this.tokenId = tokenId;
        this.token = token;
        this.authentication = authentication;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}

