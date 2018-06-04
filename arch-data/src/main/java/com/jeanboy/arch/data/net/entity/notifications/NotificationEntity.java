package com.jeanboy.arch.data.net.entity.notifications;

import com.google.gson.annotations.SerializedName;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;

import java.util.Date;

/**
 * Created by 乔晓松 on 2018/6/1 18:43
 */
public class NotificationEntity {
    /*
    {
    "id": "1",
    "repository": {
      "id": 1296269,
      "node_id": "MDEwOlJlcG9zaXRvcnkxMjk2MjY5",
      "owner": {
        "login": "octocat",
        "id": 1,
        "node_id": "MDQ6VXNlcjE=",
        "avatar_url": "https://github.com/images/error/octocat_happy.gif",
        "gravatar_id": "",
        "url": "https://api.github.com/users/octocat",
        "html_url": "https://github.com/octocat",
        "followers_url": "https://api.github.com/users/octocat/followers",
        "following_url": "https://api.github.com/users/octocat/following{/other_user}",
        "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
        "organizations_url": "https://api.github.com/users/octocat/orgs",
        "repos_url": "https://api.github.com/users/octocat/repos",
        "events_url": "https://api.github.com/users/octocat/events{/privacy}",
        "received_events_url": "https://api.github.com/users/octocat/received_events",
        "type": "User",
        "site_admin": false
      },
      "name": "Hello-World",
      "full_name": "octocat/Hello-World",
      "description": "This your first repo!",
      "private": false,
      "fork": false,
      "url": "https://api.github.com/repos/octocat/Hello-World",
      "html_url": "https://github.com/octocat/Hello-World"
    },
    "subject": {
      "title": "Greetings",
      "url": "https://api.github.com/repos/octokit/octokit.rb/issues/123",
      "latest_comment_url": "https://api.github.com/repos/octokit/octokit.rb/issues/comments/123",
      "type": "Issue"
    },
    "reason": "subscribed",
    "unread": true,
    "updated_at": "2014-11-07T22:01:45Z",
    "last_read_at": "2014-11-07T22:01:45Z",
    "url": "https://api.github.com/notifications/threads/1"
  }
     */
//

    public static class ReasonType {
        public final static String ASSIGN = "updated_at";//You were assigned to the Issue.
        public final static String AUTHOR = "author";//You created the thread.
        public final static String COMMENT = "comment";//You commented on the thread.
        public final static String INVITATION = "invitation"; //You accepted an invitation to contribute to the repository.
        public final static String MANUAL = "manual";//You subscribed to the thread (via an Issue or Pull Request).
        public final static String MENTION = "mention";//You were specifically @mentioned in the content.
        public final static String STATE_CHANGE = "state_change";//You changed the thread state (for example, closing an Issue or merging a Pull Request).
        public final static String SUBSCRIBED = "subscribed";//You're watching the repository.
        public final static String TEAM_MENTION = "team_mention";//You're watching the repository.
    }

    private String id;
    private RepositoryEntity repository;
    private NotificationSubjectEntity subject;
    private String reason; // ReasonType
    private boolean unread;
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("last_read_at")
    private Date lastReadAt;
    private String url;

    public NotificationEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RepositoryEntity getRepository() {
        return repository;
    }

    public void setRepository(RepositoryEntity repository) {
        this.repository = repository;
    }

    public NotificationSubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(NotificationSubjectEntity subject) {
        this.subject = subject;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getLastReadAt() {
        return lastReadAt;
    }

    public void setLastReadAt(Date lastReadAt) {
        this.lastReadAt = lastReadAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "id='" + id + '\'' +
                ", repository=" + repository +
                ", subject=" + subject +
                ", reason='" + reason + '\'' +
                ", unread=" + unread +
                ", updatedAt=" + updatedAt +
                ", lastReadAt=" + lastReadAt +
                ", url='" + url + '\'' +
                '}';
    }
}
