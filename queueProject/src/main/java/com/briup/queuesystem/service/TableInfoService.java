package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineTableInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TableInfoService {

  Integer insert(ReslineTableInfo reslineTableInfo);

  Integer update(ReslineTableInfo reslineTableInfo);

  List<ReslineTableInfo> getAll();

  Integer del(List<String> id);

}
