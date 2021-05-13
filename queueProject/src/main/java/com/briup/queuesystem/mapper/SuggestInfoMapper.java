package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineSuggestInfo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SuggestInfoMapper {


    @Insert("insert into resline_suggestInfo values(#{Info.suggestId},#{Info.name}," +
            "#{Info.number},#{Info.suggestion},STR_TO_DATE(#{Info.time},'%Y-%m-%d %H:%i:%s'))")
    Integer insert(@Param("Info") ReslineSuggestInfo Info);

    @Update("")
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
