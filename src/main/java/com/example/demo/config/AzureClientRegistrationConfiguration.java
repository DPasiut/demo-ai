package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
public class AzureClientRegistrationConfiguration {

    @Value("${spring.security.oauth2.client.registration.azure.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.azure.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.provider.azure.authorization-uri}")
    private String authorizationUri;
    @Value("${spring.security.oauth2.client.provider.azure.token-uri}")
    private String tokenUri;
    @Value("${spring.security.oauth2.client.provider.azure.issuer-uri}")
    private String issuerUri;
    @Value("${spring.security.oauth2.client.provider.azure.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.azureClientRegistration());
    }

    private ClientRegistration azureClientRegistration() {
        return ClientRegistration.withRegistrationId("azure")
            .clientId(clientId)
            .clientSecret(clientSecret)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/")
            .scope("openid", "profile", "email", "address", "phone")
            .authorizationUri(authorizationUri)
            .tokenUri(tokenUri)
            .jwkSetUri(jwkSetUri)
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .clientName("Azure")
            .build();
    }

}
