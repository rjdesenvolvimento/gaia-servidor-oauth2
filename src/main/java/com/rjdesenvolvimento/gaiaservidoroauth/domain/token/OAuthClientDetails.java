package com.rjdesenvolvimento.gaiaservidoroauth.domain.token;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "oauth_client_details", schema = "token")
public class OAuthClientDetails implements Serializable {

    @Id
    @Column(nullable = false)
    private String client_id;
    @Column(nullable = false)
    private String client_secret;
    @Column(nullable = false)
    private String scope;
    @Column(nullable = false)
    private String authorized_grant_types;
    private String web_server_redirect_uri;
    private String authorities;
    private String additional_information;
    private String autoapprove;
    private Integer access_token_validity;
    private Integer refresh_token_validity;

    public OAuthClientDetails() {
    }

    public OAuthClientDetails(String client_id, String client_secret, String scope, String authorized_grant_types,
                              String web_server_redirect_uri, String authorities, String additional_information,
                              String autoapprove, Integer access_token_validity, Integer refresh_token_validity) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.scope = scope;
        this.authorized_grant_types = authorized_grant_types;
        this.web_server_redirect_uri = web_server_redirect_uri;
        this.authorities = authorities;
        this.additional_information = additional_information;
        this.autoapprove = autoapprove;
        this.access_token_validity = access_token_validity;
        this.refresh_token_validity = refresh_token_validity;
    }
}