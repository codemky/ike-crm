package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.Complaint;
import com.ike.pojo.ComplaintHandle;
import com.ike.pojo.User;
import com.ike.pojo.vo.ComplaintHandleVo;
import com.ike.pojo.vo.ComplaintVo;
import com.ike.pojo.vo.PageInfo;
import com.ike.service.ComplaintHandleService;
import com.ike.service.ComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author wgm
 * @Date 2019/10/12 11:26
 */
@RestController
@Api(description = "客户投诉处理模块")
@RequestMapping("/complaintHandle")
public class ComplaintHandleController {

    @Autowired
    private ComplaintHandleService handleService;

    @ApiOperation(value = "根据投诉记录id查询全部投诉处理记录")
    @GetMapping("/list/{complaintId}")
    public Result list(@PathVariable Long complaintId) {
        List<ComplaintHandleVo> complaintHandleVos = handleService.selAllByComplaintId(complaintId);
        return Result.success(complaintHandleVos);
    }

    @ApiOperation(value = "处理投诉")
    @ApiImplicitParam(name = "complaintHandle", value = "投诉处理实体类")
    @PostMapping("/handleComplaint")
    public Result handleComplaint(User user, @RequestBody ComplaintHandle complaintHandle) {
        if ( user == null ) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        complaintHandle.setEmployeeId(user.getId());
        return handleService.handleComplaint(complaintHandle) > 0 ? Result.success(null) : Result.error();
    }
}
