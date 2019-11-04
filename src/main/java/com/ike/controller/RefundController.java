package com.ike.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.common.util.Converter;
import com.ike.common.util.DownloadUtil;
import com.ike.common.util.ExcelUtil;
import com.ike.common.util.ImportTestUtil;
import com.ike.mapper.ext.RefundExportExtMapper;
import com.ike.pojo.*;
import com.ike.pojo.vo.*;
import com.ike.service.OrderBaseService;
import com.ike.service.RefundService;
import com.ike.service.RelationService;
import com.ike.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Api(description = "退款模块")
@RequestMapping("/json/refund/")
public class RefundController {

    @Autowired
    RefundService refundService;

    @Autowired
    OrderBaseService orderBaseService;

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    RefundExportExtMapper refundExportExtMapper;

    @Autowired
    RelationService relationService;

    private static void updateAction(Refund refund, User user) {
        refund.setEmployeeId(user.getId());
        refund.setModifyUserId(user.getId());
        refund.setModifyTime(LocalDateTime.now());
    }

    private static void createAction(Refund refund, User user) {
        refund.setEmployeeId(user.getId());
        refund.setCreateUserId(user.getId());
        refund.setCreateTime(LocalDateTime.now());
        refund.setModifyUserId(user.getId());
        refund.setModifyTime(LocalDateTime.now());
    }

    @ApiOperation(value = "查询全部退款，以及客户联系人对应的客户名称", notes = "已测")
    @GetMapping("list")
    public Result list() {
        List<RefundVo> refundList = refundService.selectAllList();
        return refundList == null ?
                Result.error(CodeMsg.ERROR) : Result.success(refundList);
    }

    @ApiOperation(value = "分页查询全部退款，以及客户联系人对应的客户名称", notes = "已测")
    @GetMapping("listAll")
    public Result listAll(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<RefundVo> pageInfo = new PageInfo<>(refundService.selectAllList());
        map.put("pageInfo", pageInfo);
        map.put("msgList", pageInfo.getList());
        return Result.success(map);
    }

    @ApiOperation(value = "查询当前用户负责的退款列表，以及客户联系人对应的客户名称", notes = "已测")
    @GetMapping("listAllByUser")
    public Result listAllByUser(
            @RequestParam(value = "employeeId", required = false) Long employeeId,
                                @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<RefundVo> pageInfo = new PageInfo<>(refundService.selectByUserid(employeeId));
        map.put("pageInfo", pageInfo);
        map.put("msgList", pageInfo.getList());
        return Result.success(map);
    }

    @ApiOperation(value = "添加退款记录,并将改退款记录对应的订单状态改为退款成功(4)", notes = "已测")
    @PostMapping("create")
    public Result create(User user,
                         RefundVo refundVo) {
        Refund refund = new Refund();
        BeanUtils.copyProperties(refundVo, refund);
        LocalDateTime localDateTime = ImportTestUtil.parseStringLocalDateTime(refundVo.getRefundDateStr());
        refund.setRefundDate(localDateTime);
        createAction(refund, user);
        //把对应的订单的状态变为退款成功 4
        orderBaseService.updateStateById(refund.getOrderBaseId());
        return refundService.insert(refund) > 0 ?
                Result.success(null) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "删除退款记录,并修改订单状态为0(未付款)", notes = "已测")
    @DeleteMapping(value = "delete")
    public Result delete(
            @RequestParam(value = "id", required = false) Long id) {
        return refundService.deleteById(id) > 0 ?
                Result.success(null) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "修改退款记录", notes = "已测")
    @PutMapping("update")
    public Result update(User user, RefundVo refundVo) {
        Refund refund = new Refund();
        BeanUtils.copyProperties(refundVo, refund);
        if (refundVo.getRefundDateStr() != null) {
            LocalDateTime localDateTime = ImportTestUtil.parseStringLocalDateTime(refundVo.getRefundDateStr());
            refund.setRefundDate(localDateTime);
        }
        updateAction(refund, user);
        return refundService.update(refund) > 0 ?
                Result.success(null) : Result.error(CodeMsg.ERROR);

    }

