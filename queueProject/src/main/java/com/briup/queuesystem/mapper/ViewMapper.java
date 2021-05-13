package com.briup.queuesystem.mapper;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineInfoExtends;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ViewMapper {


    @Select("select state,count(1) count from resline_info  where p_day = DATE_FORMAT(NOW(),\"%Y%m%d\") group by state\n")
    List<JSONObject> getTodayData();


    @Select("select p_day,state,count(1) count from resline_info  group by state,p_day\n")
    List<JSONObject> getNowMonthData();


    @Select("<script>" +
            "select a.*,b.type from resline_info a,resline_category b where a.category_id = b.id " +
            "<if test = 'p_day != \"\"'>" +
            "and p_day = #{p_day}" +
            "</if>" +
            "order by createDate desc" +
            "</script>")
    List<ReslineInfoExtends> getAllReslineInfo(String p_day);



}
