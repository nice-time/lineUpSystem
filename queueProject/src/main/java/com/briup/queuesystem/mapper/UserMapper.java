package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {


  @Insert("insert into resline_user values(#{Info.id},#{Info.username}," +
          "#{Info.usernumber},#{Info.pwd},#{Info.level},#{Info.phone}," +
          "#{Info.sex},createDate=NOW(),lastupdate=NOW())")
  Integer insert(@Param("Info") ReslineUser Info);

  @Update("update resline_user set username=#{Info.username},usernumber=#{Info.usernumber}," +
          "pwd=#{Info.pwd},level=#{Info.level},phone=#{Info.phone}," +
          "sex=#{Info.sex},lastupdate=NOW() where id = #{Info.id} ")
  Integer update(@Param("Info") ReslineUser Info);

  @Select("select * from resline_user order by lastupdate desc")
  List<ReslineUser> getAll();

  @Delete("<script>" +
          "delete from resline_user where id in " +
          "(" +
          "<foreach collection='list' item='String' index='index' separator=','>" +
          " #{String}" +
          "</foreach>" +
          ")" +
          "</script>")
  Integer del(List<String> list);

  @Select("select * from resline_user where usernumber = #{userNumber} and pwd = #{password}")
  ReslineUser userLogin(String userNumber, String password);

  @Select("select * from resline_user")
  List<ReslineUser> findAllUser();
}
