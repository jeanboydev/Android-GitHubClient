package com.jeanboy.arch.data.net.entity.received;

/**
 * Created by jeanboy on 2018/4/28.
 */
public class PayLoadEntity {

    private String action;
    private ForkeeEntity forkee;

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
}
