package com.github.scribejava.apis;

import com.github.scribejava.apis.service.YahooJapanConnectOAuthServiceImpl;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * Created by yamashiro-r on 2017/02/12.
 */
public class YahooJapanConnectApi extends DefaultApi20 {

    private static final YahooJapanConnectApi instance = new YahooJapanConnectApi();

    public static YahooJapanConnectApi instance() {
        return instance;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://auth.login.yahoo.co.jp/yconnect/v1/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://auth.login.yahoo.co.jp/yconnect/v1/authorization";
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public OAuth20Service createService(final OAuthConfig config) {
        return new YahooJapanConnectOAuthServiceImpl(this, config);
    }
}
