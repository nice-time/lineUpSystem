package com.briup.queuesystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.briup.queuesystem.bean.ReslineCategoryExtends;
import com.briup.queuesystem.bean.ReslineTableInfo;
import com.briup.queuesystem.mapper.TableInfoMapper;
import com.briup.queuesystem.service.TableInfoService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: WithAnOrchid
 * @Date: 2021/5/4 21:20
 */
@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Resource
    TableInfoMapper tableInfoMapper;

    @Override
    public Integer insert(ReslineTableInfo reslineTableInfo) {
        return tableInfoMapper.insert(reslineTableInfo);
    }

    @Override
    public Integer update(ReslineTableInfo reslineTableInfo) {
        return tableInfoMapper.update(reslineTableInfo);
    }

    @Override
    public List<ReslineCategoryExtends> getAll() {
        List<JSONObject> getalltablenumber = tableInfoMapper.getalltablenumber();
        List<ReslineCategoryExtends> all = tableInfoMapper.getAll();
        all.stream().forEach(v->{
            for(JSONObject item : getalltablenumber){
                if(v.getId().equals(item.getString("table_id"))){
                    v.setResnumber(item.getString("number"));
                    v.setPhone(item.getString("phone"));
                }
            }
        });
        return all;
    }

    @Override
    public Integer del(List<String> id) {
        return tableInfoMapper.del(id);
    }
}
