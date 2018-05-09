package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;
import com.jeanboy.arch.data.net.entity.received.ActorEntity;
import com.jeanboy.arch.data.net.entity.received.OrganizationEntity;
import com.jeanboy.arch.data.net.entity.received.PayLoadEntity;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class ReceivedEventEntity {

    private String id;
    private String type;
    private ActorEntity actor;
    private RepositoryEntity repo;
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