    @ApiOperation(value = "根据多条件查询退款信息", notes = "已测")
    @GetMapping("searchRefund")
    public Result searchRefund(@RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "customerId", required = false) Long customerId,
                               @RequestParam(value = "createUserId", required = false) Long createUserId,
                               @RequestParam(value = "employeeId", required = false) Long employeeId,
                               @RequestParam(value = "createTimeMax", required = false) String createTimeMax,
                               @RequestParam(value = "createTimeMin", required = false) String createTimeMin,
                               @RequestParam(value = "orderBaseId", required = false) Long orderBaseId,
                               @RequestParam(value = "returnTimeMax", required = false) String returnTimeMax,
                               @RequestParam(value = "returnTimeMin", required = false) String returnTimeMin,
                               @RequestParam(value = "amountMax", required = false) Double amountMax,
                               @RequestParam(value = "amountMin", required = false) Double amountMin,
                               @RequestParam(value = "returnMethod", required = false) Double returnMethod,
                               @RequestParam(value = "customerName", required = false) String customerName,
                               @RequestParam(value = "relationName", required = false) String relationName,
                               @ApiParam(name = "isToday", value = "今天", required = false)
                                   @RequestParam(value = "isToday", required = false) String isToday,
                               @ApiParam(name = "yesterday", value = "昨天", required = false)
                                   @RequestParam(value = "yesterday", required = false) String yesterday,
                               @ApiParam(name = "preMouth", value = "上月", required = false)
                                   @RequestParam(value = "preMouth", required = false) String preMouth,
                               @ApiParam(name = "isMouth", value = "本月", required = false)
                                   @RequestParam(value = "isMouth", required = false) String isMouth,
                               @ApiParam(name = "sort",value = "排序用的,不输入默认asc 可输入项为asc或者desc")
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //将payTimeMin和payTimeMax改为时间格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        try {
            if (returnTimeMin != null) {
                request.setAttribute("returnTimeMin", df.parse(returnTimeMin));
            }
            if (returnTimeMax != null) {
                request.setAttribute("returnTimeMax", df.parse(returnTimeMax));
            }
            if (createTimeMax != null) {
                request.setAttribute("createTimeMax", df.parse(createTimeMax));
            }
            if (createTimeMin != null) {
                request.setAttribute("createTimeMin", df.parse(createTimeMin));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RefundSearchVo refundSearchVo = RefundSearchVo.getProperties(request, new RefundSearchVo());
        List<RefundVo> refundVos = refundService.searchRefund(refundSearchVo);
        PageInfo<RefundVo> pageInfo = new PageInfo<>(refundVos);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "根据时间,负责人id,状态多条件查询退款记录", notes = "已测")
    @GetMapping("searchRefundByCondition")
    public Result searchRefundByCondition(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "day", required = false) Integer day,
            @ApiParam(name = "sortType", value = "展示方式（倒序DESC，正序ASC）", defaultValue = "DESC", required = false)
            @RequestParam(value = "sortType", required = false) String sortType,
            @ApiParam(name = "employeeId", value = "订单负责人ID", required = false)
            @RequestParam(value = "employeeId", required = false) String employeeId,
            @ApiParam(name = "pageNum", value = "第几页", required = false)
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageNum", value = "一页几条", required = false)
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        RefundStatisitcVo refundStatisitcVo = RefundStatisitcVo.getProperties(request, new RefundStatisitcVo());
        List<RefundVo> list = refundService.searchRefundByCondition(refundStatisitcVo);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<RefundVo> pageInfo = new PageInfo<>(list);
        // List<RefundVo> list1 = pageInfo.getList();
        return list != null ?
                Result.success(pageInfo) : Result.error(CodeMsg.ERROR);

    }

    @GetMapping("exportExcel")
    @ApiOperation(value = "导出退款模板",notes = "已测")
    public Result exportExcel(HttpServletRequest request, HttpServletResponse response) {
        OutputStream out = null;

        try {
            HttpServletResponse response1 = DownloadUtil.setResponseHeader(response, "退款信息");
            out = response1.getOutputStream();

            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            List<RefundExportVo> refundExportVos = refundExportExtMapper.selectAllToExport();
            Sheet sheet = new Sheet(1,0,RefundExportVo.class);
            sheet.setSheetName("测试");
            writer.write(refundExportVos,sheet);
            writer.finish();
            out.close();
            return  null;
        } catch (IOException e) {
            return null;
        }

    }

    @ApiOperation(value = "将excel文件中的退款记录导入数据库，并更新相关订单状态为已退款", notes = "已测")
    @PostMapping("/import")
    public Result importExcel(User user,
                              @RequestParam("file") MultipartFile file, HttpServletResponse response){

        if (null == file) {
            return Result.error(CodeMsg.INVALID_FILE_TYPE);
        }
        //解析excel
        List<Object> list = ExcelUtil.readExcel(file, new RefundImportVO());

        if (null == list || list.size() == 0) {
            return Result.error(CodeMsg.FILE_DATE_EMPTY);
        }

        //获取现有订单信息
        List<Long> orderBaseList = orderBaseService.selectAll().stream().map(OrderBase::getId).collect(Collectors.toList());
        //获取现有负责人联系人回款记录
        List<Long> userList = userService.selectAll().stream().map(User::getId).collect(Collectors.toList());
        List<Long> relationList = relationService.selectAll().stream().map(Relation::getId).collect(Collectors.toList());
        List<Long> refundListOld = refundService.selectAll().stream().map(Refund::getId).collect(Collectors.toList());
        //获取已回款总金额
        Map<String, Double> getSumMap = new HashMap<>(16);
        for (OrderBaseVo vo : orderBaseService.selectOrderBaseInfo()){
            getSumMap.put(vo.getId().toString(), vo.getOrderGetSum());
        }

        //创建要变更状态的订单集合
        Map<String, Byte> stateMap = new HashMap<>(16);
        //创建退款集合
        List<Refund> refundList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++){
            //获取VO类
            RefundImportVO importVo= (RefundImportVO) list.get(i-1);

            //判断订单是否已经有退款记录
            if (refundListOld.contains(Converter.toLong(importVo.getOrderBaseId()))){
                return Result.error(new CodeMsg("第"+i+"行出错，"+"出错原因：编号为"+importVo.getOrderBaseId()+"的订单已有过退款记录，或本行之前已有一条退款数据"));
            }

            //判断负责人id
            String testEmployeeId = ImportTestUtil.testId(i,importVo.getEmployeeId(),"负责人id",userList);
            if (testEmployeeId != null){
                return Result.error(new CodeMsg(testEmployeeId));
            }

            //判断联系人id
            String testRelationId = ImportTestUtil.testId(i,importVo.getRelationId(),"联系人id",relationList);
            if (testRelationId != null){
                return Result.error(new CodeMsg(testRelationId));
            }

            //判断订单id
            String testOrderBaseId = ImportTestUtil.testId(i,importVo.getOrderBaseId(),"订单id",orderBaseList);
            if (testOrderBaseId != null){
                return Result.error(new CodeMsg(testOrderBaseId));
            }

            //判断退款时间
            String testPayTime = ImportTestUtil.testLocalDateTime(i,importVo.getRefundDate(),"退款时间");
            if (testPayTime != null){
                return Result.error(new CodeMsg(testPayTime));
            }

            //判断退款金额金额
            String testOrderCost = ImportTestUtil.testDecimals(i,importVo.getRefundAmount(),"退款金额");
            if (testOrderCost != null){
                return Result.error(new CodeMsg(testOrderCost));
            }

            //获取当前已回款总金额
            Double getSum = getSumMap.get(importVo.getOrderBaseId());
            //判断退款金额是否大于已回款总金额
            if (Converter.toDouble(importVo.getRefundAmount()) > getSum){
                return Result.error(new CodeMsg("第"+i+"行出错，"+"出错原因：退款金额大于编号为"+importVo.getOrderBaseId()+"的订单已回款总额"));
            }
            //添加要变更状态的订单
            stateMap.put(importVo.getOrderBaseId(), (byte) 4);

            //判断支付方式
            String testPaymentMethod = ImportTestUtil.testNullOrEmpty(i,importVo.getRefundMethod(),"支付方式");
            if (testPaymentMethod != null){
                return Result.error(new CodeMsg(testPaymentMethod));
            }

            //转换为退款实体类
            Refund refund = new Refund();
            refund = RefundImportVO.getProperties(refund, importVo);
            createAction(refund,user);

            refundList.add(refund);

            //将这条记录加入以退款的订单列表
            refundListOld.add(refund.getOrderBaseId());
        }

        return refundService.importExcel(refundList,stateMap) < 2 ?
                Result.error(CodeMsg.ERROR) : Result.success(stateMap);
    }

    @ApiOperation(value = "根据退款ID的数组进行批量删除，同时修改相应订单的状态为0(未付款)", notes = "传入退款ID的数组(已测)")
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] refundId) {
        refundService.deleteBatch(refundId);
        return Result.success(null);
    }
}
