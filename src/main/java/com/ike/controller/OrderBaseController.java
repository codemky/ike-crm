package com.ike.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.common.util.DownloadUtil;
import com.ike.common.util.ExcelUtil;
import com.ike.common.util.ImportTestUtil;
import com.ike.mapper.ext.OrderBaseExtMapper;
import com.ike.pojo.OrderBase;
import com.ike.pojo.OrderProduct;
import com.ike.pojo.Relation;
import com.ike.pojo.User;
import com.ike.pojo.vo.*;
import com.ike.service.*;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Date: 2019-10-11 23:40
 * @Description:
 */
@Api(description = "订单模块")
@RestController
@RequestMapping("/json/orderBase")
public class OrderBaseController {

    @Autowired
    OrderBaseService orderBaseService;

    @Autowired
    UserService userService;

    @Autowired
    RelationService relationService;

    @Autowired
    ReturnDetailService returnDetailService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    OrderBaseExtMapper orderBaseExtMapper;

    @Autowired
    OrderProductService orderProductService;

    @Autowired
    ProductService productService;

    private static void updateAction(OrderBase orderBase, User user) {
        orderBase.setModifyUserId(user.getId());
        orderBase.setModifyTime(LocalDateTime.now());
    }

    private static void createAction(OrderBase orderBase, User user) {
        orderBase.setCreateUserId(user.getId());
        orderBase.setCreateTime(LocalDateTime.now());
        orderBase.setModifyUserId(user.getId());
        orderBase.setModifyTime(LocalDateTime.now());
    }

    private static void createActionOrderProduct(OrderProduct orderProduct, User user) {
        orderProduct.setCreateUserId(user.getId());
        orderProduct.setCreateTime(LocalDateTime.now());
        orderProduct.setModifyUserId(user.getId());
        orderProduct.setModifyTime(LocalDateTime.now());
    }

    private static void updateActionOrderProduct(OrderProduct orderProduct, User user) {
        orderProduct.setModifyUserId(user.getId());
        orderProduct.setModifyTime(LocalDateTime.now());
    }

