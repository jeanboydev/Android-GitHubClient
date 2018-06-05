package com.jeanboy.app.github.ui.fragment;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiFragment;
import com.jeanboy.app.github.ui.activity.RepositoryInfoActivity;
import com.jeanboy.app.github.ui.adapter.TrendingRepositoryAdapter;
import com.jeanboy.app.github.ui.vm.MainTrendingViewModel;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.base.adapter.recyclerview.decoration.SpaceItemDecoration;
import com.jeanboy.arch.base.helper.ToastHelper;
import com.jeanboy.arch.base.helper.ToolbarHelper;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.arch.data.repository.params.TrendingParams;
import com.jeanboy.recyclerviewhelper.RecyclerViewHelper;
import com.jeanboy.recyclerviewhelper.listener.TipsListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by jeanboy on 2018/4/25.
 */
public class TrendingFragment extends BaseDiFragment {

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srl_refresh;
    @BindView(R.id.list_container)
    RecyclerView list_container;

    private List<RepositoryEntity> dataList = new ArrayList<>();
    private TrendingRepositoryAdapter dataAdapter;
    private RecyclerViewHelper recyclerViewHelper;
    private boolean isRefreshing = false;

    @Inject
    MainTrendingViewModel mainTrendingViewModel;

    private TrendingParams params = null;
    private TrendingParams.Period period = TrendingParams.Period.Daily;

    @Inject
    public TrendingFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void setupView(View view, Bundle savedInstanceState) {
        ToolbarHelper.setToolBarTitle(getToolbar(), R.string.title_trending);

        srl_refresh.setEnabled(false);
        srl_refresh.setColorSchemeResources(R.color.colorPrimary);
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isRefreshing) return;
                isRefreshing = true;
                initData();
            }
        });

        dataAdapter = new TrendingRepositoryAdapter(dataList);

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

        dataAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, int position) {
                RepositoryEntity repositoryEntity = dataList.get(position);
                if (repositoryEntity == null) return;

                String fromRepoName = repositoryEntity.getName();
                if (!fromRepoName.contains("/")) return;

                String[] params = fromRepoName.split("/");
                if (params.length != 2) return;
                RepositoryInfoActivity.startBy(getActivity(), params[0], params[1]);
            }
        });
    }

    @Override
    protected void initData() {
        LiveData<List<RepositoryEntity>> request = mainTrendingViewModel.request(params);
        request.observe(this, new Observer<List<RepositoryEntity>>() {
            @Override
            public void onChanged(@Nullable List<RepositoryEntity> receivedEventModels) {
                if (isRefreshing) {
                    ToastHelper.toast(getActivity(), "刷新成功！");
                }
                isRefreshing = false;
                srl_refresh.setRefreshing(false);
                if (receivedEventModels == null) {
                    recyclerViewHelper.loadError();
                    return;
                }
                recyclerViewHelper.loadComplete(false);
                dataList.clear();
                srl_refresh.setEnabled(true);
                dataList.addAll(receivedEventModels);
                dataAdapter.notifyDataSetChanged();
            }
        });
    }

}
