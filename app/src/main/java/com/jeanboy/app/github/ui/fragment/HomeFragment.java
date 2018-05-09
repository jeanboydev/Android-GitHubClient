package com.jeanboy.app.github.ui.fragment;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiFragment;
import com.jeanboy.app.github.ui.adapter.ReceivedEventAdapter;
import com.jeanboy.app.github.ui.vm.MainHomeViewModel;
import com.jeanboy.arch.base.adapter.recyclerview.decoration.SpaceItemDecoration;
import com.jeanboy.arch.base.helper.ToolbarHelper;
import com.jeanboy.arch.data.cache.database.model.ReceivedEventModel;
import com.jeanboy.recyclerviewhelper.RecyclerViewHelper;
import com.jeanboy.recyclerviewhelper.footer.FooterState;
import com.jeanboy.recyclerviewhelper.listener.LoadMoreListener;
import com.jeanboy.recyclerviewhelper.listener.OnFooterChangeListener;
import com.jeanboy.recyclerviewhelper.listener.TipsListener;

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
    private RecyclerViewHelper recyclerViewHelper;


    @Inject
    MainHomeViewModel mainHomeViewModel;

    private int currentPage = 1;

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

        recyclerViewHelper = new RecyclerViewHelper(list_container, dataAdapter);
        //设置没有数据的Tips
        recyclerViewHelper.setTipsEmptyView(R.layout.view_data_empty);
        //设置加载中的Tips
        recyclerViewHelper.setTipsLoadingView(R.layout.view_data_loading);
        //设置加载失败的Tips
        recyclerViewHelper.setTipsErrorView(R.layout.view_data_error);
        //默认加载更多 footer 也可自定义
        recyclerViewHelper.useDefaultFooter();
        //加载失败，没有数据时Tips的接口
        recyclerViewHelper.setTipsListener(new TipsListener() {
            @Override
            public void retry() {
                initData();
            }
        });
        //加载更多的接口
        recyclerViewHelper.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void loadMore() {
                loadNext();
            }
        });

//        mainHomeViewModel = ViewModelProviders.of(this).get(MainHomeViewModel.class);
    }

    private void loadNext() {
        LiveData<List<ReceivedEventModel>> request = mainHomeViewModel.request(currentPage);
        request.observe(this, new Observer<List<ReceivedEventModel>>() {
            @Override
            public void onChanged(@Nullable List<ReceivedEventModel> receivedEventModels) {
                if (receivedEventModels == null) {
                    if (currentPage == 1) {
                        recyclerViewHelper.loadError();
                    } else {
                        recyclerViewHelper.loadComplete(false);
                    }
                    return;
                }
                recyclerViewHelper.loadComplete(true);
                if (currentPage == 1) {
                    dataList.clear();
                }
                dataList.addAll(receivedEventModels);
                dataAdapter.notifyDataSetChanged();
                currentPage++;
            }
        });
    }

    @Override
    protected void initData() {
        currentPage = 1;
        loadNext();
    }

}
