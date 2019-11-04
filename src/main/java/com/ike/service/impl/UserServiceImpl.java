package com.ike.service.impl;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.mapper.UserMapper;
import com.ike.mapper.ext.StatisticsMapper;
import com.ike.mapper.ext.UserExtMapper;
import com.ike.pojo.User;
import com.ike.pojo.UserExample;
import com.ike.pojo.vo.StatisticsVo;
import com.ike.pojo.vo.UserVo;
import com.ike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserExtMapper userExtMapper;

    @Override
    public List<UserVo> selectAll() {
        return userExtMapper.selectAll();
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserVo selectById(Long id) {
        return userExtMapper.selectById(id);
    }

    @Override
    public Optional<User> findUserByName(String userName) {
        //后期改为多表查询
        UserExample example = new UserExample();
        example.or().andUsernameEqualTo(userName);
        List<User> users = userMapper.selectByExample(example);
        User user = users.get(0);
        return Optional.ofNullable(user);
    }

    @Override
    public User findById(Long id) {
        UserExample example = new UserExample();
        example.or().andIdEqualTo(id);
        List<User> users = userMapper.selectByExample(example);
        return users.get(0);
    }

    @Override
    public boolean updatePassword(User user) {
        int index = userMapper.updateByPrimaryKeySelective(user);
        return index > 0 ? true : false;
    }

    @Override
    @Transactional
    public void importExcel(List<UserVo> userVos) throws Exception {
        try {
            userExtMapper.importExcel(userVos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Excel导入异常,请稍后重试!");
        }
    }

    @Override
    public List<UserVo> getExportList() {
        return userExtMapper.getExportList();
    }

    @Override
    public Optional<List<User>> findUserLikeName(String userName) {
        UserExample example = new UserExample();
        example.or().andNameLike("%" + userName + "%");
        List<User> users = userMapper.selectByExample(example);
        return Optional.ofNullable(users);
    }

    @Override
    public Optional<User> findUserByEmail(String eamil) {
        UserExample example = new UserExample();
        example.or().andEmailEqualTo(eamil);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(0));
    }
}
