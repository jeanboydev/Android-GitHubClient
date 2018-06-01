package com.jeanboy.arch.data.repository.params;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 乔晓松 on 2018/6/1 10:25
 */
public class AuthParams {

    private List<String> scopes;
    private String note;
    private String noteUrl;
    @SerializedName("client_id") private String clientId;
    @SerializedName("client_secret") private String clientSecret;

    public AuthParams() {
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
