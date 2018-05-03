package com.jeanboy.arch.data.cache.database.model;

import com.jeanboy.arch.data.cache.database.model.received.ActorModel;
import com.jeanboy.arch.data.cache.database.model.received.OrganizationModel;
import com.jeanboy.arch.data.cache.database.model.received.PayLoadModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class ReceivedEventModel {

    private String id;
    private String type;
    private ActorModel actor;
    private RepositoryModel repo;
    private PayLoadModel payload;
    private OrganizationModel org;
    private boolean isPublic;
    private long createdAt;

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

    public ActorModel getActor() {
        return actor;
    }

    public void setActor(ActorModel actor) {
        this.actor = actor;
    }

    public RepositoryModel getRepo() {
        return repo;
    }

    public void setRepo(RepositoryModel repo) {
        this.repo = repo;
    }

    public PayLoadModel getPayload() {
        return payload;
    }

    public void setPayload(PayLoadModel payload) {
        this.payload = payload;
    }

    public OrganizationModel getOrg() {
        return org;
    }

    public void setOrg(OrganizationModel org) {
        this.org = org;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
