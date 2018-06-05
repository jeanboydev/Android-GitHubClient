package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;
import com.jeanboy.arch.data.net.entity.received.CommitEntity;

/**
 * Created by 乔晓松 on 2018/6/5 15:35
 */
public class TagEntity {

    /**
     *
     * {
     "name": "v1.0",
     "zipball_url": "https://api.github.com/repos/coolspan/IOSRadarView/zipball/v1.0",
     "tarball_url": "https://api.github.com/repos/coolspan/IOSRadarView/tarball/v1.0",
     "commit": {
     "sha": "34bc50babefd900a3e3427457921ad50d2c18aa5",
     "url": "https://api.github.com/repos/coolspan/IOSRadarView/commits/34bc50babefd900a3e3427457921ad50d2c18aa5"
     },
     "node_id": "MDM6UmVmMTI1NDYxNzQ3OnYxLjA="
     }
     */

    private String name;
    @SerializedName("zipball_url")
    private String zipballUrl;
    @SerializedName("tarball_url")
    private String tarballUrl;
    private CommitEntity commit;
    @SerializedName("node_id")
    private String nodeId;

    public TagEntity() {
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

    public CommitEntity getCommit() {
        return commit;
    }

    public void setCommit(CommitEntity commit) {
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
        return "TagEntity{" +
                "name='" + name + '\'' +
                ", zipballUrl='" + zipballUrl + '\'' +
                ", tarballUrl='" + tarballUrl + '\'' +
                ", commit=" + commit +
                ", nodeId='" + nodeId + '\'' +
                '}';
    }
}
