package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineTableInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TableInfoMapper {


    @Insert("insert into resline_tableinfo values (#{Info.id},#{Info.number}," +
            "#{Info.state},#{Info.categoryId},NOW(),NOW())")
    Integer insert(@Param("Info") ReslineTableInfo Info);

    @Update("update resline_tableinfo set number=#{Info.number},state=#{Info.state}," +
            "category_id=#{Info.categroy_id},lastupdate=NOW() where id = #{Info.id} ")
    Integer update(@Param("Info") ReslineTableInfo Info);

    @Select("select * from resline_tableinfo order by lastupdate desc")
    List<ReslineTableInfo> getAll();

    @Delete("<script>" +
            "delete from resline_tableinfo where id in " +
            "(" +
            "<foreach collection='list' item='String' index='index' separator=','>" +
            " #{String}" +
            "</foreach>" +
            ")" +
            "</script>")
    Integer del(List<String> list);
}
