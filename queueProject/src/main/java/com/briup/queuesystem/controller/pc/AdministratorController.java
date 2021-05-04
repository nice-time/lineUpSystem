package com.briup.queuesystem.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.*;
import com.briup.queuesystem.service.CategoryService;
import com.briup.queuesystem.service.TableInfoService;
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
    @Resource
    TableInfoService tableInfoService;
    @Resource
    CategoryService categoryService;

    @RequestMapping("/findAllUser")
    public Message findAllUser() {
        try {
            List<ReslineUser> allUser = userLoginService.findAllUser();
            return MessageUtil.success("查询成功", allUser);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("查询失败：" + e.toString());
        }
    }

    @RequestMapping("/saveOrUpdateUser")
    public Message addNewUser(@RequestBody JSONObject jsonObject) {
        try {

            ReslineUser reslineUser = new ReslineUser();

            reslineUser.setUsername(jsonObject.getString("userName"));
            reslineUser.setLevel(jsonObject.getString("level"));
            reslineUser.setPhone(jsonObject.getString("phone"));
            reslineUser.setSex(jsonObject.getString("sex"));
            Integer integer = 0;
            if (StringUtils.isEmpty(jsonObject.getString("id"))) {
                //  工号 和 密码 必传
                String userNumber = jsonObject.getString("userNumber");
                String pwd = jsonObject.getString("pwd");
                reslineUser.setUsernumber(userNumber);
                reslineUser.setPwd(pwd);
                if (StringUtils.isEmpty(userNumber) || StringUtils.isEmpty(pwd)) {
                    return MessageUtil.error("工号 或 密码 不能为空");
                }
                integer = userLoginService.addNewUser(reslineUser);
                if (integer == 1) {
                    return MessageUtil.success("添加成功", reslineUser);
                } else {
                    return MessageUtil.error("添加失败" + integer);
                }
            } else {
                reslineUser.setId(jsonObject.getString("id"));
                String userNumber = jsonObject.getString("userNumber");
                String pwd = jsonObject.getString("pwd");
                reslineUser.setUsernumber(userNumber);
                reslineUser.setPwd(pwd);
                Integer integer1 = userLoginService.updateUser(reslineUser);
                if (integer1 == 1) {
                    return MessageUtil.success("更新成功", reslineUser);
                } else {
                    return MessageUtil.error("更新失败" + integer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("新增或更新失败：" + e.toString());
        }
    }

    @RequestMapping("/delUser")
    public Message delUser(@RequestBody JSONObject jsonObject) {
        try {
            String idStr = jsonObject.getString("idList");
            if (StringUtils.isEmpty(idStr)) {
                return MessageUtil.error("请选择要删除的用户，idStr=" + idStr);
            }
            List<String> idList = Arrays.asList(idStr.split(","));
            Integer integer = userLoginService.delUser(idList);
            return MessageUtil.success("删除成功：" + integer);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("新增或更新失败：" + e.toString());
        }
    }

    @RequestMapping("/findAllComment")
    public Message findAllComment() {
        try {
            List<ReslineSuggestInfo> allComment = userLoginService.findAllComment();
            return MessageUtil.success("查询成功", allComment);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("查询失败：" + e.toString());
        }
    }

    @RequestMapping("/delComment")
    public Message delComment(@RequestBody JSONObject jsonObject) {
        try {
            String idStr = jsonObject.getString("idList");
            if (StringUtils.isEmpty(idStr)) {
                return MessageUtil.error("请选择要删除的评论，idStr=" + idStr);
            }
            List<String> idList = Arrays.asList(idStr.split(","));
            Integer integer = userLoginService.delComment(idList);
            return MessageUtil.success("删除成功：" + integer);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("新增或更新失败：" + e.toString());
        }
    }

    /**
     * 查询全部桌子详细信息
     * @return
     */
    @RequestMapping("/findAllTableInfo")
    public Message findAllTableInfo() {
        try {
            List<ReslineTableInfo> allComment = tableInfoService.getAll();
            return MessageUtil.success("查询成功", allComment);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("查询失败：" + e.toString());
        }
    }

    @RequestMapping("/saveOrUpdateTableInfo")
    public Message saveOrUpdateTableInfo(@RequestBody JSONObject jsonObject){
        try{
            ReslineTableInfo reslineTableInfo = new ReslineTableInfo();
            reslineTableInfo.setNumber(jsonObject.getString("number"));
            reslineTableInfo.setState(jsonObject.getString("state"));
            reslineTableInfo.setCategroy_id(jsonObject.getString("categoryId"));
            //  id 为空就是新增
            if (StringUtils.isEmpty(jsonObject.getString("id"))){
                Integer insert = tableInfoService.insert(reslineTableInfo);
                if (insert>0){
                    return MessageUtil.success("新增成功",reslineTableInfo);
                }else {
                    return MessageUtil.error("新增失败请联系管理员：" + insert);
                }
            }else {
                reslineTableInfo.setId(jsonObject.getString("id"));
                Integer update = tableInfoService.update(reslineTableInfo);
                if (update>0){
                    return MessageUtil.success("更新成功",reslineTableInfo);
                }else {
                    return MessageUtil.error("更新失败请联系管理员：" + update);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error("出现异常：" + e.toString());
        }
    }

    @RequestMapping("/delTableInfo")
    public Message delTableInfo(@RequestBody JSONObject jsonObject) {
        try {
            String idStr = jsonObject.getString("idList");
            if (StringUtils.isEmpty(idStr)) {
                return MessageUtil.error("请选择要删除信息，idStr=" + idStr);
            }
            List<String> idList = Arrays.asList(idStr.split(","));
            Integer integer = tableInfoService.del(idList);
            return MessageUtil.success("删除成功：" + integer);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("新增或更新失败：" + e.toString());
        }
    }



    /**
     * 查询全部桌子详细信息
     * @return
     */
    @RequestMapping("/findAllCategory")
    public Message findAllCategory() {
        try {
            return categoryService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("查询失败：" + e.toString());
        }
    }

    @RequestMapping("/saveOrUpdateCategory")
    public Message saveOrUpdateCategory(@RequestBody JSONObject jsonObject){
        try{
            ReslineCategory reslineCategory = new ReslineCategory();
            reslineCategory.setType(jsonObject.getString("type"));
            reslineCategory.setDesc1(jsonObject.getString("desc"));
            //  id 为空就是新增
            if (StringUtils.isEmpty(jsonObject.getString("id"))){
                return categoryService.insert(reslineCategory);
            }else {
                reslineCategory.setId(jsonObject.getString("id"));
                return categoryService.update(reslineCategory);
            }
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error("出现异常：" + e.toString());
        }
    }

    @RequestMapping("/delCategory")
    public Message delCategory(@RequestBody JSONObject jsonObject) {
        try {
            String idStr = jsonObject.getString("idList");
            if (StringUtils.isEmpty(idStr)) {
                return MessageUtil.error("请选择要删除信息，idStr=" + idStr);
            }
            List<String> idList = Arrays.asList(idStr.split(","));
            return categoryService.del(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("新增或更新失败：" + e.toString());
        }
    }


}
