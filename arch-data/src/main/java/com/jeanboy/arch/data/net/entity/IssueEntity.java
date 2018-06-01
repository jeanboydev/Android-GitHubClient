package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by 乔晓松 on 2018/6/1 15:19
 */
public class IssueEntity {

    /*
     {
            "url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/issues/117",
            "repository_url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub",
            "labels_url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/issues/117/labels{/name}",
            "comments_url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/issues/117/comments",
            "events_url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/issues/117/events",
            "html_url": "https://github.com/ThirtyDegreesRay/OpenHub/pull/117",
            "id": 320834396,
            "node_id": "MDExOlB1bGxSZXF1ZXN0MTg2MzczOTU1",
            "number": 117,
            "title": "Korean translate",
            "user": {
                "login": "ghost",
                "id": 10137,
                "node_id": "MDQ6VXNlcjEwMTM3",
                "avatar_url": "https://avatars3.githubusercontent.com/u/10137?v=4",
                "gravatar_id": "",
                "url": "https://api.github.com/users/ghost",
                "html_url": "https://github.com/ghost",
                "followers_url": "https://api.github.com/users/ghost/followers",
                "following_url": "https://api.github.com/users/ghost/following{/other_user}",
                "gists_url": "https://api.github.com/users/ghost/gists{/gist_id}",
                "starred_url": "https://api.github.com/users/ghost/starred{/owner}{/repo}",
                "subscriptions_url": "https://api.github.com/users/ghost/subscriptions",
                "organizations_url": "https://api.github.com/users/ghost/orgs",
                "repos_url": "https://api.github.com/users/ghost/repos",
                "events_url": "https://api.github.com/users/ghost/events{/privacy}",
                "received_events_url": "https://api.github.com/users/ghost/received_events",
                "type": "User",
                "site_admin": false
            },
            "labels": [],
            "state": "closed",
            "locked": false,
            "assignee": null,
            "assignees": [],
            "milestone": null,
            "comments": 0,
            "created_at": "2018-05-07T14:50:22Z",
            "updated_at": "2018-05-07T14:50:51Z",
            "closed_at": "2018-05-07T14:50:51Z",
            "author_association": "NONE",
            "pull_request": {
                "url": "https://api.github.com/repos/ThirtyDegreesRay/OpenHub/pulls/117",
                "html_url": "https://github.com/ThirtyDegreesRay/OpenHub/pull/117",
                "diff_url": "https://github.com/ThirtyDegreesRay/OpenHub/pull/117.diff",
                "patch_url": "https://github.com/ThirtyDegreesRay/OpenHub/pull/117.patch"
            },
            "body": "",
            "score": 1
        }
     */
    private long id;
    private String title;
    private String state; //open, closed
    private String body;
    private String url;
    @SerializedName("repository_url")
    private String repositoryUrl;
    @SerializedName("labels_url")
    private String labelsUrl;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("node_id")
    private String nodeId;
    private long number;
    private UserInfoEntity user;
    private List<LabelEntity> labels;
    private boolean locked;
    private UserInfoEntity assignee;
    private List<UserInfoEntity> assignees;
    private MilestoneEntity milestone;
    private long comments;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("closed_at")
    private Date closedAt;
    @SerializedName("author_association")
    private String authorAssociation; //OWNER, CONTRIBUTOR, NONE
    private long score;
    @SerializedName("pull_request")
    private PullRequestEntity pullRequest;

    public IssueEntity() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserInfoEntity getUser() {
        return user;
    }

    public void setUser(UserInfoEntity user) {
        this.user = user;
    }

    public List<LabelEntity> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelEntity> labels) {
        this.labels = labels;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public UserInfoEntity getAssignee() {
        return assignee;
    }

    public void setAssignee(UserInfoEntity assignee) {
        this.assignee = assignee;
    }

    public List<UserInfoEntity> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<UserInfoEntity> assignees) {
        this.assignees = assignees;
    }

    public MilestoneEntity getMilestone() {
        return milestone;
    }

    public void setMilestone(MilestoneEntity milestone) {
        this.milestone = milestone;
    }

    public long getComments() {
        return comments;
    }

    public void setComments(long comments) {
        this.comments = comments;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public void setAuthorAssociation(String authorAssociation) {
        this.authorAssociation = authorAssociation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public PullRequestEntity getPullRequest() {
        return pullRequest;
    }

    public void setPullRequest(PullRequestEntity pullRequest) {
        this.pullRequest = pullRequest;
    }
}
