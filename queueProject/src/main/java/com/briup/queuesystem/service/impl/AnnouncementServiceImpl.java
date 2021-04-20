package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.ReslineAnnouncement;
import com.briup.queuesystem.mapper.AnnouncementMapper;
import com.briup.queuesystem.service.AnnouncementService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

  @Resource
  AnnouncementMapper announcementMapper;
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


  @Override
  public int insert(ReslineAnnouncement reslineAnnouncement) {
    Date date = new Date();
    String pDayFormat = formatter.format(date);
    reslineAnnouncement.setCreateDate(pDayFormat);
    reslineAnnouncement.setLastupdate(pDayFormat);
    return announcementMapper.insert(reslineAnnouncement);
  }

  @Override
  public Integer update(ReslineAnnouncement reslineAnnouncement) {
    Date date = new Date();
    String pDayFormat = formatter.format(date);
    reslineAnnouncement.setLastupdate(pDayFormat);
    return announcementMapper.update(reslineAnnouncement);
  }

  @Override
  public List<ReslineAnnouncement> getAll() {
    return announcementMapper.getAll();
  }

  @Override
  public Integer del(List<String> id) {
    return announcementMapper.del(id);
  }

  @Override
  public Integer delOne(String id) {
    return announcementMapper.delOne(id);
  }

  @Override
  public ReslineAnnouncement selectById(String id) {
    return announcementMapper.selectById(id);
  }
}
