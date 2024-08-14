package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Pay;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface PayMapper extends Mapper<Pay> {
    Pay selectById(Integer id);

    int insertById(Pay pay);

}