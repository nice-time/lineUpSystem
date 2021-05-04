package com.briup.queuesystem.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineUser;
import com.briup.queuesystem.service.UserLoginService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: WithAnOrchid
 * @Date: 2021/5/4 15:52
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    @Resource
    UserLoginService userLoginService;

    @RequestMapping("/findAllUser")
    public Message findAllUser(){
        try{
            List<ReslineUser> allUser = userLoginService.findAllUser();
            return MessageUtil.success("查询成功",allUser);
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error("查询失败：" + e.toString());
        }
    }

    @RequestMapping("/addNewUser")
    public Message addNewUser(@RequestBody JSONObject jsonObject){
        try{
            //  工号 和 密码 必传
            String userNumber = jsonObject.getString("userNumber");
            String pwd = jsonObject.getString("pwd");
            if (StringUtils.isEmpty(userNumber) || StringUtils.isEmpty(pwd)){
                return MessageUtil.error("工号 或 密码 不能为空");
            }
            ReslineUser reslineUser = new ReslineUser();
            reslineUser.setUsernumber(userNumber);
            reslineUser.setPwd(pwd);
            reslineUser.setUsername(jsonObject.getString("userName"));
            reslineUser.setUsername(jsonObject.getString("level"));
            reslineUser.setUsername(jsonObject.getString("phone"));
            reslineUser.setUsername(jsonObject.getString("sex"));
            Integer integer = 0;
            if (StringUtils.isEmpty(jsonObject.getString("id"))){
                 integer = userLoginService.addNewUser(reslineUser);
                if (integer == 1 ){
                    return MessageUtil.success("添加成功",reslineUser);
                }else {
                    return MessageUtil.error("添加失败" + integer);
                }
            }else {
                reslineUser.setId(jsonObject.getString("id"));
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error("新增失败：" + e.toString());
        }
    }
}
