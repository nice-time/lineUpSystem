package com.briup.queuesystem.bean;

import java.io.Serializable;

/**
 * @author lijx
 */
public class TestBean implements Serializable {

  private int id;
  private String name;
  private int age;
  sexEnum sex;
  private String CREATE_TIME;
  private String MODIFY_TIME;

  /**
   * 1:男性 2：女性
   */
  private enum sexEnum {male, female}

  public TestBean(int id, String name, int age, sexEnum sex, String CREATE_TIME,
      String MODIFY_TIME) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.sex = sex;
    this.CREATE_TIME = CREATE_TIME;
    this.MODIFY_TIME = MODIFY_TIME;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public sexEnum getSex() {
    return sex;
  }

  public void setSex(sexEnum sex) {
    this.sex = sex;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCREATE_TIME() {
    return CREATE_TIME;
  }

  public void setCREATE_TIME(String CREATE_TIME) {
    this.CREATE_TIME = CREATE_TIME;
  }

  public String getMODIFY_TIME() {
    return MODIFY_TIME;
  }

  public void setMODIFY_TIME(String MODIFY_TIME) {
    this.MODIFY_TIME = MODIFY_TIME;
  }

  @Override
  public String toString() {
    return "TestBean{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", sex=" + sex +
        ", CREATE_TIME='" + CREATE_TIME + '\'' +
        ", MODIFY_TIME='" + MODIFY_TIME + '\'' +
        '}';
  }

  private static final long serialVersionUID = 1L;

}
