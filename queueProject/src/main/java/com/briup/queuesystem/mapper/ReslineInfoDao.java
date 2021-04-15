package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineInfo;

public interface ReslineInfoDao {

    int insert(ReslineInfo record);

    int insertSelective(ReslineInfo record);
}