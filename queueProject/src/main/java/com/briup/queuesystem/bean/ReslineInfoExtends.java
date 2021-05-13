package com.briup.queuesystem.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReslineInfoExtends implements Serializable {

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
        private String createdate;
        //  就餐时间
        private String eatdate;
        //  结束时间
        private String enddate;
        //  这是干啥的 不懂？
        private String pDay;
        //  桌号
        private String tableId;

        private String type;

}
