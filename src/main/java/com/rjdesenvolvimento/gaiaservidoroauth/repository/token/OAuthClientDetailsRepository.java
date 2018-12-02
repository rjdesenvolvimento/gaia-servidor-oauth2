package com.rjdesenvolvimento.gaiaservidoroauth.repository.token;

import com.rjdesenvolvimento.gaiaservidoroauth.domain.token.OAuthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, Long> {

    OAuthClientDetails findByClientId(String clientId);

    OAuthClientDetails findByResourceIds(String resourcesIds);

    OAuthClientDetails findByScope(String scope);
}
