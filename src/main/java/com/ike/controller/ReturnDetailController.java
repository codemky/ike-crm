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
import com.ike.mapper.ext.ReturnDetailExtMapper;
import com.ike.pojo.OrderBase;
import com.ike.pojo.Relation;
import com.ike.pojo.ReturnDetail;
import com.ike.pojo.User;
import com.ike.pojo.vo.*;
import com.ike.service.OrderBaseService;
import com.ike.service.RelationService;
import com.ike.service.ReturnDetailService;
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
import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Date 2019/10/12 1:54
 */
@RestController
@RequestMapping("/json/returnDetail/")
@Api(description = "回款记录模块")
public class ReturnDetailController{

    @Autowired
    ReturnDetailService returnDetailService;

    @Autowired
    OrderBaseService orderBaseService;

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ReturnDetailExtMapper returnDetailExtMapper;

    @Autowired
    RelationService relationService;

    private static void updateAction(ReturnDetail returnDetail, User user) {
        returnDetail.setEmployeeId(user.getId());
        returnDetail.setModifyUserId(user.getId());
        returnDetail.setModifyTime(LocalDateTime.now());
    }

    private static void createAction(ReturnDetail returnDetail, User user) {
        returnDetail.setEmployeeId(user.getId());
        returnDetail.setCreateUserId(user.getId());
        returnDetail.setCreateTime(LocalDateTime.now());
        returnDetail.setModifyUserId(user.getId());
        returnDetail.setModifyTime(LocalDateTime.now());
    }

