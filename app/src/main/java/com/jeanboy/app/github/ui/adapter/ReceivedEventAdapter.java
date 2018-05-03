package com.jeanboy.app.github.ui.adapter;

import android.support.annotation.NonNull;

import com.jeanboy.app.github.R;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;

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

    }
}
