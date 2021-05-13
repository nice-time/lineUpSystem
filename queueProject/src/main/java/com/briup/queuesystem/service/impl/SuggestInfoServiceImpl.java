package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.ReslineSuggestInfo;
import com.briup.queuesystem.mapper.SuggestInfoMapper;
import com.briup.queuesystem.service.SuggestInfoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SuggestInfoServiceImpl implements SuggestInfoService {

  @Resource
  SuggestInfoMapper suggestInfoMapper;

  @Override
  public int insert(ReslineSuggestInfo reslineSuggestInfo) throws ParseException {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String parse = formatter.format(date);
    reslineSuggestInfo.setTime(parse);
//    reslineSuggestInfo.setSuggestId("1");
    return suggestInfoMapper.insert(reslineSuggestInfo);
  }

  @Override
  public Integer update(ReslineSuggestInfo reslineSuggestInfo) {
    return null;
  }

  @Override
  public List<ReslineSuggestInfo> getAll() {
    return null;
  }

  @Override
  public Integer del(List<String> id) {
    return null;
  }
}
