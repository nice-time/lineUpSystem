package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineCategory;
import com.briup.queuesystem.bean.ReslineTableInfo;

import java.util.List;

public interface TableInfoService {

    void insert(ReslineTableInfo reslineTableInfo);

    Integer update(ReslineTableInfo reslineTableInfo);

    List<ReslineTableInfo> getAll();

    Integer del(List<String> id);

}
