package com.jeanboy.app.github.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeanboy.app.github.R;
import com.jeanboy.app.github.di.BaseDiActivity;
import com.jeanboy.app.github.ui.adapter.TrendingRepositoryAdapter;
import com.jeanboy.app.github.ui.adapter.UserRepositoryAdapter;
import com.jeanboy.app.github.ui.vm.UserRepositoryListViewModel;
import com.jeanboy.arch.base.ExtrasCallback;
import com.jeanboy.arch.base.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.arch.base.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.arch.base.adapter.recyclerview.decoration.SpaceItemDecoration;
import com.jeanboy.arch.base.helper.ToastHelper;
import com.jeanboy.arch.base.helper.ToolbarHelper;
import com.jeanboy.arch.data.net.entity.RepositoryEntity;
import com.jeanboy.recyclerviewhelper.RecyclerViewHelper;
import com.jeanboy.recyclerviewhelper.listener.LoadMoreListener;
import com.jeanboy.recyclerviewhelper.listener.TipsListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class UserRepositoryListActivity extends BaseDiActivity {

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srl_refresh;
    @BindView(R.id.list_container)
    RecyclerView list_container;


    @Inject
    UserRepositoryListViewModel userRepositoryListViewModel;

    private List<RepositoryEntity> dataList = new ArrayList<>();
    private UserRepositoryAdapter dataAdapter;
    private RecyclerViewHelper recyclerViewHelper;
    private boolean isRefreshing = false;
    private int currentPage = 1;

    private static final String KEY_USERNAME = "username";

    private String username;

    public static void startBy(Activity context, String username) {
        startActivity(context, UserRepositoryListActivity.class, new ExtrasCallback() {
            @Override
            public void onPutExtras(Bundle bundle) {
                bundle.putString(KEY_USERNAME, username);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_repository_list;
    }

    @Override
    protected void setupArguments(Bundle args) {
        super.setupArguments(args);
        username = args.getString(KEY_USERNAME);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolbarHelper.setToolBarTitle(getToolbar(), R.string.title_repos_list);
        ToolbarHelper.setToolbarHomeAsUp(this);


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

        dataAdapter = new UserRepositoryAdapter(dataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(UserRepositoryListActivity.this);
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

        dataAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, int position) {
                RepositoryEntity repositoryEntity = dataList.get(position);
                if (repositoryEntity == null) return;

                String fromRepoName = repositoryEntity.getFull_name();
                if (!fromRepoName.contains("/")) return;

                String[] params = fromRepoName.split("/");
                if (params.length != 2) return;
                RepositoryInfoActivity.startBy(UserRepositoryListActivity.this, params[0], params[1]);
            }
        });
    }

    @Override
    protected void initData() {
        currentPage = 1;
        loadNext();
    }

    private void loadNext() {
        LiveData<List<RepositoryEntity>> request = userRepositoryListViewModel.getReposList(username, currentPage);
        request.observe(this, new Observer<List<RepositoryEntity>>() {
            @Override
            public void onChanged(@Nullable List<RepositoryEntity> receivedEventModels) {
                if (isRefreshing) {
                    ToastHelper.toast(UserRepositoryListActivity.this, "刷新成功！");
                }
                isRefreshing = false;
                srl_refresh.setRefreshing(false);
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
                    srl_refresh.setEnabled(true);
                }
                dataList.addAll(receivedEventModels);
                dataAdapter.notifyDataSetChanged();
                currentPage++;
            }
        });
    }

}
