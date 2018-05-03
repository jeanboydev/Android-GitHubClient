package com.jeanboy.arch.data.cache.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jeanboy on 2018/4/28.
 */
@Entity(tableName = "access_token")
public class AccessTokenModel {

    @PrimaryKey(autoGenerate = false)
    private long id = 1;
    @ColumnInfo(name = "access_token")
    private String accessToken;
    @ColumnInfo(name = "token_type")
    private String tokenType;
    @ColumnInfo(name = "expires_in")
    private long expiresIn;
    @ColumnInfo(name = "create_time")
    private long createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
