package com.jeanboy.arch.data.net.entity;

import java.util.List;

/**
 * Created by 乔晓松 on 2018/6/1 15:04
 */
public class SearchWrapperEntity<T> {

    private int total_count;
    private boolean incomplete_results;
    private List<T> items;

    public SearchWrapperEntity(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
