package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineCategory;
import com.briup.queuesystem.utils.Message;
import java.util.List;

public interface CategoryService {

  Message insert(ReslineCategory reslineCategory);

  Message update(ReslineCategory reslineCategory);

  Message getAll();

  Message del(List<String> id);

}
