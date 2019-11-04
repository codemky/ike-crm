package com.ike.service.impl;

import com.ike.pojo.Test;
import com.ike.mapper.TestMapper;
import com.ike.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ike
 * @since 2019-09-30
 */

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Override
    public List<Test> getTestList() {
        return baseMapper.selectList(null);
    }
}
