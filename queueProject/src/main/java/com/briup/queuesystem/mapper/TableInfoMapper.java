//package com.briup.queuesystem.mapper;
//
//import com.briup.queuesystem.bean.ReslineAnnouncement;
//import com.briup.queuesystem.bean.ReslineTableInfo;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Mapper
//@Repository
//public interface TableInfoMapper {
//
//
//    @Insert("insert into resline_tableinfo values(#{Info.id},#{Info.number}," +
//            "#{Info.state},#{Info.category_id},sysdate,sysdate)")
//    Integer insert(ReslineTableInfo Info);
//
//    @Update("update resline_tableinfo set #{}  where id = #{}")
//    Integer update(ReslineTableInfo Info);
//
//    @Select("select * from resline_tableinfo order by lastupdate desc")
//    List<ReslineTableInfo> getAll();
//
//    @Delete("<script>" +
//            "delete from resline_tableinfo where id in " +
//            "(" +
//            "<foreach collection='list' item='String' index='index' separator=','>" +
//            " #{String}" +
//            "</foreach>" +
//            ")" +
//            "</script>")
//    Integer del(List<String> list);
//}
