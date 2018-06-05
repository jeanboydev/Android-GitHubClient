package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by 乔晓松 on 2018/6/5 18:29
 */
public class ReleaseEntity {

    private String id;
    @SerializedName("tag_name")
    private String tagName;
    @SerializedName("target_commitish")
    private String targetCommitish;
    private String name;
    private String body;
    @SerializedName("body_html")
    private String bodyHtml;
    @SerializedName("tarball_url")
    private String tarballUrl;
    @SerializedName("zipball_url")
    private String zipballUrl;

    private boolean draft;
    @SerializedName("prerelease")
    private boolean preRelease;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("published_at")
    private Date publishedAt;

    private UserInfoEntity author;


}
