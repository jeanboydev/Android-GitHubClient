package com.jeanboy.arch.data.cache.database.model.notifications;

import com.google.gson.annotations.SerializedName;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;

import java.util.Date;

/**
 * Created by 乔晓松 on 2018/6/4 17:52
 */
public class NotificationModel {

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
    private RepositoryModel repository;
    private NotificationSubjectModel subject;
    private String reason; // ReasonType
    private boolean unread;
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("last_read_at")
    private Date lastReadAt;
    private String url;

    public NotificationModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RepositoryModel getRepository() {
        return repository;
    }

    public void setRepository(RepositoryModel repository) {
        this.repository = repository;
    }

    public NotificationSubjectModel getSubject() {
        return subject;
    }

    public void setSubject(NotificationSubjectModel subject) {
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
}
