package com.jeanboy.arch.data.cache.database.model.received;

/**
 * Created by 乔晓松 on 2018/6/5 15:26
 */
public class CommitModel {

    private String sha;
    private String message;
    private boolean distinct;
    private String url;
    private AuthorModel author;

    public CommitModel() {
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

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "CommitModel{" +
                "sha='" + sha + '\'' +
                ", message='" + message + '\'' +
                ", distinct=" + distinct +
                ", url='" + url + '\'' +
                ", author=" + author +
                '}';
    }
}
