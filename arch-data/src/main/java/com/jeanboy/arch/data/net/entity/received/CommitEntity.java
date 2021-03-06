package com.jeanboy.arch.data.net.entity.received;

/**
 * Created by jeanboy on 2018/5/10.
 */
public class CommitEntity {
    /**
     * {
     * "sha": "7efa6f992304e505ec6dbe847074776761636e12",
     * "author": {
     * "email": "coolspan@sina.cn",
     * "name": "coolspan"
     * },
     * "message": "Add the dynamic list interface to get the specified user(添加获取指定用户的动态列表接口)",
     * "distinct": true,
     * "url": "https://api.github.com/repos/jeanboydev/Android-GitHubClient/commits/7efa6f992304e505ec6dbe847074776761636e12"
     * }
     */

    private String sha;
    private String message;
    private boolean distinct;
    private String url;
    private AuthorEntity author;

    public CommitEntity() {
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "CommitEntity{" +
                "sha='" + sha + '\'' +
                ", message='" + message + '\'' +
                ", distinct=" + distinct +
                ", url='" + url + '\'' +
                ", author=" + author +
                '}';
    }
}
