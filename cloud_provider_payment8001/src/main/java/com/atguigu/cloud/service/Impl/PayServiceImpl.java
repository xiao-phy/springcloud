package com.atguigu.cloud.service.Impl;

import cn.hutool.core.date.DateTime;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.mapper.PayMapper;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PayServiceImpl implements PayService{

    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay){
        if(pay.getDeleted() == null){
            pay.setDeleted((byte) 0);
        }
        if(pay.getCreateTime() == null || pay.getUpdateTime() == null){
            pay.setCreateTime(new DateTime());
            pay.setUpdateTime(new DateTime());
        }
        return payMapper.insertById(pay);
    }
    @Override
    public int delete(Integer id){
        return payMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int update(Pay pay){
        return payMapper.updateByPrimaryKeySelective(pay);
    }
    @Override
    public Pay getById(Integer id) {
        return payMapper.selectById(id);

    }
    @Override
    public List<Pay> getAll(){
        return payMapper.selectAll();
    }
}
