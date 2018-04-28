package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class ReceivedEventEntity {
    /**
     * {
     *         "id": "7600824528",
     *         "type": "WatchEvent",
     *         "actor": {
     *             "id": 10704521,
     *             "login": "hongyangAndroid",
     *             "display_login": "hongyangAndroid",
     *             "gravatar_id": "",
     *             "url": "https://api.github.com/users/hongyangAndroid",
     *             "avatar_url": "https://avatars.githubusercontent.com/u/10704521?"
     *         },
     *         "repo": {
     *             "id": 93491617,
     *             "name": "EOSIO/Documentation",
     *             "url": "https://api.github.com/repos/EOSIO/Documentation"
     *         },
     *         "payload": {
     *             "action": "started"
     *         },
     *         "public": true,
     *         "created_at": "2018-04-28T06:53:12Z",
     *         "org": {
     *             "id": 26932212,
     *             "login": "EOSIO",
     *             "gravatar_id": "",
     *             "url": "https://api.github.com/orgs/EOSIO",
     *             "avatar_url": "https://avatars.githubusercontent.com/u/26932212?"
     *         }
     *     }
     */

    private String id;
    private String type;
    private ActorEntity actor;
    private RepoEntity repo;
    private PayLoadEntity payload;
    @SerializedName("public")
    private boolean isPublic;
    private String created_at;
    private OrganizationEntity org;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ActorEntity getActor() {
        return actor;
    }

    public void setActor(ActorEntity actor) {
        this.actor = actor;
    }

    public RepoEntity getRepo() {
        return repo;
    }

    public void setRepo(RepoEntity repo) {
        this.repo = repo;
    }

    public PayLoadEntity getPayload() {
        return payload;
    }

    public void setPayload(PayLoadEntity payload) {
        this.payload = payload;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public OrganizationEntity getOrg() {
        return org;
    }

    public void setOrg(OrganizationEntity org) {
        this.org = org;
    }
}