    @ApiOperation(value = "新增回款记录信息，并修改订单的相应信息,修改订单状态", notes = "测试通过")
    @PostMapping("create")
    public Result create(User user,
                         ReturnDetailVo returnDetailVo) {
        ReturnDetail returnDetail = new ReturnDetail();
        BeanUtils.copyProperties(returnDetailVo, returnDetail);
        LocalDateTime localDateTime = ImportTestUtil.parseStringLocalDateTime(returnDetailVo.getPayTimeStr());
        returnDetail.setPayTime(localDateTime);
        createAction(returnDetail,user);
        return returnDetailService.insertReturnDetail(returnDetail) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_RETURN_LESS_TOTAL);
    }

    @ApiOperation(value = "查询所有的回款记录，并添加对应ID的人员名字")
    @GetMapping("listAll")
    public Result listAll(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ReturnDetailVo> r_list = returnDetailService.selectAllList();
        PageInfo<ReturnDetailVo> pageinfo = new PageInfo<ReturnDetailVo>(r_list, pageSize);
        return Result.success(pageinfo);
    }

    @ApiOperation(value = "根据订单ID查询回款记录，并添加对应ID的人员名字")
    @GetMapping("listAllByOrderBaseId")
    public Result listAllByOrderBaseId(@ApiParam(name = "order_base_id", value = "订单ID", required = true)
                                       @RequestParam(value = "order_base_id", required = true) Long order_base_id,
                                       @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ReturnDetailVo> r_list = returnDetailService.selectByOrderBaseId(order_base_id);
        PageInfo<ReturnDetailVo> pageinfo = new PageInfo<>(r_list, pageSize);
        return Result.success(pageinfo);
    }

    @ApiOperation(value = "根据客户联系人ID查询回款记录，并添加对应ID的人员名字")
    @GetMapping("listAllByRelationId")
    public Result listAllByRelationId(@ApiParam(name = "relation_id", value = "联系人ID", required = true)
                                      @RequestParam(value = "relation_id", required = true) Long relation_id,
                                      @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ReturnDetailVo> r_list = returnDetailService.selectByRelationId(relation_id);
        PageInfo<ReturnDetailVo> pageinfo = new PageInfo<ReturnDetailVo>(r_list, pageSize);
        return Result.success(pageinfo);
    }


    @ApiOperation(value = "根据订单ID查询所有的回款记录个数")
    @GetMapping("countId")
    public Result countId(@ApiParam(name = "order_base_id", value = "订单ID", required = true)
                          @RequestParam(value = "order_base_id", required = true) Long order_base_id){
        int falg = returnDetailService.countId(order_base_id);
        return Result.success(falg);
    }

    @ApiOperation("修改回款记录")
    @PutMapping("update")
    public Result update(User user,
                         ReturnDetailVo returnDetailVo) {
        ReturnDetail returnDetail = new ReturnDetail();
        BeanUtils.copyProperties(returnDetailVo, returnDetail);
        if (returnDetailVo.getPayTimeStr() != null) {
            LocalDateTime localDateTime = ImportTestUtil.parseStringLocalDateTime(returnDetailVo.getPayTimeStr());
            returnDetail.setPayTime(localDateTime);
        }
        updateAction(returnDetail,user);
        return Result.success(returnDetailService.update(returnDetail));
    }

    @ApiOperation(value = "根据id删除回款记录，同时修改相应订单的回款金额和订单状态为2(付款中")
    @DeleteMapping("delete")
    public Result delete(@ApiParam(name = "id", value = "回款记录ID", required = true)
                         @RequestParam(value = "id", required = true)  Long id){
        return returnDetailService.deleteById(id) > 0
                ?  Result.success(1) : Result.error();
    }

    @ApiOperation(value = "根据特定信息进行回款搜索", notes = "已测")
    @GetMapping("/listReturnDetailSearch")
    public Result listReturnDetailSearch(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "createUserId", required = false) Long createUserId,
            @RequestParam(value = "employeeId", required = false) Long employeeId,
            @RequestParam(value = "createTimeMax", required = false) String createTimeMax,
            @RequestParam(value = "createTimeMin", required = false) String createTimeMin,
            @RequestParam(value = "relationName", required = false) String relationName,
            @RequestParam(value = "customerName", required = false) String customerName,
            @RequestParam(value = "orderBaseId", required = false) Long orderBaseId,
            @RequestParam(value = "payTimeMax", required = false) String payTimeMax,
            @RequestParam(value = "payTimeMin", required = false) String payTimeMin,
            @RequestParam(value = "isThisMonth",required = false) String isThisMonth,
            @RequestParam(value = "isLastMonth",required = false) String isLastMonth,
            @RequestParam(value = "isToday",required = false) String isToday,
            @RequestParam(value = "isYesterday",required = false) String isYesterday,
            @RequestParam(value = "amountMax", required = false) Double amountMax,
            @RequestParam(value = "amountMin", required = false) Double amountMin,
            @RequestParam(value = "paymentType", required = false) String paymentType,
            @RequestParam(value = "sortType", required = false, defaultValue = "DESC") String sortType,
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //将payTimeMin和payTimeMax改为时间格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (payTimeMin != null) {
                request.setAttribute("payTimeMin", df.parse(payTimeMin));
            }
            if (payTimeMax != null) {
                request.setAttribute("payTimeMax", df.parse(payTimeMax));
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
        ReturnDetailSearchVo returnDetailSearchVo = ReturnDetailSearchVo.getProperties(request, new ReturnDetailSearchVo());
        List<ReturnDetailVo> detailList = returnDetailService.listReturnDetailSearch(returnDetailSearchVo);
        PageInfo<ReturnDetailVo> pageInfo = new PageInfo<>(detailList);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "根据时间,负责人id,状态进行多条件查询回款记录", notes = "已测")
    @GetMapping("searchReturnDetailByTime")
    public Result searchReturnDetailByTime(
            @ApiParam(name = "relationName", value = "联系人名称(模糊查询)", required = false)
            @RequestParam(value = "relationName", required = false) String relationName,
            @ApiParam(name = "customerName", value = "客户人名称(模糊查询)", required = false)
            @RequestParam(value = "customerName", required = false) String customerName,
            @ApiParam(name = "isToday", value = "今天", required = false)
            @RequestParam(value = "isToday", required = false) String isToday,
            @ApiParam(name = "yesterday", value = "昨天", required = false)
            @RequestParam(value = "yesterday", required = false) String yesterday,
            @ApiParam(name = "preMouth", value = "上月", required = false)
            @RequestParam(value = "preMouth", required = false) String preMouth,
            @ApiParam(name = "isMouth", value = "本月", required = false)
            @RequestParam(value = "isMouth", required = false) String isMouth,
            // @RequestParam(value = "year", required = false) Integer year,
            // @RequestParam(value = "month", required = false) Integer month,
            // @RequestParam(value = "day", required = false) Integer day,
            @ApiParam(name = "sortType", value = "展示方式（倒序DESC，正序ASC）", defaultValue = "DESC", required = false)
            @RequestParam(value = "sortType", required = false) String sortType,
            @ApiParam(name = "employeeId", value = "订单负责人ID", required = false)
            @RequestParam(value = "employeeId", required = false) String employeeId,
            @ApiParam(name = "pageNum", value = "第几页", required = false)
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageNum", value = "一页几条", required = false)
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        ReturnDetailStatisitcVo returnDetailStatisitcVo = ReturnDetailStatisitcVo.getProperties(request, new ReturnDetailStatisitcVo());
        List<ReturnDetailVo> list = returnDetailService.searchReturnDetailByTime(returnDetailStatisitcVo);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ReturnDetailVo> pageInfo = new PageInfo<>(list);
        // List<ReturnDetailVo> list1 = pageInfo.getList();
        return list != null ?
                Result.success(pageInfo) : Result.error(CodeMsg.ERROR);

    }

    @ApiOperation(value = "将excel文件的回款记录导入数据库，并更新相关订单记录", notes = "已测")
    @PostMapping("/import")
    public Result importExcel(User user,
                              @RequestParam("file") MultipartFile file, HttpServletResponse response){

        if (null == file) {
            return Result.error(CodeMsg.INVALID_FILE_TYPE);
        }
        //解析excel
        List<Object> list = ExcelUtil.readExcel(file, new ReturnDetailImportVO());

        if (null == list || list.size() == 0) {
            return Result.error(CodeMsg.FILE_DATE_EMPTY);
        }

        //获取现有订单信息
        List<Long> orderBaseList = orderBaseService.selectAll().stream().map(OrderBase::getId).collect(Collectors.toList());
        //获取现有负责人联系人
        List<Long> userList = userService.selectAll().stream().map(User::getId).collect(Collectors.toList());
        List<Long> relationList = relationService.selectAll().stream().map(Relation::getId).collect(Collectors.toList());
        //获取总金额，已回款金额，回款次数集合
        Map<String, Double> totalMap = new HashMap<>(16);
        Map<String, Double> getSumMap = new HashMap<>(16);
        Map<String, Integer> returnDetailMap = new HashMap<>(16);
        for (OrderBaseVo vo : orderBaseService.selectOrderBaseInfo()){
            totalMap.put(vo.getId().toString(), vo.getOrderTotal());
            getSumMap.put(vo.getId().toString(), vo.getOrderGetSum());
            returnDetailMap.put(vo.getId().toString(), Converter.toInt(vo.getReturnDetailCount()));
        }

        //创建订单新回款金额
        Map<String, Double> getSumMapNew = new HashMap<>(16);
        //创建新订单状态集合
        Map<String, Byte> stateMapNew = new HashMap<>(16);
        //创建回款明细集合
        List<ReturnDetail> returnDetailList = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++){
            //获取VO类
            ReturnDetailImportVO importVo= (ReturnDetailImportVO) list.get(i-1);

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

            //判断回款时间
            String testPayTime = ImportTestUtil.testLocalDateTime(i,importVo.getPayTime(),"回款时间");
            if (testPayTime != null){
                return Result.error(new CodeMsg(testPayTime));
            }

            //判断支付金额
            String testOrderCost = ImportTestUtil.testDecimals(i,importVo.getAmount(),"支付金额");
            if (testOrderCost != null){
                return Result.error(new CodeMsg(testOrderCost));
            }

            //判断支付方式
            String testPaymentMethod = ImportTestUtil.testNullOrEmpty(i,importVo.getPayment_type(),"支付方式");
            if (testPaymentMethod != null){
                return Result.error(new CodeMsg(testPaymentMethod));
            }

            //转换为回款明细实体类
            ReturnDetail returnDetail = new ReturnDetail();
            ReturnDetailImportVO.getProperties(returnDetail,importVo);
            createAction(returnDetail,user);

            //回款明细检验完毕，添加至回款计划集合集合
            returnDetailList.add(returnDetail);

            //获取订单id，开始进行订单状态变更判断
            String orderBaseid = importVo.getOrderBaseId();

            //更新回款总金额
            double getSum = getSumMap.get(orderBaseid) + returnDetail.getAmount();
            //判断算上本次回款，回款金额是否超过订单金额
            if (getSum > totalMap.get(orderBaseid)){
                return Result.error(new CodeMsg("第"+i+"行出错，"+"出错原因：订单已还款金额超过了编号为"+orderBaseid+"的订单的订单金额"));
            }else if (getSum == totalMap.get(orderBaseid)){
                //如果本次回款刚好还完，则变更对应订单的已回款总额，并更新状态为已完成
                getSumMapNew.put(orderBaseid,getSum);
                stateMapNew.put(orderBaseid, (byte) 1);
                //更新新的订单的已回款总额，用于下一次判断
                getSumMap.put(orderBaseid,getSum);
            }else {
                //如果本次回款为正常回款，就只更新已回款总额
                getSumMapNew.put(orderBaseid, getSum);
                //更新新的已回款总额，用于下一次判断
                getSumMap.put(orderBaseid,getSum);
            }
        }

        return returnDetailService.importExcel(getSumMapNew,stateMapNew,returnDetailList) >= 3 ?
                Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }


    @ApiOperation(value = "回款表导出",notes = "已测试")
    @GetMapping("/exportReturnDetail")
    public Result exportReturnDetail(HttpServletResponse response) {
        OutputStream out = null;

        try {
            response = DownloadUtil.setResponseHeader(response, "订单信息");
            out = response.getOutputStream();

            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            List<ReturnDetailExportVo> exportVos = returnDetailExtMapper.exportReturnDetail();
            Sheet sheet = new Sheet(1, 0, ReturnDetailExportVo.class);
            sheet.setSheetName("回款信息");
            writer.write(exportVos, sheet);
            writer.finish();
            out.close();

            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @ApiOperation(value = "根据回款ID的数组进行批量删除，同时修改相应订单的回款金额和订单状态为2(付款中)", notes = "传入回款ID的数组(已测)")
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody Long[] returnDetailId) {
        returnDetailService.deleteBatch(returnDetailId);
        return Result.success(null);
    }

}
