package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.IssueModel;
import com.jeanboy.arch.data.cache.database.model.LabelModel;
import com.jeanboy.arch.data.cache.database.model.MilestoneModel;
import com.jeanboy.arch.data.cache.database.model.PullRequestModel;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.net.entity.IssueEntity;
import com.jeanboy.arch.data.net.entity.LabelEntity;
import com.jeanboy.arch.data.net.entity.MilestoneEntity;
import com.jeanboy.arch.data.net.entity.PullRequestEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

import java.util.List;

/**
 * Created by 乔晓松 on 2018/6/1 15:58
 */
public class IssueMapper extends MapperHandler<IssueEntity, IssueModel> {

    @Override
    protected IssueModel transform(IssueEntity issueEntity) {
        IssueModel issueModel = new IssueModel();

        issueModel.setAuthorAssociation(issueEntity.getAuthorAssociation());
        issueModel.setBody(issueEntity.getBody());
        issueModel.setClosedAt(issueEntity.getClosedAt());
        issueModel.setComments(issueEntity.getComments());
        issueModel.setCreatedAt(issueEntity.getCreatedAt());
        issueModel.setEventsUrl(issueEntity.getEventsUrl());
        issueModel.setCommentsUrl(issueEntity.getCommentsUrl());
        issueModel.setHtmlUrl(issueEntity.getHtmlUrl());
        issueModel.setId(issueEntity.getId());
        issueModel.setLocked(issueEntity.isLocked());
        issueModel.setNodeId(issueEntity.getNodeId());
        issueModel.setUrl(issueEntity.getUrl());
        issueModel.setState(issueEntity.getState());
        issueModel.setTitle(issueEntity.getTitle());
        issueModel.setScore(issueEntity.getScore());
        issueModel.setUpdatedAt(issueEntity.getUpdatedAt());
        UserInfoEntity issueEntityUser = issueEntity.getUser();
        UserInfoEntity assignee = issueEntity.getAssignee();
        if (assignee != null) {
            UserInfoModel userInfoModel = new UserInfoMapper().transform(assignee);
            issueModel.setAssignee(userInfoModel);
        }
        List<UserInfoEntity> userInfoEntityList = issueEntity.getAssignees();
        if (userInfoEntityList != null) {
            List<UserInfoModel> userInfoModels = new UserInfoMapper().transform(userInfoEntityList);
            issueModel.setAssignees(userInfoModels);
        }
        if (issueEntityUser != null) {
            UserInfoModel userInfoModel = new UserInfoMapper().transform(issueEntityUser);
            issueModel.setUser(userInfoModel);
        }
        PullRequestEntity issueEntityPullRequest = issueEntity.getPullRequest();
        if (issueEntityPullRequest != null) {
            PullRequestModel pullRequestModel =
                    new PullRequestMapper().transform(issueEntityPullRequest);
            issueModel.setPullRequest(pullRequestModel);
        }
        List<LabelEntity> issueEntityLabels = issueEntity.getLabels();
        if (issueEntityLabels != null) {
            List<LabelModel> labelModels = new LabelMapper().transform(issueEntityLabels);
            issueModel.setLabels(labelModels);
        }
        MilestoneEntity milestoneEntity = issueEntity.getMilestone();
        if (milestoneEntity != null) {
            MilestoneModel milestoneModel = new MilestoneMapper().transform(milestoneEntity);
            issueModel.setMilestone(milestoneModel);
        }

        return issueModel;
    }

}
