package com.jeanboy.arch.data.repository.params;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by 乔晓松 on 2018/6/5 13:43
 */
public class MarkNotificationReadRequestParams {

    @SerializedName("last_read_at")
    private Date lastReadAt;

    public MarkNotificationReadRequestParams() {
        this.lastReadAt = new Date();
    }

    public Date getLastReadAt() {
        return lastReadAt;
    }

    public void setLastReadAt(Date lastReadAt) {
        this.lastReadAt = lastReadAt;
    }

    @Override
    public String toString() {
        return "MarkNotificationReadRequestParams{" +
                "lastReadAt=" + lastReadAt +
                '}';
    }
}
