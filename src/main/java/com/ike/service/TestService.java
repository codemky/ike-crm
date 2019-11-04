package com.ike.service;

import com.ike.pojo.Test;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ike
 * @since 2019-09-30
 */
public interface TestService extends IService<Test> {

    List<Test> getTestList();
}
