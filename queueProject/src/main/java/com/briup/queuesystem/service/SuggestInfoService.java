package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineSuggestInfo;
import java.text.ParseException;
import java.util.List;

public interface SuggestInfoService {


  int insert(ReslineSuggestInfo reslineSuggestInfo) throws ParseException;

  Integer update(ReslineSuggestInfo reslineSuggestInfo);

  List<ReslineSuggestInfo> getAll();

  Integer del(List<String> id);
}
