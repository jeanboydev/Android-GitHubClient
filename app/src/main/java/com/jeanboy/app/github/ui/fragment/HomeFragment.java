package com.jeanboy.app.github.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiFragment;
import com.jeanboy.app.github.ui.adapter.ReceivedEventAdapter;
import com.jeanboy.arch.base.adapter.recyclerview.decoration.SpaceItemDecoration;
import com.jeanboy.arch.base.helper.ToolbarHelper;
import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class HomeFragment extends BaseDiFragment {

    @BindView(R.id.list_container)
    RecyclerView list_container;

    private List<ReceivedEventModel> dataList = new ArrayList<>();
    private ReceivedEventAdapter dataAdapter;


    @Inject
    public HomeFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setupArguments(Bundle args) {

    }

    @Override
    protected void setupView(View view, Bundle savedInstanceState) {
        ToolbarHelper.setToolBarTitle(getToolbar(), "Received Events");

        dataAdapter = new ReceivedEventAdapter(dataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list_container.setAdapter(dataAdapter);
        list_container.setLayoutManager(layoutManager);
        list_container.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_item_space)));

    }

    @Override
    protected void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add(new ReceivedEventModel());
        }
        dataAdapter.notifyDataSetChanged();
    }

}
