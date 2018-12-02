package com.rjdesenvolvimento.gaiaservidoroauth.domain.token;

import com.rjdesenvolvimento.gaiaservidoroauth.domain.EntidadeAbstrata;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "oauth_client_details", schema = "token")
public class OAuthClientDetails extends EntidadeAbstrata {

    @Column(nullable = false)
    private String clientId;
    @Column(nullable = false)
    private String clientSecret;
    private String resourceIds;
    @Column(nullable = false)
    private String scope;
    @Column(nullable = false)
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;

    public OAuthClientDetails(String clientId, String clientSecret, String resourceIds, String scope,
                              String authorizedGrantTypes, String webServerRedirectUri, String authorities,
                              Integer accessTokenValidity, Integer refreshTokenValidity, String additionalInformation,
                              String autoapprove) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.resourceIds = resourceIds;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.webServerRedirectUri = webServerRedirectUri;
        this.authorities = authorities;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.additionalInformation = additionalInformation;
        this.autoapprove = autoapprove;
    }
}