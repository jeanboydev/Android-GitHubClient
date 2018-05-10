package com.jeanboy.arch.data.cache.database.model.received;

import com.jeanboy.arch.data.net.entity.received.CommitEntity;
import com.jeanboy.arch.data.net.entity.received.ForkeeEntity;

import java.util.List;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class PayLoadModel {

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
