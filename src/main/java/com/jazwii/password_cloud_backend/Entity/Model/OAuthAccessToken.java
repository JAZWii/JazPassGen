package com.jazwii.password_cloud_backend.Entity.Model;

import com.jazwii.password_cloud_backend.Singleton.DatabaseConstants;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = DatabaseConstants.TABLE_OAUTH_ACCESS_TOKEN)
public class OAuthAccessToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "token_id", length = 255, nullable = false, unique = true)
    private String tokenId;

    @Lob
    @Column(name = "token", nullable = false, length=100000)
    private byte[] token;

    @Column(name = "authentication_id", length = 128, nullable = false, unique = true)
    private String authenticationId;

    @Column(name = "user_name", length = 255, nullable = false)
    private String username;

    @Column(name = "client_id", length = 255, nullable = false)
    private String clientId;

    @Lob
    @Column(name = "authentication", nullable = false, length=100000)
    private byte[] authentication;

    @Column(name = "refresh_token", length = 255, nullable = false)
    private String refreshToken;

    public OAuthAccessToken() {
    }

    public OAuthAccessToken(String tokenId, byte[] token, String authenticationId, String username, String clientId, byte[] authentication, String refreshToken) {
        this.tokenId = tokenId;
        this.token = token;
        this.authenticationId = authenticationId;
        this.username = username;
        this.clientId = clientId;
        this.authentication = authentication;
        this.refreshToken = refreshToken;
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

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

