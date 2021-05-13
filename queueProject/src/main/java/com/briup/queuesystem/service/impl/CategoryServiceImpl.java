package com.briup.queuesystem.service.impl;

import com.briup.queuesystem.bean.ReslineCategory;
import com.briup.queuesystem.mapper.CategoryMapper;
import com.briup.queuesystem.service.CategoryService;
import com.briup.queuesystem.utils.Message;
import com.briup.queuesystem.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public Message insert(ReslineCategory reslineCategory) {


        Integer insert = categoryMapper.insert(reslineCategory);
        if(insert > 0) return MessageUtil.success("success");
        else return MessageUtil.error("failed");
    }

    @Override
    public Message update(ReslineCategory reslineCategory) {
        Integer update = categoryMapper.update(reslineCategory);
        if(update > 0) return MessageUtil.success("success");
        else return MessageUtil.error("failed");
    }

    @Override
    public Message getAll() {
        return MessageUtil.success("查询成功",categoryMapper.getAll());
    }

    @Override
    public Message del(List<String> id) {
        Integer del = categoryMapper.del(id);
        if(del > 0) return MessageUtil.success("success");
        else return MessageUtil.error("failed");
    }
}
