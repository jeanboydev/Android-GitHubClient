package com.jeanboy.app.github.ui.adapter;

import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;

import com.jeanboy.app.github.R;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;
import com.jeanboy.arch.data.cache.database.model.received.PayLoadModel;
import com.jeanboy.arch.data.net.entity.received.ForkeeEntity;

import java.util.List;

/**
 * Created by jeanboy on 2018/5/2.
 */
public class ReceivedEventAdapter extends RecyclerBaseAdapter<ReceivedEventModel> {


    public ReceivedEventAdapter(@NonNull List<ReceivedEventModel> dataList) {
        super(dataList, R.layout.item_received_event);
    }

    @Override
    public void convert(BaseViewHolder holder, ReceivedEventModel receivedEventModel, int position) {
        String username = "";
        String action = "";
        String projectName = "";
        if (receivedEventModel.getActor() != null) {
            username = receivedEventModel.getActor().getDisplayLogin();
        }

        if ("WatchEvent".equals(receivedEventModel.getType())) {
            action = "started";
//            PayLoadModel payload = receivedEventModel.getPayload();
//            action = payload.getAction();
//            if (TextUtils.isEmpty(action)) {
//                if (payload.getForkee() == null) {
//                    action = "created";
//                } else {
//                    ForkeeEntity forkee = payload.getForkee();
//                    action = forkee.isFork() ? "forked" : "";
//                }
//            }
        }else if ("CreateEvent".equals(receivedEventModel.getType())) {
            action = "created a repository";
        }else if ("ForkEvent".equals(receivedEventModel.getType())) {
            action = "created a repository";
        }

        if (receivedEventModel.getRepo() != null) {
            projectName = receivedEventModel.getRepo().getName();
        }

        String title = holder.getConvertView().getResources().getString(R.string.title_project,
                username, action, projectName);
        holder.setText(R.id.tv_title, Html.fromHtml(title));

        holder.setText(R.id.tv_project_name, receivedEventModel.getRepo().getName());
    }
}
