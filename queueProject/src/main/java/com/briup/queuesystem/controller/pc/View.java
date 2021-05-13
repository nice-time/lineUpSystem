package com.briup.queuesystem.controller.pc;

import ch.qos.logback.classic.net.SocketNode;
import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.mapper.ViewMapper;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/view")
public class View {

    @Resource
    ViewMapper viewMapper;

    static SimpleDateFormat sdf_S = new SimpleDateFormat("yyyyMMdd");



    @RequestMapping("getTodayData")
    public Message getTodayData(){
        List<JSONObject> todayData = viewMapper.getTodayData();

        return MessageUtil.success(todayData);

    }

    @RequestMapping("/getNowMonthData")
    public Message getNowMonthData(){
        List<JSONObject> nowMonthData = viewMapper.getNowMonthData();
        List<String> allDay = getAllDay(new Date(), false, false, "");
        List<Integer> dataY0 = new ArrayList<>();
        List<Integer> dataY1 = new ArrayList<>();
        List<Integer> dataY2 = new ArrayList<>();
        List<Integer> dataY3 = new ArrayList<>();

        for(String v:allDay){
            List<String> strings = new ArrayList<>();
            for(JSONObject item : nowMonthData){
                if(null != item.getString("p_day") && v.equals(item.getString("p_day"))){
                    if(item.getString("state").equals("0")){
                        dataY0.add(item.getInteger("count"));
                        strings.add("0");
                    }else if("1".equals(item.getString("state"))){
                        dataY1.add(item.getInteger("count"));
                        strings.add("1");
                    }else if("2".equals(item.getString("state"))){
                        dataY2.add(item.getInteger("count"));
                        strings.add("2");
                    }else if("3".equals(item.getString("state"))){
                        dataY3.add(item.getInteger("count"));
                        strings.add("3");
                    }

                }
            }
            if(!strings.contains("0")) dataY0.add(0);
            if(!strings.contains("1")) dataY1.add(0);
            if(!strings.contains("2")) dataY2.add(0);
            if(!strings.contains("3")) dataY3.add(0);
        }
//        System.out.println(dataY0);
//        System.out.println(dataY1);
//        System.out.println(dataY2);
//        System.out.println(dataY3);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataX",allDay);
        jsonObject.put("dataY0",dataY0);
        jsonObject.put("dataY1",dataY1);
        jsonObject.put("dataY2",dataY2);
        jsonObject.put("dataY3",dataY3);


        return MessageUtil.success(jsonObject);
    }


    @RequestMapping("getAllReslineInfo")
    public Message getAllReslineInfo(String p_day){

        p_day = null != p_day ? p_day :"";
        return MessageUtil.success(viewMapper.getAllReslineInfo(p_day));
    }



    private static List<String> getAllDay(Date month, boolean isMonth, boolean lengthFlag, String type) {
        SimpleDateFormat sdf = null;
        List<String> list = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);
        if (isMonth) {
            sdf = sdf_S;
            int maxDate = cal.getActualMaximum(Calendar.MONTH) + 1;
            if (!lengthFlag) {
                maxDate = cal.get(Calendar.MONTH);
            }
            cal.set(Calendar.DAY_OF_YEAR, 1);

            cal.set(Calendar.MONTH, 1);
            for (int i = 0; i < maxDate; i++, cal.add(Calendar.MONTH, 1)) {
                String date = "";
                if ("1".equals(type)) {

                    Calendar instance = Calendar.getInstance();
                    instance.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                    instance.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));

                    Date d = instance.getTime();
                    date = sdf.format(d);
                } else{

                    date = sdf.format(cal.getTime());
//                }else {
////                    cal.set(Calendar.DAY_OF_MONTH, 1);
//                    date = sdf.format(cal.getTime());
                }
                list.add(date);
//                cal.set(Calendar.MONTH,1);
            }
        } else {
            sdf = sdf_S;

            int maxDate = cal.getActualMaximum(Calendar.DATE);
            if (!lengthFlag) {
                maxDate = cal.get(Calendar.DAY_OF_MONTH);
            }
            cal.set(Calendar.DAY_OF_MONTH, 1);

            for (int i = 0; i < maxDate; i++, cal.add(Calendar.DATE, 1)) {
                Date d = cal.getTime();
                String date = sdf.format(d);
                list.add(date);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        List<String> allDay = getAllDay(new Date(), false, false, "");
        System.out.println(allDay);
    }

}
