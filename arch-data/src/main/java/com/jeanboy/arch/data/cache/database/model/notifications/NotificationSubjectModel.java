package com.jeanboy.arch.data.cache.database.model.notifications;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 乔晓松 on 2018/6/4 17:53
 */
public class NotificationSubjectModel {

    public static class SubjectType {
        public final static String ISSUE = "Issue";
        public final static String PULLREQUEST = "PullRequest";
        public final static String COMMENT = "Commit";
    }

    private String title;
    private String url;
    @SerializedName("latest_comment_url")
    private String latestCommentUrl;
    private String type; //SubjectType

    public NotificationSubjectModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatestCommentUrl() {
        return latestCommentUrl;
    }

    public void setLatestCommentUrl(String latestCommentUrl) {
        this.latestCommentUrl = latestCommentUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
