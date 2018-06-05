package com.jeanboy.arch.data.cache.database.model;

import com.jeanboy.arch.data.cache.database.model.received.CommitModel;

/**
 * Created by 乔晓松 on 2018/6/5 15:18
 */
public class BranchModel {

    private String name;
    private CommitModel commit;

    public BranchModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommitModel getCommit() {
        return commit;
    }

    public void setCommit(CommitModel commit) {
        this.commit = commit;
    }

    @Override
    public String toString() {
        return "BranchModel{" +
                "name='" + name + '\'' +
                ", commit=" + commit +
                '}';
    }
}
