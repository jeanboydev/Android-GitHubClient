package com.jeanboy.arch.data.net.entity;

import com.google.gson.annotations.SerializedName;
import com.jeanboy.arch.data.net.entity.received.PayLoadEntity;

import java.util.Date;

/**
 * Created by 乔晓松 on 2018/6/5 17:06
 */
public class RepositoryEventEntity {

    /**
     * {
     * "id": "7776328309",
     * "type": "CreateEvent",
     * "actor": {
     * "id": 5109696,
     * "login": "coolspan",
     * "display_login": "coolspan",
     * "gravatar_id": "",
     * "url": "https://api.github.com/users/coolspan",
     * "avatar_url": "https://avatars.githubusercontent.com/u/5109696?"
     * },
     * "repo": {
     * "id": 125461747,
     * "name": "coolspan/IOSRadarView",
     * "url": "https://api.github.com/repos/coolspan/IOSRadarView"
     * },
     * "payload": {
     * "ref": "v1.0",
     * "ref_type": "tag",
     * "master_branch": "master",
     * "description": " 仿支付宝芝麻信用雷达图",
     * "pusher_type": "user"
     * },
     * "public": true,
     * "created_at": "2018-06-05T07:41:13Z"
     * },
     * {
     * "id": "7776328294",
     * "type": "ReleaseEvent",
     * "actor": {
     * "id": 5109696,
     * "login": "coolspan",
     * "display_login": "coolspan",
     * "gravatar_id": "",
     * "url": "https://api.github.com/users/coolspan",
     * "avatar_url": "https://avatars.githubusercontent.com/u/5109696?"
     * },
     * "repo": {
     * "id": 125461747,
     * "name": "coolspan/IOSRadarView",
     * "url": "https://api.github.com/repos/coolspan/IOSRadarView"
     * },
     * "payload": {
     * "action": "published",
     * "release": {
     * "url": "https://api.github.com/repos/coolspan/IOSRadarView/releases/11322877",
     * "assets_url": "https://api.github.com/repos/coolspan/IOSRadarView/releases/11322877/assets",
     * "upload_url": "https://uploads.github.com/repos/coolspan/IOSRadarView/releases/11322877/assets{?name,label}",
     * "html_url": "https://github.com/coolspan/IOSRadarView/releases/tag/v1.0",
     * "id": 11322877,
     * "node_id": "MDc6UmVsZWFzZTExMzIyODc3",
     * "tag_name": "v1.0",
     * "target_commitish": "master",
     * "name": "",
     * "draft": false,
     * "author": {
     * "login": "coolspan",
     * "id": 5109696,
     * "node_id": "MDQ6VXNlcjUxMDk2OTY=",
     * "avatar_url": "https://avatars2.githubusercontent.com/u/5109696?v=4",
     * "gravatar_id": "",
     * "url": "https://api.github.com/users/coolspan",
     * "html_url": "https://github.com/coolspan",
     * "followers_url": "https://api.github.com/users/coolspan/followers",
     * "following_url": "https://api.github.com/users/coolspan/following{/other_user}",
     * "gists_url": "https://api.github.com/users/coolspan/gists{/gist_id}",
     * "starred_url": "https://api.github.com/users/coolspan/starred{/owner}{/repo}",
     * "subscriptions_url": "https://api.github.com/users/coolspan/subscriptions",
     * "organizations_url": "https://api.github.com/users/coolspan/orgs",
     * "repos_url": "https://api.github.com/users/coolspan/repos",
     * "events_url": "https://api.github.com/users/coolspan/events{/privacy}",
     * "received_events_url": "https://api.github.com/users/coolspan/received_events",
     * "type": "User",
     * "site_admin": false
     * },
     * "prerelease": false,
     * "created_at": "2018-03-18T10:23:04Z",
     * "published_at": "2018-06-05T07:41:13Z",
     * "assets": [],
     * "tarball_url": "https://api.github.com/repos/coolspan/IOSRadarView/tarball/v1.0",
     * "zipball_url": "https://api.github.com/repos/coolspan/IOSRadarView/zipball/v1.0",
     * "body": ""
     * }
     * },
     * "public": true,
     * "created_at": "2018-06-05T07:41:13Z"
     * }
     */

