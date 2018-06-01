package com.jeanboy.app.github.ui.vm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.arch.data.cache.database.model.AuthTokenModel;
import com.jeanboy.arch.data.cache.database.model.IssueModel;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.cache.database.model.received.RepositoryModel;
import com.jeanboy.arch.data.repository.FollowingRepository;
import com.jeanboy.arch.data.repository.OrganizationRepository;
import com.jeanboy.arch.data.repository.ReposRepository;
import com.jeanboy.arch.data.repository.SearchRepository;
import com.jeanboy.arch.data.repository.TokenRepository;
import com.jeanboy.arch.data.repository.UserRepository;
import com.jeanboy.arch.data.repository.params.AuthParams;

import java.util.List;

/**
 * Created by 乔晓松 on 2018/5/11 10:53
 */
public class TestApiViewModel extends ViewModel {

    private LiveData<UserInfoModel> userInfo;
    private LiveData<Boolean> checkFollowing;
    private LiveData<Boolean> checkFollowing2;
    private LiveData<Boolean> checkFollowing3;
    private LiveData<Boolean> checkFollowing4;
    private LiveData<List<UserInfoModel>> checkFollowing5;
    private LiveData<List<UserInfoModel>> checkFollowing6;
    private LiveData<List<UserInfoModel>> checkFollowing7;
    private LiveData<List<RepositoryModel>> checkFollowing8;
    private LiveData<List<RepositoryModel>> checkFollowing9;
    LiveData<AuthTokenModel> authorizations;
    LiveData<List<UserInfoModel>> searchUsers;
    LiveData<List<RepositoryModel>> searchRepos;
    LiveData<List<IssueModel>> searchIssues;

    private TokenRepository tokenRepository = new TokenRepository();
    private UserRepository userRepository = new UserRepository();
    private FollowingRepository followingRepository = new FollowingRepository();
    private OrganizationRepository organizationRepository = new OrganizationRepository();
    private ReposRepository reposRepository = new ReposRepository();
    private SearchRepository searchRepository = new SearchRepository();

    public LiveData<AuthTokenModel> authorizations(String username, String password, AuthParams authParams) {
        authorizations = tokenRepository.authorizations(username, password, authParams);
        return authorizations;
    }

    public LiveData<UserInfoModel> requestUserInfo(String accessToken, String username) {
        userInfo = userRepository.getUserInfo(accessToken, username);
        return userInfo;
    }

    public LiveData<Boolean> checkFollowing(String accessToken, String username) {
        checkFollowing = followingRepository.checkFollowing(username, accessToken);
        return checkFollowing;
    }

    public LiveData<Boolean> checkFollowing(String accessToken, String username, String targetUsername) {
        checkFollowing2 = followingRepository.checkFollowing(accessToken, username, targetUsername);
        return checkFollowing2;
    }

    public LiveData<Boolean> unfollowUser(String accessToken, String username) {
        checkFollowing3 = followingRepository.unfollowUser(accessToken, username);
        return checkFollowing3;
    }

    public LiveData<Boolean> followUser(String accessToken, String username) {
        checkFollowing4 = followingRepository.followUser(accessToken, username);
        return checkFollowing4;
    }

    public LiveData<List<UserInfoModel>> getFollowing(String accessToken, String username, int page) {
        checkFollowing5 = followingRepository.getFollowing(accessToken, username, page);
        return checkFollowing5;
    }

    public LiveData<List<UserInfoModel>> getFollowers(String accessToken, String username, int page) {
        checkFollowing6 = followingRepository.getFollowers(accessToken, username, page);
        return checkFollowing6;
    }

    public LiveData<List<UserInfoModel>> getOrgMembers(String accessToken, String username, int page) {
        checkFollowing7 = organizationRepository.getOrgMembers(accessToken, username, page);
        return checkFollowing7;
    }

    public LiveData<List<RepositoryModel>> getStarredRepos(String accessToken, String username, int page, String sort, String direction) {
        checkFollowing8 = reposRepository.getStarredRepos(accessToken, username, page, sort, direction);
        return checkFollowing8;
    }

    public LiveData<List<RepositoryModel>> getUserRepos(String accessToken, int page, String type, String sort, String direction) {
        checkFollowing9 = reposRepository.getUserRepos(accessToken, page, type, sort, direction);
        return checkFollowing9;
    }

    public LiveData<List<UserInfoModel>> searchUsers(String accessToken, String query, String sort, String order, int page) {
        searchUsers = searchRepository.searchUsers(accessToken, query, sort, order, page);
        return searchUsers;
    }

    public LiveData<List<RepositoryModel>> searchRepos(String accessToken, String query, String sort, String order, int page) {
        searchRepos = searchRepository.searchRepos(accessToken, query, sort, order, page);
        return searchRepos;
    }

    public LiveData<List<IssueModel>> searchIssues(String accessToken, String query, String sort, String order, int page) {
        searchIssues = searchRepository.searchIssues(accessToken, query, sort, order, page);
        return searchIssues;
    }

}
