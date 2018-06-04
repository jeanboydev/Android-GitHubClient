package com.jeanboy.app.github.ui.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jeanboy.app.github.config.AppSettings;
import com.jeanboy.app.github.ui.vm.TestApiViewModel;

/**
 * Created by 乔晓松 on 2018/5/11 15:32
 */
public class TestApi {

    public void test(LifecycleOwner activity, TestApiViewModel testApiViewModel) {

        Log.e("TestApi", "token: " + AppSettings.getAccessToken());

        LiveData<Boolean> booleanLiveData = testApiViewModel.markNotificationAsRead(AppSettings.getAccessToken(), "1");
        booleanLiveData.observe(activity, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Log.d("TestApi", "markNotificationAsRead: " + aBoolean);
            }
        });

        
//        LiveData<List<NotificationModel>> myNotifications = testApiViewModel.getMyNotifications(AppSettings.getAccessToken(), false, false);
//
//        myNotifications.observe(activity, new Observer<List<NotificationModel>>() {
//            @Override
//            public void onChanged(@Nullable List<NotificationModel> strings) {
//                Log.d("TestApi Notifications", "strings:" + strings);
//            }
//        });
//        LiveData<List<IssueModel>> searchIssues = testApiViewModel.searchIssues(AppSettings.getAccessToken(), "user:ThirtyDegreesRay+state:open", "created", "desc", 1);
//        searchIssues.observe(activity, new Observer<List<IssueModel>>() {
//            @Override
//            public void onChanged(@Nullable List<IssueModel> list) {
//                Log.d("TestApi searchIssues", "list:" + list);
//            }
//        });


//        LiveData<List<RepositoryModel>> searchRepos = testApiViewModel.searchRepos(AppSettings.getAccessToken(), "filedownload", "followers", "desc", 1);
//        searchRepos.observe(activity, new Observer<List<RepositoryModel>>() {
//            @Override
//            public void onChanged(@Nullable List<RepositoryModel> list) {
//                Log.d("TestApi searchRepos", "list:" + list);
//            }
//        });

//        LiveData<List<UserInfoModel>> searchUsers = testApiViewModel.searchUsers(AppSettings.getAccessToken(), "coolspan", "followers", "desc", 1);
//        searchUsers.observe(activity, new Observer<List<UserInfoModel>>() {
//            @Override
//            public void onChanged(@Nullable List<UserInfoModel> list) {
//                Log.d("TestApi searchUsers", "list:" + list);
//            }
//        });

//        AuthParams authParams = new AuthParams();
//        authParams.setClientId(AppConfig.CLIENT_ID);
//        authParams.setClientSecret(AppConfig.CLIENT_SECRET);
//        authParams.setNoteUrl(AppConfig.REDIRECT_URI);
//        authParams.setNote(MainApplication.getInstance().getPackageName());
//        authParams.setScopes(Arrays.asList("user", "repo", "gist", "notifications"));

//        LiveData<AuthTokenModel> authTokenModelLiveData = testApiViewModel
//                .authorizations("coolspan", "qxs000000.", authParams);
//        authTokenModelLiveData.observe(activity, new Observer<AuthTokenModel>() {
//            @Override
//            public void onChanged(@Nullable AuthTokenModel authTokenModel) {
//                Log.d("MainActivity", "authTokenModel:" + (authTokenModel != null ? authTokenModel.toString() : "null"));
//            }
//        });

//        LiveData<UserInfoModel> userInfoModelLiveData = testApiViewModel.requestUserInfo(AppSettings.getAccessToken(), "coolspan");
//        userInfoModelLiveData.observe(activity, new Observer<UserInfoModel>() {
//            @Override
//            public void onChanged(@Nullable UserInfoModel userInfoModel) {
//                Log.d("MainActivity", "requestUserInfo:" + (userInfoModel != null ? userInfoModel.toString() : "null"));
//            }
//        });
//
//        LiveData<Boolean> booleanLiveData = testApiViewModel.checkFollowing(AppSettings.getAccessToken(), "jeanboydev");
//        booleanLiveData.observe(activity, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//                Log.d("TestApi", "checkFollowing: " + aBoolean);
//            }
//        });
//
//        LiveData<Boolean> booleanLiveData2 = testApiViewModel.checkFollowing(AppSettings.getAccessToken(), "coolspan", "jeanboydev");
//        booleanLiveData2.observe(activity, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//                Log.d("MainActivity", "checkFollowing2: " + aBoolean);
//            }
//        });
//
//        LiveData<Boolean> booleanLiveData3 = testApiViewModel.unfollowUser(AppSettings.getAccessToken(), "jeanboydev");
//        booleanLiveData3.observe(activity, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//                Log.d("TestApi", "unfollowUser: " + aBoolean);
//            }
//        });
////
//        LiveData<Boolean> booleanLiveData4 = testApiViewModel.followUser(AppSettings.getAccessToken(), "jeanboydev");
//        booleanLiveData4.observe(activity, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//                Log.d("TestApi", "followUser: " + aBoolean);
//            }
//        });
////
//        LiveData<List<UserInfoModel>> booleanLiveData5 = testApiViewModel.getFollowing(AppSettings.getAccessToken(), "coolspan", 1);
//        booleanLiveData5.observe(activity, new Observer<List<UserInfoModel>>() {
//            @Override
//            public void onChanged(@Nullable List<UserInfoModel> list) {
//                Log.d("MainActivity", "getFollowing: " + (list != null ? list.size() : "null"));
//            }
//        });
//
//        LiveData<List<UserInfoModel>> booleanLiveData6 = testApiViewModel.getFollowers(AppSettings.getAccessToken(), "coolspan", 1);
//        booleanLiveData6.observe(activity, new Observer<List<UserInfoModel>>() {
//            @Override
//            public void onChanged(@Nullable List<UserInfoModel> list) {
//                Log.d("MainActivity", "getFollowers: " + (list != null ? list.size() : "null"));
//            }
//        });
//
//        LiveData<List<UserInfoModel>> booleanLiveData7 = testApiViewModel.getOrgMembers(AppSettings.getAccessToken(), "okamiy", 1);
//        booleanLiveData7.observe(activity, new Observer<List<UserInfoModel>>() {
//            @Override
//            public void onChanged(@Nullable List<UserInfoModel> list) {
//                Log.d("MainActivity", "getOrgMembers: " + (list != null ? list.size() : "null"));
//            }
//        });
//
//        LiveData<List<RepositoryModel>> booleanLiveData8 = testApiViewModel.getStarredRepos(AppSettings.getAccessToken(), "coolspan", 1, "desc", "full_name");
//        booleanLiveData8.observe(activity, new Observer<List<RepositoryModel>>() {
//            @Override
//            public void onChanged(@Nullable List<RepositoryModel> list) {
//                Log.d("MainActivity", "getStarredRepos: " + (list != null ? list.size() : "null"));
//            }
//        });
//
//        LiveData<List<RepositoryModel>> booleanLiveData9 = testApiViewModel.getUserRepos(AppSettings.getAccessToken(), 1, "all", "desc", "full_name");
//        booleanLiveData9.observe(activity, new Observer<List<RepositoryModel>>() {
//            @Override
//            public void onChanged(@Nullable List<RepositoryModel> list) {
//                Log.d("MainActivity", "getUserRepos: " + (list != null ? list.size() : "null"));
//            }
//        });
    }
}
