package com.jeanboy.arch.data.net.entity.events;

import com.google.gson.annotations.SerializedName;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.received.ActorEntity;
import com.jeanboy.arch.data.net.entity.received.OrganizationEntity;
import com.jeanboy.arch.data.net.entity.received.PayLoadEntity;

/**
 * Created by 乔晓松 on 2018/5/10.
 */
public class UserEventEntity {

//    {
//            id: "7620483151",
//            type: "WatchEvent",
//            actor: {
//                id: 5109696,
//                login: "coolspan",
//                display_login: "coolspan",
//                gravatar_id: "",
//                url: "https://api.github.com/users/coolspan",
//                avatar_url: "https://avatars.githubusercontent.com/u/5109696?"
//            },
//            repo: {
//                id: 85670,
//                name: "yui/yui3",
//                url: "https://api.github.com/repos/yui/yui3"
//            },
//            payload: {
//                action: "started"
//            },
//            public: true,
//            created_at: "2018-05-03T03:04:02Z",
//            org: {
//                    id: 38181,
//                    login: "yui",
//                    gravatar_id: "",
//                    url: "https://api.github.com/orgs/yui",
//                    avatar_url: "https://avatars.githubusercontent.com/u/38181?"
//              }
//    }
    private long id;
    private String type;
    private ActorEntity actor;
    private RepositoryEntity repo;
    private PayLoadEntity payload;
    @SerializedName("public")
    private boolean isPublic;
    private String created_at;
    private OrganizationEntity org;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public RepositoryEntity getRepo() {
        return repo;
    }

    public void setRepo(RepositoryEntity repo) {
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
