package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineAnnouncement;
import com.briup.queuesystem.bean.ReslineSuggestInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SuggestInfoMapper {


    @Insert("insert into resline_suggestInfo values(#{Info.suggestId},#{Info.name}," +
            "#{Info.number},#{Info.suggestion})")
    Integer insert(ReslineSuggestInfo Info);

    @Update("update resline_suggestInfo set #{}  where suggestId = #{}")
    Integer update(ReslineSuggestInfo Info);

    @Select("select * from resline_suggestInfo")
    List<ReslineSuggestInfo> getAll();

    @Delete("<script>" +
            "delete from resline_suggestInfo where suggestId in " +
            "(" +
            "<foreach collection='list' item='String' index='index' separator=','>" +
            " #{String}" +
            "</foreach>" +
            ")" +
            "</script>")
    Integer del(List<String> list);

}
