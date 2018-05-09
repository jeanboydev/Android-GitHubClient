package com.jeanboy.app.github.ui.adapter;

import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.config.AppConfig;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.base.util.DateUtil;
import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;
import com.jeanboy.arch.data.cache.database.model.received.PayLoadModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.net.entity.received.ForkeeEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class ReceivedEventAdapter extends RecyclerBaseAdapter<ReceivedEventModel> {

    private Map<String, RepositoryEntity> repositoryMap;

    public ReceivedEventAdapter(@NonNull List<ReceivedEventModel> dataList, Map<String, RepositoryEntity> repositoryMap) {
        super(dataList, R.layout.item_received_event);
        this.repositoryMap = repositoryMap;
    }

    @Override
    public void convert(BaseViewHolder holder, ReceivedEventModel receivedEventModel, int position) {
        String username = "";
        String projectName = "";
        int actionStringId = AppConfig.getEventStringId(receivedEventModel.getType());
        String action = holder.getConvertView().getResources().getString(actionStringId);
        if (receivedEventModel.getActor() != null) {
            username = receivedEventModel.getActor().getDisplayLogin();
        }

        String repoName = "";
        String repoUrl = "";
        if (AppConfig.FORK_EVENT.equals(receivedEventModel.getType())) {
            PayLoadModel payload = receivedEventModel.getPayload();
            if (payload != null) {
                ForkeeEntity forkee = payload.getForkee();
                if (forkee != null) {
                    repoName = forkee.getName();
                    repoUrl = forkee.getUrl();
                }
            }
        } else {
            RepositoryModel repo = receivedEventModel.getRepo();
            if (repo != null) {
                repoName = repo.getName();
                repoUrl = repo.getUrl();
            }
        }

        holder.setVisible(R.id.ll_repos, false);
        if (!TextUtils.isEmpty(repoName) && !TextUtils.isEmpty(repoUrl)) {
            RepositoryEntity repositoryEntity = repositoryMap.get(repoName);
            if (repositoryEntity == null) {
                if (onLoadReposListener != null) {
                    onLoadReposListener.toLoad(repoName, repoUrl);
                }
            } else {
                holder.setVisible(R.id.ll_repos, true);
                holder.setText(R.id.tv_project_name, repositoryEntity.getFull_name());
                holder.setText(R.id.tv_project_desc, repositoryEntity.getDescription());
                holder.setText(R.id.tv_language, repositoryEntity.getLanguage());

                String watchers = holder.getConvertView().getResources().getString(R.string.watchers_count,
                        repositoryEntity.getWatchers_count());
                holder.setText(R.id.tv_watchers, watchers);

                long updateTime = DateUtil.formatUTC(repositoryEntity.getUpdated_at()).getTime();
                String formatRecent = DateUtil.formatRecent(updateTime);
                holder.setText(R.id.tv_update_time, formatRecent);
            }
        }


        if (receivedEventModel.getRepo() != null) {
            projectName = receivedEventModel.getRepo().getName();
        }

        long createdAt = receivedEventModel.getCreatedAt();
        String formatRecent = DateUtil.formatRecent(createdAt);

        String title = holder.getConvertView().getResources().getString(R.string.title_project,
                username, action, projectName, formatRecent);
        holder.setText(R.id.tv_title, Html.fromHtml(title));
    }

    private OnLoadReposListener onLoadReposListener;

    public void setOnLoadReposListener(OnLoadReposListener onLoadReposListener) {
        this.onLoadReposListener = onLoadReposListener;
    }

    public interface OnLoadReposListener {

        void toLoad(String name, String url);
    }
}
