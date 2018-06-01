package com.jeanboy.arch.data.cache.database.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 乔晓松 on 2018/6/1 15:55
 */
public class PullRequestModel {

    private String url;
    @SerializedName("html_url")
    private String diffUrl;
    @SerializedName("patch_url")
    private String patchUrl;
    @SerializedName("html_url")
    private String htmlUrl;

    public PullRequestModel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
