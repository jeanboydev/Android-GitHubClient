package com.jeanboy.arch.data.net.entity;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class RepoEntity {

    /**
     * {
     * "id": 93491617,
     * "name": "EOSIO/Documentation",
     * "url": "https://api.github.com/repos/EOSIO/Documentation"
     * }
     */

    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
