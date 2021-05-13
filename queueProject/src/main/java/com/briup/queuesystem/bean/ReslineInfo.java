package com.briup.queuesystem.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * resline_info
 *
 * @author
 */
@Data
public class ReslineInfo implements Serializable {

  //  用户手机号
  private String phone;
  //  就餐人数
  private String people;
  //  就餐状态 --0排队中、1正就餐、2已结账、3作废
  private String state;
  //  排队号码
  private String number;
  //  区域类型
  private String description;
  //  桌型ID -- 1-A 2-B
  private Integer categoryId;
  //  创建时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
  private Date createdate;
  //  就餐时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
  private Date eatdate;
  //  结束时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
  private Date enddate;
  //  这是干啥的 不懂？
  private String pDay;
  //  桌号
  private String tableId;
  //
  private static final long serialVersionUID = 1L;
}