package com.jeanboy.arch.data.net.entity;

import com.jeanboy.arch.data.net.entity.received.CommitEntity;

/**
 * Created by 乔晓松 on 2018/6/5 15:16
 */
public class BranchEntity {
    /**
     *
     * {
         "name": "master",
         "commit": {
         "sha": "34bc50babefd900a3e3427457921ad50d2c18aa5",
         "url": "https://api.github.com/repos/coolspan/IOSRadarView/commits/34bc50babefd900a3e3427457921ad50d2c18aa5"
        }
     }
     */

    private String name;
    private CommitEntity commit;

    public BranchEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommitEntity getCommit() {
        return commit;
    }

    public void setCommit(CommitEntity commit) {
        this.commit = commit;
    }

    @Override
    public String toString() {
        return "BranchEntity{" +
                "name='" + name + '\'' +
                ", commit=" + commit +
                '}';
    }
}
