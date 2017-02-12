package com.github.scribejava.apis.service;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.apache.commons.codec.binary.Base64;

import java.text.MessageFormat;

/**
 * Created by yamashiro-r on 2017/02/12.
 */
public class YahooJapanConnectOAuthServiceImpl extends OAuth20Service {
    /**
     * Default constructor
     *
     * @param api    OAuth2.0 api information
     * @param config OAuth 2.0 configuration param object
     */
    public YahooJapanConnectOAuthServiceImpl(final DefaultApi20 api, final OAuthConfig config) {
        super(api, config);
    }

    private String authorizationHeaderValue() {
        final String authorization = MessageFormat.format("{0}:{1}", getConfig().getApiKey(), getConfig().getApiSecret());
        final String encodeValue = Base64.encodeBase64String(authorization.getBytes());
        return "Basic " + encodeValue;
    }

    @Override
    protected OAuthRequest createAccessTokenRequest(final String code) {
        final OAuthConfig config = getConfig();
        final OAuthRequest request = new OAuthRequest(getApi().getAccessTokenVerb(), getApi().getAccessTokenEndpoint());
        request.addHeader("Authorization", this.authorizationHeaderValue());
        request.addParameter(OAuthConstants.CODE, code);
        request.addParameter(OAuthConstants.REDIRECT_URI, config.getCallback());
        request.addParameter(OAuthConstants.GRANT_TYPE, OAuthConstants.AUTHORIZATION_CODE);
        return request;
    }
}
