package com.jeanboy.arch.data.cache.database.model;

import com.google.gson.annotations.SerializedName;
import com.jeanboy.arch.data.cache.database.model.received.CommitModel;

/**
 * Created by 乔晓松 on 2018/6/5 15:35
 */
public class TagModel {

    private String name;
    @SerializedName("zipball_url")
    private String zipballUrl;
    @SerializedName("tarball_url")
    private String tarballUrl;
    private CommitModel commit;
    @SerializedName("node_id")
    private String nodeId;

    public TagModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipballUrl() {
        return zipballUrl;
    }

    public void setZipballUrl(String zipballUrl) {
        this.zipballUrl = zipballUrl;
    }

    public String getTarballUrl() {
        return tarballUrl;
    }

    public void setTarballUrl(String tarballUrl) {
        this.tarballUrl = tarballUrl;
    }

    public CommitModel getCommit() {
        return commit;
    }

    public void setCommit(CommitModel commit) {
        this.commit = commit;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        return "TagModel{" +
                "name='" + name + '\'' +
                ", zipballUrl='" + zipballUrl + '\'' +
                ", tarballUrl='" + tarballUrl + '\'' +
                ", commit=" + commit +
                ", nodeId='" + nodeId + '\'' +
                '}';
    }
}
