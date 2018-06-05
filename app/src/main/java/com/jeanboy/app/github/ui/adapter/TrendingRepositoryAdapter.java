package com.jeanboy.app.github.ui.adapter;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.helper.LanguageColorsHelper;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.base.util.StringUtil;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.repository.params.TrendingParams;

import java.util.List;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class TrendingRepositoryAdapter extends RecyclerBaseAdapter<RepositoryEntity> {

    private TrendingParams.Period period;

    public void setPeriod(TrendingParams.Period period) {
        this.period = period;
    }

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

        String periodString;
        if (TrendingParams.Period.Weekly == period) {
            periodString = holder.getResources().getString(R.string.trending_this_week, periodStarCount);
        } else if (TrendingParams.Period.Monthly == period) {
            periodString = holder.getResources().getString(R.string.trending_this_month, periodStarCount);
        } else {
            periodString = holder.getResources().getString(R.string.trending_today, periodStarCount);
        }

        holder.setText(R.id.tv_title, repoName);
        holder.setText(R.id.tv_content, description);
        holder.setText(R.id.tv_stars_count, holder.getResources().getString(R.string.stars_count, starCount));
        holder.setText(R.id.tv_forks_count, holder.getResources().getString(R.string.forks_count, forksCount));
        holder.setText(R.id.tv_period_stars_count, periodString);

        String language = repositoryEntity.getLanguage();
        holder.setVisible(R.id.language_container, false);
        if(!TextUtils.isEmpty(language)){
            holder.setVisible(R.id.language_container, true);
            int color = LanguageColorsHelper.INSTANCE.getColor(holder.getContext(), language);
            AppCompatImageView imageView = holder.getView(R.id.iv_language_color);
            imageView.setImageTintList(ColorStateList.valueOf(color));
            holder.setText(R.id.tv_language,language);
        }
    }
}
