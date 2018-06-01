package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 乔晓松 on 2018/6/1 15:29
 */
public class LabelEntity {

    //    {
////        "id": 644660364,
////            "node_id": "MDU6TGFiZWw2NDQ2NjAzNjQ=",
////            "url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/labels/bug",
////            "name": "bug",
////            "color": "ee0701",
////            "default": true
////    }
    private long id;
    private String name;//bug
    private String color;//ee0701
    @SerializedName("default")
    private boolean isDefault;

    private String url;
    @SerializedName("node_id")
    private String nodeId;

    public LabelEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
