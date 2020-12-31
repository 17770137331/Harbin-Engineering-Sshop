package org.wumbuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wumbuk.dao.ActivityMapper;
import org.wumbuk.entity.Activity;
import org.wumbuk.service.ActivityService;

import java.util.List;

/**
 * @auther PeihaoYang
 * @date 2020/12/20 - 11:16
 */

@Service("activityService")
public class ActivityServieceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;


    @Override
    public List<Activity> getAliveActivies() {
        return activityMapper.getAliveActivities();
    }
}
