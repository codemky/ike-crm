package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.Complaint;
import com.ike.pojo.User;
import com.ike.pojo.vo.ComplaintSearchVo;
import com.ike.pojo.vo.ComplaintVo;
import com.ike.pojo.vo.MessageVo;
import com.ike.pojo.vo.PageInfo;
import com.ike.service.ComplaintService;
import com.ike.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author wgm
 * @Date 2019/10/10 11:26
 */
@RestController
@Api(description = "客户投诉模块")
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @ApiOperation(value = "多条件查询投诉记录")
    @ApiImplicitParam(name = "complaintSearchVo", value = "客户投诉筛选实体类")
    @PostMapping("advanceSearch")
    public Result advanceSearch(@RequestBody ComplaintSearchVo complaintSearchVo,
                                @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Page<ComplaintVo> page = new Page<>(pageNum, pageSize);
        IPage<ComplaintVo> complaintList = complaintService.advanceSearch(page, complaintSearchVo);
        return Result.success(complaintList);
    }

    @ApiOperation(value = "查询全部投诉记录")
    @GetMapping("listAll")
    public Result listAll(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Page<ComplaintVo> page = new Page<>(pageNum, pageSize);
        IPage<ComplaintVo> complaintList = complaintService.selAll(page);
        return Result.success(complaintList);
    }

    @ApiOperation(value = "查询未处理投诉记录")
    @GetMapping("listUnHandle")
    public Result listUnHandle(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Page<ComplaintVo> page = new Page<>(pageNum, pageSize);
        IPage<ComplaintVo> complaintList = complaintService.selUnHandle(page);
        return Result.success(complaintList);
    }

    @ApiOperation(value = "查询处理过的投诉记录")
    @GetMapping("listIsHandle")
    public Result listIsHandle(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Page<ComplaintVo> page = new Page<>(pageNum, pageSize);
        IPage<ComplaintVo> complaintList = complaintService.selIsHandled(page);
        return Result.success(complaintList);
    }

    @ApiOperation(value = "查询当前用户负责的投诉记录")
    @GetMapping("listCurHandle")
    public Result HandleByCurUser(User user,
                          @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if ( user == null ) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        Page<ComplaintVo> page = new Page<>(pageNum, pageSize);
        IPage<ComplaintVo> complaintList = complaintService.selHandleByCurUser(page, user.getId());
        return Result.success(complaintList);
    }

    @ApiOperation(value = "根据id删除记录")
    @DeleteMapping("delete")
    public Result delete(@RequestParam(value = "id") Long id) {
        return complaintService.deleteById(id) > 0 ? Result.success(null) : Result.error();
    }

    @ApiOperation(value = "根据id批量删除记录")
    @DeleteMapping("batchDelete")
    public Result batchDelete(@RequestBody List<Long> ids) {
        return complaintService.batchDelete(ids) > 0 ? Result.success(null) : Result.error();
    }

    @ApiOperation(value = "新建投诉记录")
    @PostMapping("insert")
    public Result insertComplaint(@RequestBody Complaint complaint) {
        return complaintService.insertComplaint(complaint) > 0 ? Result.success(null) : Result.error();
    }

    @ApiOperation(value = "修改投诉记录")
    @ApiImplicitParam(name = "complaint", value = "投诉实体类")
    @PutMapping("update")
    public Result updateComplaint(@RequestBody Complaint complaint) {
        return complaintService.updateComplaint(complaint) > 0 ? Result.success(null) : Result.error();
    }
}
