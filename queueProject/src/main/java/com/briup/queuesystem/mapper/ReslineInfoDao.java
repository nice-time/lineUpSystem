package com.briup.queuesystem.mapper;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.bean.ReslineUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.net.Inet4Address;
import java.util.List;

@Repository
@Mapper
public interface ReslineInfoDao {

  int insert(ReslineInfo record);

  int insertSelective(ReslineInfo record);

  List<ReslineInfo> searchUserInfoByPhoneNumber(String phoneNumbber);


  ReslineInfo searchWaitUserInfoByPhoneNumber(String phoneNumber);

  @Select("SELECT\n" +
          "\tcount(a.number)\n" +
          "FROM\n" +
          "\tresline_info a,\n" +
          "\t(\n" +
          "\t\tSELECT\n" +
          "\t\t\tmax(createDate) datadate,\n" +
          "\t\t\tnumber,\n" +
          "\t\t\tcategory_id\n" +
          "\t\tFROM\n" +
          "\t\t\tresline_info\n" +
          "\t\tWHERE\n" +
          "\t\t\tp_day = date_format(\n" +
          "\t\t\t\tnow(),\n" +
          "\t\t\t\t'%Y%m%d'\n" +
          "\t\t\t)\n" +
          "\t\tAND phone = #{phoneNumber}\n" +
          "\t\tAND state = 0\n" +
          "\t\tGROUP BY\n" +
          "\t\t\tnumber,\n" +
          "\t\t\tcategory_id\n" +
          "\t) b\n" +
          "WHERE\n" +
          "\tp_day = date_format(\n" +
          "\t\tnow(),\n" +
          "\t\t'%Y%m%d'\n" +
          "\t)\n" +
          "AND a.createDate < b.datadate\n" +
          "AND a.state = 0\n" +
          "AND a.category_id = b.category_id")
  int selectBeforePeopleNum(String phoneNumber);

  List<ReslineInfo> getSmallWaitUserInfo();

  List<ReslineInfo> getBigWaitUserInfo();

  int updateUserInfo(ReslineInfo reslineUse);

  int billUpdate(ReslineInfo reslineInfo);

  int confirmMeal(ReslineInfo reslineInfo);

  int cancelMeal(ReslineInfo reslineInfo);

  @Update("UPDATE resline_info\n" +
          "SET people = #{people}\n" +
          "WHERE\n" +
          "\tphone = #{phone}\n" +
          "AND createDate = (\n" +
          "\tSELECT\n" +
          "\t\tt.time\n" +
          "\tFROM\n" +
          "\t\t(\n" +
          "\t\t\tSELECT\n" +
          "\t\t\t\tmax(createdate) time\n" +
          "\t\t\tFROM\n" +
          "\t\t\t\tresline_info\n" +
          "\t\t\tWHERE\n" +
          "\t\t\t\tphone = #{phone}\n" +
          "\t\t) t\n" +
          ")")
  Integer updatePeople(String phone, String people);

  @Select("select d.*,c.id type_id,c.type from \n" +
          "\tresline_category c LEFT JOIN  (SELECT\n" +
          "\ta.number,\n" +
          "a.category_id,\n" +
          "\tDATE_FORMAT(a.createDate,'%Y-%m-%d %H:%i:%s') createDate,\n" +
          "a.phone\n" +
          "FROM\n" +
          "\tresline_info a,\n" +
          "\t(\n" +
          "\t\tSELECT\n" +
          "\t\t\tmin(createDate) datadate,\n" +
          "\t\t\tcategory_id\n" +
          "\t\tFROM\n" +
          "\t\t\tresline_info\n" +
          "\t\tWHERE\n" +
          "\t\t\tstate = 0\n" +
          "\t\tAND p_day = date_format(\n" +
          "\t\t\tNOW(),\n" +
          "\t\t\t'%Y%m%d'\n" +
          "\t\t)\n" +
          "\t\tGROUP BY\n" +
          "\t\t\tcategory_id\n" +
          "\t) b\n" +
          "WHERE\n" +
          "\ta.createDate = b.datadate) d \n" +
          "on d.category_id = c.id")
  List<JSONObject> getMinnumber();

  @Select("SELECT\n" +
          "\tCount(1)\n" +
          "FROM\n" +
          "\tresline_info a,\n" +
          "\tresline_category b\n" +
          "WHERE\n" +
          "\tp_day = DATE_FORMAT(NOW(), \"%Y%m%d\")\n" +
          "AND state = 0\n" +
          "AND a.category_id = b.id\n" +
          "AND b.type = #{type}")
  Integer getcount(String type);

  @Update("update resline_tableinfo set state = #{state} where id = #{table_id} ")
  Integer updatetable(String state,String table_id);
}