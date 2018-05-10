package com.jeanboy.arch.data.cache.database.model.events;

import com.jeanboy.arch.data.cache.database.model.received.ActorModel;
import com.jeanboy.arch.data.cache.database.model.received.OrganizationModel;
import com.jeanboy.arch.data.cache.database.model.received.PayLoadModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;

/**
 * Created by 乔晓松 on 2018/5/10 14:35
 */
public class UserEventModel {

    private long id;
    private String type;
    private ActorModel actor;
    private RepositoryModel repo;
    private PayLoadModel payload;
    private boolean isPublic;
    private String created_at;
    private OrganizationModel org;

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

    public OrganizationModel getOrg() {
        return org;
    }

    public void setOrg(OrganizationModel org) {
        this.org = org;
    }
}
