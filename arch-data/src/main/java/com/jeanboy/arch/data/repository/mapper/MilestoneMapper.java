package com.jeanboy.arch.data.repository.mapper;

import com.jeanboy.arch.data.cache.database.model.MilestoneModel;
import com.jeanboy.arch.data.cache.database.model.UserInfoModel;
import com.jeanboy.arch.data.net.entity.MilestoneEntity;
import com.jeanboy.arch.data.net.entity.UserInfoEntity;
import com.jeanboy.arch.data.repository.handler.MapperHandler;

/**
 * Created by 乔晓松 on 2018/6/1 16:17
 */
public class MilestoneMapper extends MapperHandler<MilestoneEntity, MilestoneModel> {
    @Override
    protected MilestoneModel transform(MilestoneEntity milestoneEntity) {
        MilestoneModel milestoneModel = new MilestoneModel();
        milestoneModel.setClosedAt(milestoneEntity.getClosedAt());
        milestoneModel.setClosedIssues(milestoneEntity.getClosedIssues());
        milestoneModel.setCreatedAt(milestoneEntity.getCreatedAt());
        UserInfoEntity userInfoEntity = milestoneEntity.getCreator();
        if (userInfoEntity!=null) {
            UserInfoModel userInfoModel = new UserInfoMapper().transform(userInfoEntity);
            milestoneModel.setCreator(userInfoModel);
        }
        milestoneModel.setDueOn(milestoneEntity.getDueOn());
        milestoneModel.setId(milestoneEntity.getId());
        milestoneModel.setDescription(milestoneEntity.getDescription());
        milestoneModel.setState(milestoneEntity.getState());
        milestoneModel.setNumber(milestoneEntity.getNumber());
        milestoneModel.setTitle(milestoneEntity.getTitle());
        milestoneModel.setUpdatedAt(milestoneEntity.getUpdatedAt());
        milestoneModel.setOpenIssues(milestoneEntity.getOpenIssues());
        return milestoneModel;
    }
}
