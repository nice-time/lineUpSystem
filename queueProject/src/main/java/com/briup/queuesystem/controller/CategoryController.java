package com.briup.queuesystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Category")
public class CategoryController {

    @RequestMapping("getAllCateory")
    public Message getAllCateory(){




        return MessageUtil.success("成功",null);

    }

}
