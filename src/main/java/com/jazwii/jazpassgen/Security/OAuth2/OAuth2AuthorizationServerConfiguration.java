package com.jazwii.jazpassgen.Security.OAuth2;

import com.jazwii.jazpassgen.Singleton.ConfigConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${security.oauth2.client.access-token-validity-seconds:3600}")
    private int accessTokenExpiration;

    @Value("${security.oauth2.client.refresh-token-validity-seconds:604800}")
    private int refreshTokenExpiration;

    @Value("${security.oauth2.client.client-id}")
    String oAuth2ClientId;

    @Value("${security.oauth2.client.client-secret}")
    String oAuth2ClientSecret;

    @Value("${security.oauth2.client.resource-ids}")
    String oAuth2ResourceId;

    @Value("${security.oauth2.client.authorized-grant-types}")
    String[] grantTypes;

    @Value("${security.oauth2.client.scope}")
    String[] scope;

    @Value("${spring.datasource.driverClassName}")
    private String datasourceDriver;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> datasourceType;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return ConfigConstants.passwordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(datasourceDriver)
                .type(datasourceType)
                .url(datasourceUrl)
                .username(datasourceUsername)
                .password(datasourcePassword)
                .build();

        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) {
        configurer.authenticationManager(authenticationManager);
        configurer.userDetailsService(userDetailsService);
        configurer.tokenStore(tokenStore());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients();
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(oAuth2ClientId)
                .secret(oAuth2ClientSecret)
                .accessTokenValiditySeconds(accessTokenExpiration)
                .refreshTokenValiditySeconds(refreshTokenExpiration)
                .scopes(scope)
                .authorizedGrantTypes(grantTypes)
                .resourceIds(oAuth2ResourceId);
    }
}