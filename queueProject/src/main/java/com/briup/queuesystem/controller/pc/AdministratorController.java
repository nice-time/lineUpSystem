package com.briup.queuesystem.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineAnnouncement;
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
import java.util.Arrays;
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

    @RequestMapping("/saveOrUpdateUser")
    public Message addNewUser(@RequestBody JSONObject jsonObject){
        try{

            ReslineUser reslineUser = new ReslineUser();

            reslineUser.setUsername(jsonObject.getString("userName"));
            reslineUser.setLevel(jsonObject.getString("level"));
            reslineUser.setPhone(jsonObject.getString("phone"));
            reslineUser.setSex(jsonObject.getString("sex"));
            Integer integer = 0;
            if (StringUtils.isEmpty(jsonObject.getString("id"))){
                //  工号 和 密码 必传
                String userNumber = jsonObject.getString("userNumber");
                String pwd = jsonObject.getString("pwd");
                reslineUser.setUsernumber(userNumber);
                reslineUser.setPwd(pwd);
                if (StringUtils.isEmpty(userNumber) || StringUtils.isEmpty(pwd)){
                    return MessageUtil.error("工号 或 密码 不能为空");
                }
                 integer = userLoginService.addNewUser(reslineUser);
                if (integer == 1 ){
                    return MessageUtil.success("添加成功",reslineUser);
                }else {
                    return MessageUtil.error("添加失败" + integer);
                }
            }else {
                reslineUser.setId(jsonObject.getString("id"));
                Integer integer1 = userLoginService.updateUser(reslineUser);
                if (integer1 == 1 ){
                    return MessageUtil.success("更新成功",reslineUser);
                }else {
                    return MessageUtil.error("更新失败" + integer);
                }            }
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error("新增或更新失败：" + e.toString());
        }
    }

    @RequestMapping("/delUser")
    public Message delUser(@RequestBody JSONObject jsonObject){
        try{
            String idStr = jsonObject.getString("idList");
            if (StringUtils.isEmpty(idStr)) {
                return MessageUtil.error("请选择要删除的用户，idStr=" + idStr);
            }
            List<String> idList = Arrays.asList(idStr.split(","));
            Integer integer = userLoginService.delUser(idList);
            return MessageUtil.success("删除成功：" + integer);
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error("新增或更新失败：" + e.toString());
        }
    }

    @RequestMapping("/findAllComment")
    public Message findAllComment(){
        try{
            List<ReslineAnnouncement> allComment = userLoginService.findAllComment();
            return MessageUtil.success("查询成功",allComment);
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error("查询失败：" + e.toString());
        }
    }

    @RequestMapping("/delComment")
    public Message delComment(@RequestBody JSONObject jsonObject){
        try{
            String idStr = jsonObject.getString("idList");
            if (StringUtils.isEmpty(idStr)) {
                return MessageUtil.error("请选择要删除的评论，idStr=" + idStr);
            }
            List<String> idList = Arrays.asList(idStr.split(","));
            Integer integer = userLoginService.delComment(idList);
            return MessageUtil.success("删除成功：" + integer);
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error("新增或更新失败：" + e.toString());
        }
    }



}
