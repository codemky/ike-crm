package com.ike.service;

import com.ike.pojo.User;
import com.ike.pojo.vo.StatisticsVo;
import com.ike.pojo.vo.UserVo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserVo> selectAll();

    public int insert(User user);

    public int update(User user) ;

    public int deleteById(Long id);

    public UserVo selectById(Long id);

    Optional<User> findUserByName(String password);

    User findById(Long id);

    boolean updatePassword(User user);

    public Optional<List<User>> findUserLikeName(String userName);

    public Optional<User> findUserByEmail(String eamil);

    public void importExcel(List<UserVo> userVos) throws Exception;

    public List<UserVo> getExportList();
}
