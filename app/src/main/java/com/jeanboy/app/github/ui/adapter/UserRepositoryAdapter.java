package com.jeanboy.app.github.ui.adapter;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.helper.LanguageColorsHelper;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.base.util.DateUtil;
import com.jeanboy.arch.data.cache.database.model.received.ActorModel;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.repository.params.TrendingParams;

import java.util.Date;
import java.util.List;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class UserRepositoryAdapter extends RecyclerBaseAdapter<RepositoryEntity> {

    public UserRepositoryAdapter(@NonNull List<RepositoryEntity> dataList) {
        super(dataList, R.layout.item_user_repository);
    }

    @Override
    public void convert(BaseViewHolder holder, RepositoryEntity repositoryEntity, int position) {
        Log.w(UserRepositoryAdapter.class.getSimpleName(), "=====================================");
        Log.w(UserRepositoryAdapter.class.getSimpleName(), JSON.toJSONString(repositoryEntity));

        String username = "";
        String avatarUrl = "";
        UserInfoEntity owner = repositoryEntity.getOwner();
        if (owner != null) {
            username = owner.getLogin();
            avatarUrl = owner.getAvatar_url();
        }

        holder.setText(R.id.tv_username, username);
        ImageView avatarImageView = holder.getView(R.id.iv_avatar);
        Glide.with(holder.getContext()).load(avatarUrl)
                .apply(RequestOptions.circleCropTransform()).into(avatarImageView);

        String updatedAt = repositoryEntity.getUpdated_at();
        Date updateAtDate = DateUtil.formatUTC(updatedAt);
        String formatRecent = DateUtil.formatRecent(updateAtDate.getTime(), holder.getContext());
        holder.setText(R.id.tv_update_time, formatRecent);

        String repoName = repositoryEntity.getName();
        String description = repositoryEntity.getDescription();
        int starCount = repositoryEntity.getStargazers_count();
        int forksCount = repositoryEntity.getForks_count();
        int openIssuesCount = repositoryEntity.getOpen_issues_count();

        holder.setText(R.id.tv_title, repoName);
        holder.setText(R.id.tv_content, description);
        holder.setText(R.id.tv_stars_count, holder.getResources().getString(R.string.stars_count, starCount));
        holder.setText(R.id.tv_forks_count, holder.getResources().getString(R.string.forks_count, forksCount));
        holder.setText(R.id.tv_open_issues, holder.getResources().getString(R.string.open_issues_count, openIssuesCount));

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
