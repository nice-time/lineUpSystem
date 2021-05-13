package com.briup.queuesystem.mapper;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineCategoryExtends;
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


    @Insert("insert into resline_tableinfo(number,state,category_id,createDate,lastupdate) values (#{Info.number}," +
            "#{Info.state},#{Info.category_id},NOW(),NOW())")
    Integer insert(@Param("Info") ReslineTableInfo Info);

    @Update("update resline_tableinfo set number=#{Info.number},state=#{Info.state}," +
            "category_id=#{Info.category_id},lastupdate=NOW() where id = #{Info.id} ")
    Integer update(@Param("Info") ReslineTableInfo Info);

    @Select("select a.*,b.id type_id,b.type from resline_tableinfo a,resline_category b where a.category_id = b.id order by a.lastupdate desc")
    List<ReslineCategoryExtends> getAll();

    @Delete("<script>" +
            "delete from resline_tableinfo where id in " +
            "(" +
            "<foreach collection='list' item='String' index='index' separator=','>" +
            " #{String}" +
            "</foreach>" +
            ")" +
            "</script>")
    Integer del(List<String> list);

    @Select("select a.table_id,a.number,a.phone from resline_info a,(\n" +
            "select max(createDate)datadate,table_id from resline_info where p_day = DATE_FORMAT(NOW(),'%Y%m%d') and state = '1' group by table_id ) b\n" +
            "where a.createDate = b.datadate and a.table_id = b.table_id")
    List<JSONObject> getalltablenumber();
}
