package com.briup.queuesystem.service;

import com.briup.queuesystem.bean.ReslineAnnouncement;
import com.briup.queuesystem.bean.ReslineCategory;

import java.util.List;

public interface AnnouncementService {

    void insert(ReslineAnnouncement reslineAnnouncement);

    Integer update(ReslineAnnouncement reslineAnnouncement);

    List<ReslineAnnouncement> getAll();

    Integer del(List<String> id);



}
