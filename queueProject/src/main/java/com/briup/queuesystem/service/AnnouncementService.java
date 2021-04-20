package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineAnnouncement;
import java.util.List;

public interface AnnouncementService {

  int insert(ReslineAnnouncement reslineAnnouncement);

  Integer update(ReslineAnnouncement reslineAnnouncement);

  List<ReslineAnnouncement> getAll();

  Integer del(List<String> id);

  Integer delOne(String id);

  ReslineAnnouncement selectById(String id);


}
