package com.briup.queuesystem.mapper;

import com.briup.queuesystem.bean.TestBean;

/**
 * @author lijx
 */

public interface TestMapper {


  int deleteByPrimaryKey(Integer id);

  int insert(TestBean record);

  int insertSelective(TestBean record);

  TestBean selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TestBean record);

  int updateByPrimaryKey(TestBean record);

}
