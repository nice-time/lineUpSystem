package com.briup.queuesystem.controller.pc;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineInfo;
import com.briup.queuesystem.service.UserInfoService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 结账管理
 *
 * @Author: ljx
 * @Date: 2021/5/4 14:30
 */
@CrossOrigin
@RestController
@RequestMapping("/bill")
public class BillController {

    @Resource
    UserInfoService userInfoService;

    @RequestMapping("/billModel")
    public Message billModel(@RequestBody JSONObject jsonObject) {
        try {
            //  结账时的手机号码
            String phone = jsonObject.getString("phone");
            //  结账时的号码
            String number = jsonObject.getString("number");
            ReslineInfo reslineInfo = new ReslineInfo();
            reslineInfo.setPhone(phone);
            reslineInfo.setNumber(number);
            Integer integer = userInfoService.billUpdate(reslineInfo);
            if (integer == 1) {
                return MessageUtil.success("结账成功", integer);
            } else {
                return MessageUtil.error("该笔账单已结算或结账异常，请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("出现异常：" + e.toString());
        }
    }

    /**
     * 确认就餐接口
     * @param jsonObject
     * @return
     */
    @RequestMapping("/confirmMeal")
    public Message confirmMeal(@RequestBody JSONObject jsonObject) {
        try {
            //  就餐时的手机号码
            String phone = jsonObject.getString("phone");
            //  就餐时出示叫号号码
            String number = jsonObject.getString("number");
            ReslineInfo reslineInfo = new ReslineInfo();
            reslineInfo.setPhone(phone);
            reslineInfo.setNumber(number);
            Integer integer = userInfoService.confirmMeal(reslineInfo);
            if (integer == 1) {
                return MessageUtil.success("就餐成功", integer);
            } else {
                return MessageUtil.error("确认就餐失败，请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("出现异常：" + e.toString());
        }
    }

    /**
     * 取消（超时）就餐接口
     * @param jsonObject
     * @return
     */
    @RequestMapping("/cancelMeal")
    public Message cancelMeal(@RequestBody JSONObject jsonObject) {
        try {
            //  就餐时的手机号码
            String phone = jsonObject.getString("phone");
            ReslineInfo reslineInfo = new ReslineInfo();
            reslineInfo.setPhone(phone);
            Integer integer = userInfoService.cancelMeal(reslineInfo);
            if (integer == 1) {
                return MessageUtil.success("作废成功", integer);
            } else {
                return MessageUtil.error("号码作废失败，请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error("出现异常：" + e.toString());
        }
    }

}