    @ApiOperation(value = "查询所有的订单", notes = "已测")
    @GetMapping("listAll")
    public Result listAll(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderBaseVo> orderBases = orderBaseService.selectOrderBaseAll();
        PageInfo<OrderBaseVo> pageInfo = new PageInfo<>(orderBases);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "根据订单ID查询订单，和订单产品以及产品信息", notes = "已测")
    @GetMapping("detail")
    public Result detail(
            @RequestParam(value = "orderBaseId", required = false) Long orderBaseId) {
        OrderDetailVo orderDetailVo = orderBaseService.selectOrderBaseByOrderId(orderBaseId);
        orderDetailVo.setOrderProductVoList(orderProductService.selectAllByOrderBaseId(orderBaseId));
        return orderDetailVo != null ?
                Result.success(orderDetailVo) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据负责员工查询订单", notes = "已测")
    @GetMapping("listAllByEmployeeId")
    public Result listAllByEmployeeId(User user,
                                      @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderBaseVo> orderBases = orderBaseService.selectOrderBaseByEemployeeId(user.getId());
        PageInfo<OrderBaseVo> pageInfo = new PageInfo<>(orderBases);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "根据负责员工和订单状态查询订单", notes = "已测")
    @GetMapping("listAllByEmployeeIdAndOrderState")
    public Result listAllByEmployeeIdAndOrderState(User user,
                                                   @ApiParam(name = "orderState", value = "订单状态（0未付款，1已付款，2回款中，3退款中，4退款完成，5表示删除状态）", defaultValue = "0", required = false)
                                                   @RequestParam(value = "orderState", defaultValue = "0", required = false) Byte orderState,
                                                   @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderBaseVo> orderBases = orderBaseService.selectOrderBaseByEemployeeIdAndStatus(user.getId(), orderState);
        PageInfo<OrderBaseVo> pageInfo = new PageInfo<>(orderBases);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "创建订单", notes = "时间默认为当前时间，状态默认为0（未付款）")
    @PostMapping("create")
    public Result create(User user,
                         OrderBaseVo orderBaseVo) {
        OrderBase orderBase = new OrderBase();
        LocalDateTime localDateTime = ImportTestUtil.parseStringLocalDateTime(orderBaseVo.getOrderTimeStr());
        BeanUtils.copyProperties(orderBaseVo, orderBase);
        orderBase.setOrderTime(localDateTime);
        createAction(orderBase, user);
        return orderBaseService.insert(orderBase) > 0 ?
                Result.success(orderBase.getId()) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据订单编号逻辑删除订单", notes = "已测")
    @DeleteMapping("delete")
    public Result delete(
            @RequestParam(value = "orderBaseId", required = false) Long orderBaseId) {
        return orderBaseService.deleteById(orderBaseId) > 0 ?
                Result.success(null) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "更新订单", notes = "时间默认为当前时间")
    @PutMapping("update")
    public Result update(User user,
                         OrderBaseVo orderBaseVo) {
        OrderBase orderBase = new OrderBase();
        if (orderBaseVo.getOrderTimeStr() != null) {
            LocalDateTime localDateTime = ImportTestUtil.parseStringLocalDateTime(orderBaseVo.getOrderTimeStr());
            orderBase.setOrderTime(localDateTime);
        }
        BeanUtils.copyProperties(orderBaseVo, orderBase);
        updateAction(orderBase, user);
        return orderBaseService.update(orderBase) > 0 ?
                Result.success(null) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据客户联系人id，订单产品更新订单以及对应订单产品", notes = "时间默认为当前时间")
    @PutMapping("updateOrderWithProduct")
    public Result updateOrderWithProduct(User user,
                                         @RequestBody OrderDetailVo orderDetailVo) {
        List<OrderProductVo> list = orderDetailVo.getOrderProductVoList();
        if (list == null || list.size() == 0){
            return Result.error(new CodeMsg("订单产品不能为空！"));
        }
        //计算相关金额
        String errorMsg = orderBaseService.getOrderDetailVo(orderDetailVo);
        if (errorMsg != null){
            return Result.error(new CodeMsg(errorMsg));
        }
        //更新订单
        OrderBase orderBase = new OrderBase();
        orderBase.setId(orderDetailVo.getId());
        //获取订单创建时间
        BeanUtils.copyProperties(orderDetailVo, orderBase);
        updateAction(orderBase, user);
        int updateCount = orderBaseService.update(orderBase);

        //删除旧产品列表，创建新产品列表
        orderProductService.deleteListByOrderBaseId(orderBase.getId());
        //插入订单产品列表
        for (OrderProduct orderProduct : orderDetailVo.getOrderProductVoList()) {
            orderProduct.setOrderBaseId(orderBase.getId());
            createActionOrderProduct(orderProduct, user);
            orderProductService.insert(orderProduct);
        }

        return updateCount > 0 ?
                Result.success(orderDetailVo) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据多条件搜索订单", notes = "已测")
    @GetMapping("listBySearch")
    public Result listBySearch(@RequestParam(value = "orderBaseId", required = false) Long orderBaseId,
                               @RequestParam(value = "orderTotalMin", required = false) Double orderTotalMin,
                               @RequestParam(value = "orderTotalMax", required = false) Double orderTotalMax,
                               @RequestParam(value = "productCountMax", required = false) Integer productCountMax,
                               @RequestParam(value = "productCountMin", required = false) Integer productCountMin,
                               @RequestParam(value = "orderCountMax", required = false) Integer orderCountMax,
                               @RequestParam(value = "orderCountMin", required = false) Integer orderCountMin,
                               @RequestParam(value = "relationName", required = false) String relationName,
                               @RequestParam(value = "customerId", required = false) Long customerId,
                               @RequestParam(value = "customerName", required = false) String customerName,
                               @RequestParam(value = "orderActualTotalMin", required = false) Double orderActualTotalMin,
                               @RequestParam(value = "orderActualTotalMax", required = false) Double orderActualTotalMax,
                               @RequestParam(value = "orderCostMin", required = false) Double orderCostMin,
                               @RequestParam(value = "orderCostMax", required = false) Double orderCostMax,
                               @RequestParam(value = "orderGetSumMin", required = false) Double orderGetSumMin,
                               @RequestParam(value = "orderGetSumMax", required = false) Double orderGetSumMax,
                               @RequestParam(value = "profitMin", required = false) Double profitMin,
                               @RequestParam(value = "profitMax", required = false) Double profitMax,
                               @RequestParam(value = "beginCreateTime", required = false) String beginCreateTime,
                               @RequestParam(value = "endCreateTime", required = false) String endCreateTime,
                               @RequestParam(value = "relationId", required = false) Long relationId,
                               @RequestParam(value = "employeeId", required = false) Long employeeId,
                               @RequestParam(value = "productName", required = false) String productName,
                               @RequestParam(value = "className", required = false) String className,
                               @RequestParam(value = "sortName", required = false) String sortName,
                               @RequestParam(value = "sortType", required = false) String sortType,
                               @RequestParam(value = "orderState", required = false) String orderState,
                               @ApiParam(name = "isToday", value = "今天", required = false)
                               @RequestParam(value = "isToday", required = false) String isToday,
                               @ApiParam(name = "yesterday", value = "昨天", required = false)
                               @RequestParam(value = "yesterday", required = false) String yesterday,
                               @ApiParam(name = "preMouth", value = "上月", required = false)
                               @RequestParam(value = "preMouth", required = false) String preMouth,
                               @ApiParam(name = "isMouth", value = "本月", required = false)
                               @RequestParam(value = "isMouth", required = false) String isMouth,
                               @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //将payTimeMin和payTimeMax改为时间格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (beginCreateTime != null) {
                request.setAttribute("beginCreateTime", df.parse(beginCreateTime));
            }
            if (endCreateTime != null) {
                request.setAttribute("endCreateTime", df.parse(endCreateTime));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderBaseSearchVo orderBaseSearchVo = OrderBaseSearchVo.getProperties(request, new OrderBaseSearchVo());
        List<OrderBaseVo> orderBaseVos = orderBaseService.searchOrderBase(orderBaseSearchVo);
        PageInfo<OrderBaseVo> pageInfo = new PageInfo<>(orderBaseVos);
        System.out.println(11);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "根据订单id批量物理删除订单以及其关联的回退款信息", notes = "已测")
    @DeleteMapping("deleteBatch")
    public Result deleteBatch(@RequestBody Long[] order_base_id) {
        int i = 0;
        for (Long id : order_base_id){
            orderBaseService.deleteAllByOrderBaseId(id);
            i++;
        }
        return Result.success(i);
    }

    @ApiOperation(value = "根据订单id物理删除订单以及其关联的回退款信息", notes = "已测")
    @DeleteMapping("deleteAllById")
    public Result deleteAllById(@ApiParam(name = "order_base_id", value = "要删除关联信息的订单id", required = false)
                                @RequestParam(value = "order_base_id", required = false) Long order_base_id) {
        int i = orderBaseService.deleteAllByOrderBaseId(order_base_id);
        return i > 0 ?
                Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据负责人id删除订单模块所有关联记录", notes = "包括：订单基本信息，退款记录，回款记录和产品订单表")
    @DeleteMapping("deleteAllByEmployeeId")
    public Result deleteAllByEmployeeId(@ApiParam(name = "employee_id", value = "要删除关联信息的员工id", required = true)
                                        @RequestParam("employee_id") Long employee_id) {
        return orderBaseService.deleteAllByEmployeeId(employee_id) > 0 ?
                Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "据负责人id更新旧负责人所有关联记录为新负责人的id", notes = "包括：订单基本信息，退款记录，回款记录和产品订单表")
    @PutMapping("updateAllByEmployeeId")
    public Result updateAllByEmployeeId(User user,
                                        @ApiParam(name = "old_employee_id", value = "要与信息取消关联的旧员工id", required = true)
                                        @RequestParam("old_employee_id") Long old_employee_id,
                                        @ApiParam(name = "new_employee_id", value = "要与信息关联的员工id", required = true)
                                        @RequestParam("new_employee_id") Long new_employee_id) {
        return orderBaseService.updateAllByEmployeeId(old_employee_id, new_employee_id, user) > 0 ?
                Result.success(null) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据负责人id查询相关记录的数量", notes = "包括：订单基本信息，退款记录，回款记录和产品订单表")
    @GetMapping("countAllByEmployeeId")
    public Result countAllByEmployeeId(@ApiParam(name = "employee_id", value = "要查询关联信息的员工id", required = false)
                                       @RequestParam(value = "employee_id") Long employee_id
    ) {
        Map<String, Integer> map = orderBaseService.selectCountAllByEmployeeId(employee_id);
        return map != null ?
                Result.success(map) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据客户联系人id查询相关记录的数量", notes = "包括：订单基本信息，退款记录，回款记录和产品订单表")
    @GetMapping("countAllByRelationId")
    public Result countAllByRelationId(@ApiParam(name = "relation_id", value = "要查询关联信息的负责人id", required = true)
                                       @RequestParam(value = "relation_id") Long relation_id) {
        Map<String, Integer> map = orderBaseService.selectCountAllByRelationId(relation_id);
        return map != null ?
                Result.success(map) : Result.error(CodeMsg.ERROR);
    }


    @ApiOperation(value = "根据时间,负责人id,状态进行多条件查询订单", notes = "已测")
    @GetMapping("searchOrderBaseByCondition")
    public Result searchOrderBaseByCondition(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "day", required = false) Integer day,
            /*@ApiParam(name = "staName", value = "要统计的字段（order_total为订单成交总额，order_cost为订单总成本，order_get_sum为订单回款总额，(order_actual_total - order_cost)为利润）", defaultValue = "order_total", required = false)
            @RequestParam(value = "staName",required = false) String staName,*/
            @ApiParam(name = "sortType", value = "展示方式（倒序DESC，正序ASC）", defaultValue = "DESC", required = false)
            @RequestParam(value = "sortType", required = false) String sortType,
            @ApiParam(name = "orderState", value = "订单状态（0未付款，1已付款，2回款中，3退款中，4退款完成，5表示删除状态）", defaultValue = "1", required = false)
            @RequestParam(value = "orderState", required = false) String orderState,
            @ApiParam(name = "employeeId", value = "订单负责人ID", required = false)
            @RequestParam(value = "employeeId", required = false) String employeeId,
            @ApiParam(name = "relationId", value = "客户联系人id", required = false)
            @RequestParam(value = "relationId", required = false) String relationId,
            @ApiParam(name = "pageNum", value = "第几页", required = false)
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageNum", value = "一页几条", required = false)
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        OrderBaseStatisitcVO orderBaseStatisitcVO = OrderBaseStatisitcVO.getProperties(request, new OrderBaseStatisitcVO());
        List<OrderBaseVo> list = orderBaseService.searchOrderBaseByTime(orderBaseStatisitcVO);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderBaseVo> pageInfo = new PageInfo<>(list);
        // List<OrderBaseVo> list1 = pageInfo.getList();
        return list != null ?
                Result.success(pageInfo) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据联系人id删除订单模块所有关联记录", notes = "包括：订单基本信息，退款记录，回款记录和产品订单表(测试通过)")
    @DeleteMapping("deleteAllByRelationId")
    public Result deleteAllByRelationId(@ApiParam(name = "relation_id", value = "要删除关联信息的联系人id", required = true)
                                        @RequestParam("relation_id") Long relation_id) {
        int falg = orderBaseService.deleteAllByRelationId(relation_id);
        return falg > 0 ?
                Result.success(falg) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "将excel文件的订单基本信息导入数据库", notes = "已测")
    @PostMapping("/import")
    public Result importExcel(User user,
                              @RequestParam("file") MultipartFile file, HttpServletResponse response) {

        if (null == file) {
            return Result.error(CodeMsg.INVALID_FILE_TYPE);
        }
        //解析excel
        List<Object> list = ExcelUtil.readExcel(file, new OrderBaseImportVO());

        if (null == list || list.size() == 0) {
            return Result.error(CodeMsg.FILE_DATE_EMPTY);
        }

        //获取现有负责人联系人
        List<Long> userList = userService.selectAll().stream().map(User::getId).collect(Collectors.toList());
        List<Long> relationList = relationService.selectAll().stream().map(Relation::getId).collect(Collectors.toList());

        List<OrderBase> orderBaseList = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            //获取VO类
            OrderBaseImportVO importVo = (OrderBaseImportVO) list.get(i - 1);

            //判断负责人
            String testEmployeeId = ImportTestUtil.testId(i, importVo.getEmployeeId(), "负责人id", userList);
            if (testEmployeeId != null) {
                return Result.error(new CodeMsg(testEmployeeId));
            }

            //判断联系人
            String testRelationId = ImportTestUtil.testId(i, importVo.getRelationId(), "联系人id", relationList);
            if (testRelationId != null) {
                return Result.error(new CodeMsg(testRelationId));
            }

            //判断订单金额
            String testOrderTotal = ImportTestUtil.testDecimals(i, importVo.getOrderTotal(), "订单金额");
            if (testOrderTotal != null) {
                return Result.error(new CodeMsg(testOrderTotal));
            }

            //判断订单实际总额
            String testOrderActualTotal = ImportTestUtil.testDecimals(i, importVo.getOrderActualTotal(), "订单实际总额");
            if (testOrderActualTotal != null) {
                return Result.error(new CodeMsg(testOrderActualTotal));
            }

            //判断订单成本
            String testOrderCost = ImportTestUtil.testDecimals(i, importVo.getOrderCost(), "订单成本");
            if (testOrderCost != null) {
                return Result.error(new CodeMsg(testOrderCost));
            }

            //判断支付方式
            String testPaymentMethod = ImportTestUtil.testNullOrEmpty(i, importVo.getPaymentMethod(), "支付方式");
            if (testPaymentMethod != null) {
                return Result.error(new CodeMsg(testPaymentMethod));
            }

            //转换为实体类
            OrderBase orderBase = new OrderBase();
            OrderBaseImportVO.getProperties(orderBase, importVo);
            createAction(orderBase, user);

            //添加至集合
            orderBaseList.add(orderBase);
        }
        orderBaseService.insertList(orderBaseList);

        return Result.success(orderBaseList);
    }

    @ApiOperation(value = "订单表导出", notes = "已测")
    @GetMapping("/export")
    public Result export(HttpServletResponse response) {
        OutputStream out = null;

        try {
            HttpServletResponse response1 = DownloadUtil.setResponseHeader(response, "订单信息");
            out = response1.getOutputStream();

            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            List<OrderExportVo> orderExportVos = orderBaseExtMapper.orderBaseExport();
            Sheet sheet = new Sheet(1, 0, OrderExportVo.class);
            sheet.setSheetName("订单信息");
            writer.write(orderExportVos, sheet);
            writer.finish();
            out.close();

            return null;
        } catch (IOException e) {
            return null;
        }

    }

    @ApiOperation(value = "根据客户联系人id，订单产品创建订单以及对应订单产品", notes = "已测")
    @PostMapping("/createOrderWithProduct")
    public Result createOrderWithProduct(User user,
                                         @RequestBody OrderDetailVo orderDetailVo) {
        List<OrderProductVo> list = orderDetailVo.getOrderProductVoList();
        if (list == null || list.size() == 0){
            return Result.error(new CodeMsg("订单产品不能为空！"));
        }

        //计算相关金额
        String errorMsg = orderBaseService.getOrderDetailVo(orderDetailVo);
        if (errorMsg != null){
            return Result.error(new CodeMsg(errorMsg));
        }

        //创建新订单，并添加基本信息
        OrderBase orderBase = new OrderBase();
        BeanUtils.copyProperties(orderDetailVo, orderBase);
        createAction(orderBase, user);
        //获取订单创建时间
        orderBase.setOrderTime(ImportTestUtil.parseStringLocalDateTime(orderDetailVo.getOrderTimeStr()));
        //计划表为默认的空计划
        orderBase.setReturnPlanId((long) 0);
        //添加联系人
        orderBase.setRelationId(orderDetailVo.getRelationId());
        //负责员工默认为操作用户
        orderBase.setEmployeeId(user.getId());
        //默认订单状态为0（待付款）
        orderBase.setOrderState(Byte.parseByte("0"));
        //默认回款总额为0
        orderBase.setOrderGetSum(0.0);
        //添加实际总额
        orderDetailVo.setOrderActualTotal(orderDetailVo.getOrderActualTotal());
        //插入订单同时获取订单id
        orderBaseService.insert(orderBase);
        //插入订单产品列表
        for (OrderProduct orderProduct : orderDetailVo.getOrderProductVoList()) {
            orderProduct.setOrderBaseId(orderBase.getId());
            createActionOrderProduct(orderProduct, user);
            orderProductService.insert(orderProduct);
        }

        return orderBase.getId() != null ?
                Result.success(orderBase.getId()) : Result.error(CodeMsg.ERROR);
    }
}