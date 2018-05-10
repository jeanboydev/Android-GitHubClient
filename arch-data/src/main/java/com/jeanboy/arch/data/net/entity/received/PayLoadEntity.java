package com.jeanboy.arch.data.net.entity.received;

import java.util.List;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class PayLoadEntity {

    /**
     * push event
     * {
     * "push_id": 2553163361,
     * "size": 4,
     * "distinct_size": 4,
     * "ref": "refs/heads/master",
     * "head": "bb060b83859dc95e397afce3a6d240c60db5ad74",
     * "before": "01a68115c5e59273b6c483a08b6b94554fdbc1aa",
     * "commits": [
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
     * ]
     * }
     */
    private String action;

    //ForkEvent
    private ForkeeEntity forkee;

    //PushEvent
    private String push_id;
    private int size;
    private int distinct_size;
    private String ref;
    private String head;
    private String before;
    private List<CommitEntity> commits;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ForkeeEntity getForkee() {
        return forkee;
    }

    public void setForkee(ForkeeEntity forkee) {
        this.forkee = forkee;
    }

    public String getPush_id() {
        return push_id;
    }

    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDistinct_size() {
        return distinct_size;
    }

    public void setDistinct_size(int distinct_size) {
        this.distinct_size = distinct_size;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<CommitEntity> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitEntity> commits) {
        this.commits = commits;
    }
}
