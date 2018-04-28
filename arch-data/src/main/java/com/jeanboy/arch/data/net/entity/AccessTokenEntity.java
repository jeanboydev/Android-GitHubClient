package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class AccessTokenEntity {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
