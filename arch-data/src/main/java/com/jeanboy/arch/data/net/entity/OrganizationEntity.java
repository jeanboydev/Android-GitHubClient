package com.jeanboy.arch.data.net.entity;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class OrganizationEntity {

    /**
     * "org": {
     * "id": 26932212,
     * "login": "EOSIO",
     * "gravatar_id": "",
     * "url": "https://api.github.com/orgs/EOSIO",
     * "avatar_url": "https://avatars.githubusercontent.com/u/26932212?"
     * }
     */

    private String id;
    private String login;
    private String gravatar_id;
    private String url;
    private String avatar_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
