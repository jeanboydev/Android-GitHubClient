package com.jeanboy.app.github.ui.adapter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.github.R;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;

import java.util.List;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class TrendingRepositoryAdapter extends RecyclerBaseAdapter<RepositoryEntity> {

    public TrendingRepositoryAdapter(@NonNull List<RepositoryEntity> dataList) {
        super(dataList, R.layout.item_trending_repository);
    }

    @Override
    public void convert(BaseViewHolder holder, RepositoryEntity repositoryEntity, int position) {
        Log.w(TrendingRepositoryAdapter.class.getSimpleName(), "=====================================");
        Log.w(TrendingRepositoryAdapter.class.getSimpleName(), JSON.toJSONString(repositoryEntity));

        String repoName = repositoryEntity.getName();
        String description = repositoryEntity.getDescription();
        int starCount = repositoryEntity.getStargazers_count();
        int forksCount = repositoryEntity.getForks_count();
        int periodStarCount = repositoryEntity.getPeriod_stargazers_count();

        holder.setText(R.id.tv_title, repoName);
        holder.setText(R.id.tv_content, description);
        holder.setText(R.id.tv_stars_count, String.valueOf(starCount));
        holder.setText(R.id.tv_forks_count, String.valueOf(forksCount));
        holder.setText(R.id.tv_period_stars_count, String.valueOf(periodStarCount));
    }
}
