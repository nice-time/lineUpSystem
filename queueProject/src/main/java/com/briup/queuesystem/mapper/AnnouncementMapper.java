package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineAnnouncement;
import com.briup.queuesystem.bean.ReslineCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnnouncementMapper {

    @Insert("insert into resline_announcement values(#{Info.id},#{Info.caption}," +
            "#{Info.contents},#{Info.createDate},#{Info.lastupdate})")
    Integer insert(@Param("Info") ReslineAnnouncement Info);

    @Update("")
    Integer update(ReslineAnnouncement Info);

    @Select("select * from resline_announcement order by lastupdate desc")
    List<ReslineAnnouncement> getAll();

    @Delete("<script>" +
            "delete from resline_announcement where id in " +
            "(" +
            "<foreach collection='list' item='String' index='index' separator=','>" +
            " #{String}" +
            "</foreach>" +
            ")" +
            "</script>")
    Integer del(List<String> list);

}
