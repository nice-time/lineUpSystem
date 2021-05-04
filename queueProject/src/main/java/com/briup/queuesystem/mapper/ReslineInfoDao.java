package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.bean.ReslineUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReslineInfoDao {

  int insert(ReslineInfo record);

  int insertSelective(ReslineInfo record);

  List<ReslineInfo> searchUserInfoByPhoneNumber(String phoneNumbber);

  ReslineInfo searchWaitUserInfoByPhoneNumber(String phoneNumber);

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
}