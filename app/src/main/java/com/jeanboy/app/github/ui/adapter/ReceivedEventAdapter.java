package com.jeanboy.app.github.ui.adapter;

import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jeanboy.app.github.R;
import com.jeanboy.app.github.config.AppConfig;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.base.util.DateUtil;
import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;
import com.jeanboy.arch.data.cache.database.model.received.ActorModel;
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
        Log.w(ReceivedEventAdapter.class.getSimpleName(), "=====================================");
        Log.w(ReceivedEventAdapter.class.getSimpleName(), JSON.toJSONString(receivedEventModel));

        String username = "";
        String avatarUrl = "";
        ActorModel actor = receivedEventModel.getActor();
        if (actor != null) {
            username = actor.getDisplayLogin();
            avatarUrl = actor.getAvatarUrl();
        }

        int actionStringId = AppConfig.getEventStringId(receivedEventModel.getType());
        String action = holder.getConvertView().getResources().getString(actionStringId);

        String fromRepoName = "";
        String fromRepoUrl = "";
        RepositoryModel repo = receivedEventModel.getRepo();
        if (repo != null) {
            fromRepoName = repo.getName();
            fromRepoUrl = repo.getUrl();
        }

        String toRepoName = "";
        String toRepoUrl = "";
        String branch = "";
        if (AppConfig.FORK_EVENT.equals(receivedEventModel.getType())) {
            PayLoadModel payload = receivedEventModel.getPayload();
            if (payload != null) {
                ForkeeEntity forkee = payload.getForkee();
                if (forkee != null) {
                    toRepoName = forkee.getFull_name();
                    toRepoUrl = forkee.getUrl();
                }
            }
        } else if (AppConfig.PUSH_EVENT.equals(receivedEventModel.getType())) {
            PayLoadModel payload = receivedEventModel.getPayload();
            if (payload != null) {
                String ref = payload.getRef();
                if (ref.contains("/")) {
                    branch = ref.substring(ref.lastIndexOf("/") + 1, ref.length());
                }
            }
        }

        String content = holder.getConvertView().getResources().getString(R.string.title_normal_event,
                action, fromRepoName);
        if (AppConfig.FORK_EVENT.equals(receivedEventModel.getType())) {
            content = holder.getConvertView().getResources().getString(R.string.title_fork_event,
                    action, toRepoName, fromRepoName);
        } else if (AppConfig.PUSH_EVENT.equals(receivedEventModel.getType())) {
            content = holder.getConvertView().getResources().getString(R.string.title_push_event,
                    action, branch, fromRepoName);
        } else if (AppConfig.PUBLIC_EVENT.equals(receivedEventModel.getType())) {
            content = holder.getConvertView().getResources().getString(R.string.title_public_event,
                    action, fromRepoName);
        }

        long createdAt = receivedEventModel.getCreatedAt();
        String formatRecent = DateUtil.formatRecent(createdAt, holder.getConvertView().getContext());

        holder.setText(R.id.tv_username, username);
        holder.setText(R.id.tv_create_at, formatRecent);
        holder.setText(R.id.tv_content, Html.fromHtml(content));
        ImageView avatarImageView = holder.getView(R.id.iv_avatar);
        Glide.with(holder.getConvertView().getContext()).load(avatarUrl)
                .apply(RequestOptions.circleCropTransform()).into(avatarImageView);
    }
}
