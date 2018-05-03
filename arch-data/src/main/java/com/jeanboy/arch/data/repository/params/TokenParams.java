package com.jeanboy.arch.data.repository.params;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class TokenParams {

    private String clientID;
    private String clientSecret;
    private String code;

    public TokenParams(String clientID, String clientSecret, String code) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.code = code;
    }

    public String getClientID() {
        return clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getCode() {
        return code;
    }
}
