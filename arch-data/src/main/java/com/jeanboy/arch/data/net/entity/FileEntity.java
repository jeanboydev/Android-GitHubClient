package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 乔晓松 on 2018/6/5 15:00
 */
public class FileEntity {
    /**
     *
     * {
     "name": ".gitignore",
     "path": ".gitignore",
     "sha": "1d07efb03ae59ee9f94b3d9cd5b0d228aaca8f3a",
     "size": 1453,
     "url": "https://api.github.com/repos/coolspan/IOSRadarView/contents/.gitignore?ref=master",
     "html_url": "https://github.com/coolspan/IOSRadarView/blob/master/.gitignore",
     "git_url": "https://api.github.com/repos/coolspan/IOSRadarView/git/blobs/1d07efb03ae59ee9f94b3d9cd5b0d228aaca8f3a",
     "download_url": "https://raw.githubusercontent.com/coolspan/IOSRadarView/master/.gitignore",
     "type": "file",//dir
     "_links": {
     "self": "https://api.github.com/repos/coolspan/IOSRadarView/contents/.gitignore?ref=master",
     "git": "https://api.github.com/repos/coolspan/IOSRadarView/git/blobs/1d07efb03ae59ee9f94b3d9cd5b0d228aaca8f3a",
     "html": "https://github.com/coolspan/IOSRadarView/blob/master/.gitignore"
     }
     }
     */

    private String name;
    private String path;
    private String sha;
    private long size;//目录文件这里是0
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("download_url")
    private String downloadUrl;
    private String type;//file , dir

    public FileEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", sha='" + sha + '\'' +
                ", size=" + size +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
