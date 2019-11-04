package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.result.Result;
import com.ike.service.CustomerStageLogService;
import com.ike.pojo.vo.CustomerStageLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName CustomerStageLogController
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 21:06
 **/
@Api(description = "客户阶段修改日志模块")
@RestController
@RequestMapping("/customerStageLog")
public class CustomerStageLogController {

    @Autowired
    private CustomerStageLogService stageLogService;

    /**
     * Author: mokuanyuan
     * @param cid
     * @apiNote: 根据客户id获取客户的阶段修改日志
     * @since:  2019年10月9日 21点13分
     */
    @ApiOperation(value = "根据客户id获取客户的阶段修改日志列表", notes = "已测试通过 2019年10月10日 21点33分")
    @GetMapping("/listByCid")
    public Result listByCustomerId(@RequestParam("Cid") Long cid,
                       @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){

        Page<CustomerStageLogVo> paramPage = new Page<>(pageNum, pageSize);
        IPage<CustomerStageLogVo> stageLogVoPage = stageLogService.selectStageLogByCustomerId(paramPage, cid);

        return Result.success(stageLogVoPage);

    }

}
