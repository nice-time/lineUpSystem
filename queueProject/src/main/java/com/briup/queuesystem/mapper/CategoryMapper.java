package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryMapper {

    @Insert("insert into resline_category values(#{Info.id},#{Info.type}," +
            "#{Info.desc1},NOW(),NOW())")
    Integer insert(@Param("Info") ReslineCategory Info);

    @Update("update resline_category set type=#{Info.type},desc1=#{Info.desc1}," +
            "lastupdate=NOW() where id = #{Info.id}")
    Integer update(@Param("Info") ReslineCategory Info);

    @Select("select * from resline_category order by lastupdate desc")
    List<ReslineCategory> getAll();

    @Delete("<script>" +
            "delete from resline_category where id in " +
            "(" +
            "<foreach collection='list' item='String' index='index' separator=','>" +
            " #{String}" +
            "</foreach>" +
            ")" +
            "</script>")
    Integer del(List<String> list);

}
