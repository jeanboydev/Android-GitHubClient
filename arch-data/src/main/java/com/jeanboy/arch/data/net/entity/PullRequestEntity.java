package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 乔晓松 on 2018/6/1 15:38
 */
public class PullRequestEntity {

//    {
//        "url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/pulls/45",
//            "html_url": "https://github.com/ThirtyDegreesRay/OpenHub/pull/45",
//            "diff_url": "https://github.com/ThirtyDegreesRay/OpenHub/pull/45.diff",
//            "patch_url": "https://github.com/ThirtyDegreesRay/OpenHub/pull/45.patch"
//    }

    private String url;
    @SerializedName("diff_url")
    private String diffUrl;
    @SerializedName("patch_url")
    private String patchUrl;
    @SerializedName("html_url")
    private String htmlUrl;

    public PullRequestEntity() {
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
