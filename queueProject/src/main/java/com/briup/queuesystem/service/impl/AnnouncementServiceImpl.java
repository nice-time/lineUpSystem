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

  @Override
  public int insert(ReslineAnnouncement reslineAnnouncement) {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String pDayFormat = formatter.format(date);
    reslineAnnouncement.setCreateDate(pDayFormat);
    reslineAnnouncement.setLastupdate(pDayFormat);
    return announcementMapper.insert(reslineAnnouncement);

  }

  @Override
  public Integer update(ReslineAnnouncement reslineAnnouncement) {
    return null;
  }

  @Override
  public List<ReslineAnnouncement> getAll() {
    return null;
  }

  @Override
  public Integer del(List<String> id) {
    return null;
  }
}