    public final static class EventType {
        public final static String CommitCommentEvent = "CommitCommentEvent";
        public final static String CreateEvent = "CreateEvent";
        /**
         * Represents a deleted branch or tag.
         */
        public final static String DeleteEvent = "DeleteEvent";
        public final static String ForkEvent = "ForkEvent";
        /**
         * Triggered when a Wiki page is created or updated.
         */
        public final static String GollumEvent = "GollumEvent";


        /**
         * Triggered when a GitHub App has been installed or uninstalled.
         */
        public final static String InstallationEvent = "InstallationEvent";
        /**
         * Triggered when a repository is added or removed from an installation.
         */
        public final static String InstallationRepositoriesEvent = "InstallationRepositoriesEvent";
        public final static String IssueCommentEvent = "IssueCommentEvent";
        public final static String IssuesEvent = "IssuesEvent";


        /**
         * Triggered when a user purchases, cancels, or changes their GitHub Marketplace plan.
         */
        public final static String MarketplacePurchaseEvent = "MarketplacePurchaseEvent";
        /**
         * Triggered when a user is added or removed as a collaborator to a repository, or has their permissions changed.
         */
        public final static String MemberEvent = "MemberEvent";
        /**
         * Triggered when an organization blocks or unblocks a user.
         */
        public final static String OrgBlockEvent = "OrgBlockEvent";
        /**
         * Triggered when a project card is created, updated, moved, converted to an issue, or deleted.
         */
        public final static String ProjectCardEvent = "ProjectCardEvent";
        /**
         * Triggered when a project column is created, updated, moved, or deleted.
         */
        public final static String ProjectColumnEvent = "ProjectColumnEvent";


        /**
         * Triggered when a project is created, updated, closed, reopened, or deleted.
         */
        public final static String ProjectEvent = "ProjectEvent";
        /**
         * made repository public
         */
        public final static String PublicEvent = "PublicEvent";
        public final static String PullRequestEvent = "PullRequestEvent";
        /**
         * Triggered when a pull request review is submitted into a non-pending state, the body is edited, or the review is dismissed.
         */
        public final static String PullRequestReviewEvent = "PullRequestReviewEvent";
        public final static String PullRequestReviewCommentEvent = "PullRequestReviewCommentEvent";


        public final static String PushEvent = "PushEvent";
        public final static String ReleaseEvent = "ReleaseEvent";
        public final static String WatchEvent = "WatchEvent";

        //Events of this type are not visible in timelines. These events are only used to trigger hooks.
        public final static String DeploymentEvent = "DeploymentEvent";
        public final static String DeploymentStatusEvent = "DeploymentStatusEvent";
        public final static String MembershipEvent = "MembershipEvent";
        public final static String MilestoneEvent = "MilestoneEvent";
        public final static String OrganizationEvent = "OrganizationEvent";
        public final static String PageBuildEvent = "PageBuildEvent";
        public final static String RepositoryEvent = "RepositoryEvent";
        public final static String StatusEvent = "StatusEvent";
        public final static String TeamEvent = "TeamEvent";
        public final static String TeamAddEvent = "TeamAddEvent";
        public final static String LabelEvent = "LabelEvent";

        //Events of this type are no longer delivered, but it's possible that they exist in timelines
        // of some users. You cannot createForRepo webhooks that listen to these events.
        public final static String DownloadEvent = "DownloadEvent";
        public final static String FollowEvent = "FollowEvent";
        public final static String ForkApplyEvent = "ForkApplyEvent";
        public final static String GistEvent = "GistEvent";
    }

    private String id;
    private String type;//EventType 具体值看定义类
    private UserInfoEntity actor;
    private RepositoryEntity repo;
    private PayLoadEntity payload;
    @SerializedName("public")
    private boolean isPublic;
    @SerializedName("created_at")
    private Date createdAt;

    public RepositoryEventEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserInfoEntity getActor() {
        return actor;
    }

    public void setActor(UserInfoEntity actor) {
        this.actor = actor;
    }

    public RepositoryEntity getRepo() {
        return repo;
    }

    public void setRepo(RepositoryEntity repo) {
        this.repo = repo;
    }

    public PayLoadEntity getPayload() {
        return payload;
    }

    public void setPayload(PayLoadEntity payload) {
        this.payload = payload;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RepositoryEventEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", actor=" + actor +
                ", repo=" + repo +
                ", payload=" + payload +
                ", isPublic=" + isPublic +
                ", createdAt=" + createdAt +
                '}';
    }
}
