package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.common.util.ExcelUtil;
import com.ike.common.util.ServletBeanUtil;
import com.ike.mapper.ext.StatisticsMapper;
import com.ike.pojo.*;
import com.ike.pojo.vo.CustomerListVo;
import com.ike.pojo.vo.PageInfo;
import com.ike.pojo.vo.StatisticsVo;
import com.ike.pojo.vo.*;
import com.ike.service.*;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.message.ReusableMessage;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.beans.Beans;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassName CustomerController
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/10 21:06
 **/
@Api(description = "客户模块")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerStageLogService stageLogService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private CustomerLevelService levelService;
    @Autowired
    private CustomerOriginService originService;
    @Autowired
    private CustomerStageService stageService;
    @Autowired
    private OrderBaseService orderBaseService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private FollowService followService;
    @Autowired
    private FollowPlanService followPlanService;
    @Autowired
    private FollowArrangeService followArrangeService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "根据多种条件客户信息列表（已测通过）", notes = "")
    @GetMapping("/listByCriteria")
    public Result listByCriteria(
            @RequestParam(value = "customerName", required = false) String customerName,
            @RequestParam(value = "customerStageId", required = false) Long customerStageId,
            @RequestParam(value = "customerLevelId", required = false) Long customerLevelId,
            @RequestParam(value = "customerOriginId", required = false) Long customerOriginId,
            @RequestParam(value = "customerState", required = false) String customerState,
            @RequestParam(value = "customerAddress", required = false) String customerAddress,
            @RequestParam(value = "createMin", required = false) String createMin,
            @RequestParam(value = "createMax", required = false) String createMax,
            @RequestParam(value = "isToday", required = false) String isToday,
            @RequestParam(value = "orderMinSum", required = false) Double orderMinSum,
            @RequestParam(value = "orderMaxSum ", required = false) Double orderMaxSum,
            @RequestParam(value = "orderMinCount", required = false) Long orderMinCount,
            @RequestParam(value = "orderMaxCount", required = false) Long orderMaxCount,
            @RequestParam(value = "complainMinCount", required = false) Long complainMinCount,
            @RequestParam(value = "complainMaxCount", required = false) Long complainMaxCount,
            @RequestParam(value = "differFollow", required = false) Long differFollow,
            @RequestParam(value = "isFollowed", required = false) String isFollowed,
            @RequestParam(value = "followMinCount", required = false) Long followMinCount,
            @RequestParam(value = "followMaxCount", required = false) Long followMaxCount,
            @RequestParam(value = "employIds", required = false) Long employIds,
            @RequestParam(value = "sortName", required = false) String sortName,
            @RequestParam(value = "sortType", required = false) String sortType,

            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        CustomerSearchVo criteria = CustomerSearchVo.getProperties(request, new CustomerSearchVo());

        IPage<CustomerListVo> voPage = customerService.selectListVoByCriteria(new Page<>(pageNum, pageSize), criteria);

        return Result.success(voPage);

    }

    @ApiOperation(value = "点击我的客户页面后，根据员工登录信息获取员工的客户信息 ", notes = "")
    @GetMapping("/listMyCustomer")
    public Result listMyCustomer(User user,
                                 @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        User userOption = Optional.ofNullable(user).orElse(new User());
        if (userOption.getId() == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        CustomerSearchVo criteria = CustomerSearchVo.getProperties(request, new CustomerSearchVo());
        criteria.setEmployeeIds(Collections.singletonList(userOption.getId()));

        IPage<CustomerListVo> voPage = customerService.selectListVoByCriteria(new Page<>(pageNum, pageSize), criteria);

        return Result.success(voPage);

    }


    @ApiOperation(value = "点击修改时的表单回显", notes = "")
    @GetMapping("update")
    public Result detailForUpdate(@RequestParam("Cid") Long cid) {

        CustomerVo customerVo = customerService.selectForUpdate(cid);

        return customerVo != null ? Result.success(customerVo) : Result.error(CodeMsg.CUSTOMER_FAILURE);
    }

    @ApiOperation(value = "修改客户记录", notes = "")
    @PutMapping("update")
    public Result update(User user, @RequestBody Customer customer) {
        //判断实体类完整性
        Customer customerOp = Optional.ofNullable(customer).orElse(new Customer());
        if (customerOp.getId() == null) {
            return Result.error(CodeMsg.CUSTOMER_FAILURE);
        }
        Long customerLevelId = customerOp.getCustomerLevelId();
        Long customerOriginId = customerOp.getCustomerOriginId();
        Long customerStageId = customerOp.getCustomerStageId();
        if (customerLevelId == null || customerOriginId == null || customerStageId == null) {
            return Result.error(405, "客户部分属性不合法");
        }

        if (levelService.selectById(customerLevelId) == null) {
            return Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);
        }
        if (originService.selectById(customerOriginId) == null) {
            return Result.error(CodeMsg.CUSTOMER_ORIGIN_FAILURE);
        }
        if (stageService.selectById(customerStageId) == null) {
            return Result.error(CodeMsg.CUSTOMER_ORIGIN_FAILURE);
        }

        Customer beforeCustomer = customerService.selectById(customerOp.getId());
        if (beforeCustomer == null) {
            return Result.error(CodeMsg.CUSTOMER_FAILURE);
        }
        Long beStageId = beforeCustomer.getCustomerStageId();
        Long afStageId = customerOp.getCustomerStageId();

        //先更新客户信息
        customerOp.setModifyUserId(user.getId());
        customerOp.setModifyTime(LocalDateTime.now());
        if (customerService.update(customerOp) == 0) {
            return Result.error(CodeMsg.CUSTOMER_UPDATE_FAILURE);
        }
        //再判断是否有更新阶段信息 如果有则新增日志记录
        if (!beStageId.equals(afStageId) && afStageId != null) {
            return stageLogService.createForUpdate(new CustomerStageLog(), beStageId, afStageId, user.getId(), customerOp.getId()) > 0 ?
                    Result.success(null) : Result.error(CodeMsg.CUSTOMER_STAGE_LOG_FAILURE);
        }

        return Result.success(null);
    }

    @ApiOperation(value = "客户转移 把前员工负责的所有客户转移到后员工上")
    @PutMapping("transfer")
    public Result transfer(User user, @RequestParam("before") Long before, @RequestParam("after") Long after) {
        int arrange = followArrangeService.transferCustomer(before, after);
        int plan = followPlanService.transferCustomer(before, after);
        int follow = followService.transferCustomer(before, after);
        int order = orderBaseService.updateAllByEmployeeId(before, after, user);
        int complaint = complaintService.transferComplaint(before, after);
        int customer = customerService.transferCustomer(before, after);
        int sum = arrange + plan + follow + order + complaint + customer;
        if (sum == 0) {
            Result.error(new CodeMsg("跟进记录数为0"));
        }

        UserVo beUser = userService.selectById(before);
        UserVo afUser = userService.selectById(after);
        if (Objects.isNull(beUser)) {
            return Result.error(new CodeMsg("找不到前员工信息"));
        }
        if (Objects.isNull(afUser)) {
            return Result.error(new CodeMsg("找不到后员工信息"));
        }
        String arrangeContent = beUser.getName() + "把员工转移给你 时间" + LocalDateTime.now();

        Message message = new Message();
        message.setFromId(before);
        message.setToId(after);
        message.setReaded((byte) 0);
        message.setMsgTitle("员工转移信息");
        message.setMsgContent(arrangeContent);
        message.setSendTime(LocalDateTime.now());

        return messageService.sendMessage(message) > 0 ?
                Result.success(null) : Result.error(new CodeMsg("Fail to send message!"));
    }

    @ApiOperation(value = "新增客户记录并同时创建客户联系人记录（已测通过）", notes = "")
    @PostMapping("create")
    public Result create(User user, @RequestBody CustomerRelationVo customerRelationVo) {

        if (levelService.selectById(customerRelationVo.getCustomerLevelId()) == null) {
            return Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);
        }
        if (originService.selectById(customerRelationVo.getCustomerOriginId()) == null) {
            return Result.error(CodeMsg.CUSTOMER_ORIGIN_FAILURE);
        }
        if (stageService.selectById(customerRelationVo.getCustomerStageId()) == null) {
            return Result.error(CodeMsg.CUSTOMER_ORIGIN_FAILURE);
        }

        Customer customer = new Customer();
        Relation relation = new Relation();
        BeanUtils.copyProperties(customerRelationVo, customer);
        BeanUtils.copyProperties(customerRelationVo, relation);

        createAction(customer, user);
        if (customerService.create(customer) == 0) {
            return Result.error(CodeMsg.CUSTOMER_FAILURE);
        }

        relation.setRelationPrimary((byte) 0);
        relation.setCustomerId(customer.getId());
        relation.setCreateTime(LocalDateTime.now());

        return relationService.insert(relation) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);
    }

    @ApiOperation(value = "删除客户(慎重）", notes = "已测试通过")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id) {
        List<Relation> relationList = relationService.selectByCustomId(id);
        if (relationList.size() == 0) {
            return Result.error(new CodeMsg("该客户没有联系人"));
        }
        List<Long> relationIds = relationList.stream().map(Relation::getId).collect(Collectors.toList());
        int order = 0;
        for (Relation relation : relationList) {
            order += orderBaseService.deleteAllByRelationId(relation.getId());
        }
        int complain = complaintService.deleteByCustomerId(id);
        int followArrange = followArrangeService.deleteByRelationIds(relationIds);
        int followPlan = followPlanService.deleteByRelationIds(relationIds);
        int follow = followService.deleteByCustomerId(id);
        int customer = customerService.deleteCustomerById(id);
        int sum = order + complain + followArrange + follow + followPlan + followArrange + customer;

        return sum > 0 ? Result.success(sum) : Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);

    }

    @ApiOperation(value = "批量删除客户(慎重）", notes = "已测试通过")
    @DeleteMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<Long> ids) {
        StringBuffer failStr = new StringBuffer();
        StringBuffer successStr = new StringBuffer();

        for (long id : ids) {
            Customer customer1 = customerService.selectById(id);
            if (null == customer1 || Objects.isNull(customer1.getCustomerName())) {
                failStr.append("找不到该客户信息").append(id).append("\n");
                continue;
            }
            List<Relation> relationList = relationService.selectByCustomId(id);
            if (relationList.size() == 0) {
                failStr.append("该客户没有联系人").append(customer1.getCustomerName()).append("\n");
                continue;
            }
            List<Long> relationIds = relationList.stream().map(Relation::getId).collect(Collectors.toList());
            int order = 0;
            for (Relation relation : relationList) {
                order += orderBaseService.deleteAllByRelationId(relation.getId());
            }
            int complain = complaintService.deleteByCustomerId(id);
            int followArrange = followArrangeService.deleteByRelationIds(relationIds);
            int followPlan = followPlanService.deleteByRelationIds(relationIds);
            int follow = followService.deleteByCustomerId(id);
            int customer = customerService.deleteCustomerById(id);
            int sum = order + complain + followArrange + follow + followPlan + followArrange + customer;

            if (sum > 0) {
                successStr.append("删除客户信息成功:").append(customer1.getCustomerName()).append("\n");
            } else {
                failStr.append("删除客户信息失败:").append(customer1.getCustomerName()).append("\n");
            }
        }

        return failStr.length() == 0 ? Result.success(successStr)
                : Result.error(new CodeMsg("Error message:" + failStr + "\nSuccess message:" + successStr));
    }

    @ApiOperation(value = "修改客户的客户等级", notes = "")
    @PutMapping("updateLevel")
    public Result updateLevel(User user, @RequestParam("Cid") Long cid, @RequestParam("Lid") Long lid) {
        Customer customer = customerService.selectById(cid);
        if (customer == null) {
            return Result.error(CodeMsg.CUSTOMER_FAILURE);
        }
        customer.setCustomerLevelId(lid);
        updateAction(customer, user);

        return customerService.update(customer) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_UPDATE_FAILURE);
    }


    @ApiOperation(value = "修改客户的客户来源", notes = "")
    @PutMapping("updateOrigin")
    public Result updateOrigin(User user, @RequestParam("Cid") Long cid, @RequestParam("Oid") Long oid) {
        Customer customer = customerService.selectById(cid);
        if (customer == null) {
            return Result.error(CodeMsg.CUSTOMER_FAILURE);
        }
        customer.setCustomerOriginId(oid);
        updateAction(customer, user);

        return customerService.update(customer) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_UPDATE_FAILURE);
    }


    @ApiOperation(value = "修改客户的客户状态", notes = "")
    @PutMapping("updateState")
    public Result updateState(User user, @RequestParam("Cid") Long cid, @RequestParam("state") String state) {
        Customer customer = customerService.selectById(cid);
        if (customer == null) {
            return Result.error(CodeMsg.CUSTOMER_FAILURE);
        }
        customer.setCustomerState(state);
        updateAction(customer, user);

        return customerService.update(customer) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_UPDATE_FAILURE);
    }


    @ApiOperation(value = "修改客户的客户阶段", notes = "")
    @PutMapping("updateStage")
    public Result updateStage(User user, @RequestParam("Cid") Long cid, @RequestParam("Sid") Long sid) {
        Customer customer = customerService.selectById(cid);
        if (customer == null) {
            return Result.error(CodeMsg.CUSTOMER_FAILURE);
        }
        Long beforeStageId = customer.getCustomerStageId();
        customer.setCustomerStageId(sid);
        updateAction(customer, user);

        if (customerService.update(customer) == 0) {
            return Result.error(CodeMsg.CUSTOMER_UPDATE_FAILURE);
        }

        //再判断是否有更新阶段信息 如果有则新增日志记录
        if (!beforeStageId.equals(sid) && sid != null) {
            return stageLogService.createForUpdate(new CustomerStageLog(), beforeStageId, sid, user.getId(), customer.getId()) > 0 ?
                    Result.success(null) : Result.error(CodeMsg.CUSTOMER_STAGE_LOG_FAILURE);
        } else {
            return Result.success(null);
        }
    }

    @ApiOperation(value = "根据客户id获取客户详细资料（", notes = "")
    @GetMapping("detail")
    public Result detail(@RequestParam("id") Long id) {

        return Result.success(customerService.selectDetail(id));

    }

    @ApiOperation(value = "获取所有的客户id和客户名称", notes = "")
    @GetMapping("IdAndNameAll")
    public Result idAndNameAll() {

        return Result.success(customerService.selectIdAndNameAll());
    }

    @ApiOperation(value = "把客户和客户联系人的Excel表数据导入到数据库(测试通过)", notes = "批量导入")
    @PostMapping("/import")
    public Result importExcel(User user, MultipartFile file) throws Exception {
        if (null == file) {
            return Result.error(new CodeMsg("文件读取错误, 无效的文件!"));
        }

        List<Object> list = ExcelUtil.readExcel(file, new CustomerImportVo());

        if (null == list || list.size() == 0) {
            return Result.error(new CodeMsg("文件数据为空!"));
        }

        List<Customer> customerList = new ArrayList<>();
        List<Relation> relationList = new ArrayList<>();
        List<CustomerStage> customerStages = stageService.selectAll();
        List<CustomerLevel> customerLevels = levelService.selectAll();
        List<CustomerOrigin> customerOrigins = originService.selectAll();
        List<String> stages = stageService.selectAll().stream().map(CustomerStage::getStageName).collect(Collectors.toList());
        List<String> levels = levelService.selectAll().stream().map(CustomerLevel::getLevelName).collect(Collectors.toList());
        List<String> origins = originService.selectAll().stream().map(CustomerOrigin::getOriginName).collect(Collectors.toList());

        for (int i = 1; i <= list.size(); i++) {
            CustomerImportVo importVo = (CustomerImportVo) list.get(i - 1);

            String customerName = importVo.getCustomerName();
            if (Objects.isNull(customerName) || customerName.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户名称为空"));
            }
            String state = importVo.getCustomerState();
            if (Objects.isNull(state) || state.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户状态为空"));
            }
            String address = importVo.getCustomerAddress();
            if (Objects.isNull(address) || address.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户地址为空"));
            }
            String introduce = importVo.getCustomerIntroduce();
            if (Objects.isNull(introduce) || introduce.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户介绍为空"));
            }

            String customerStage = importVo.getCustomerStage();
            int index = stages.indexOf(customerStage);
            if (index == -1) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户阶段不存在"));
            }
            importVo.setCustomerStageId(customerStages.get(index).getId());

            String customerLevel = importVo.getCustomerLevel();
            index = levels.indexOf(customerLevel);
            if (index == -1) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户等级不存在"));
            }
            importVo.setCustomerLevelId(customerLevels.get(index).getId());

            String customerOrigin = importVo.getCustomerOrigin();
            index = origins.indexOf(customerOrigin);
            if (index == -1) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户来源不存在"));
            }
            importVo.setCustomerOriginId(customerOrigins.get(index).getId());

            Customer customer = new Customer();
            BeanUtils.copyProperties(importVo, customer);
            customer.setCreateTime(LocalDateTime.now());
            customer.setCreateUserId(user.getId());
            customer.setEmployeeId(user.getId());
            customerList.add(customer);
            //客户信息获取完成，下面获取客户联系人信息

            String relationCall = importVo.getRelationCall();
            if (Objects.isNull(relationCall) || relationCall.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户联系人称呼为空"));
            }
            String relationName = importVo.getRelationName();
            if (Objects.isNull(relationName) || relationName.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户联系人姓名为空"));
            }
            String relationSex = importVo.getSex();
            if (Objects.isNull(relationSex) || relationSex.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户联系人性别为空"));
            }
            if ("男".equals(relationSex)) {
                importVo.setRelationSex(false);
            } else {
                importVo.setRelationSex(true);
            }
            String relationPosition = importVo.getRelationPosition();
            if (Objects.isNull(relationPosition) || relationPosition.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户联系人职位为空"));
            }
            String landlineNumber = importVo.getLandlineNumber();
            if (Objects.isNull(landlineNumber) || landlineNumber.isEmpty()) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户联系人座机号为空"));
            }

            String relationPhone = importVo.getRelationPhone();
            String phoneRegex = "^1[3|4|5|8][0-9]\\d{8}$";
            boolean phoneIsMatche = relationPhone.matches(phoneRegex);
            if (!phoneIsMatche) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因:客户联系人手机号码不合法"));
            }

            String relationEmail = importVo.getRelationEmail();
            String emailRegex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
            boolean emailIsMatche = relationEmail.matches(emailRegex);
            if (!emailIsMatche) {
                return Result.error(new CodeMsg("第" + i + "行出错" + " 出错原因: 客户联系人邮箱不合法"));
            }

            Relation relation = new Relation();
            BeanUtils.copyProperties(importVo, relation);
            relation.setRelationPrimary((byte) 0);
            relation.setCreateTime(LocalDateTime.now());
            relation.setCreateUserId(user.getId());
            relationList.add(relation);

        }

        customerService.importExcel(customerList, relationList);

        return Result.success(CodeMsg.SUCCESS);
    }

    @GetMapping("/downloadExcelTemplate")
    @ApiOperation(value = "下载客户导入模板需要在浏览器中访问测试(测试通过)")
    public ResponseEntity<byte[]> downloadExcelTemplate() throws Exception {
        try {
            File file = ResourceUtils.getFile("classpath:excelTemplate/客户导入模板.xls");
            String fileName = file.getName();
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            throw new Exception("读取模板文件出错或不存在!");
        }
    }

    private static void updateAction(Customer customer, User user) {
        customer.setModifyUserId(user.getId());
        customer.setModifyTime(LocalDateTime.now());
    }

    private static void createAction(Customer customer, User user) {
        customer.setCreateUserId(user.getId());
        customer.setCreateTime(LocalDateTime.now());
    }


    @ApiOperation(value = "根据类别统计客户数目（1.按阶段，2.按等级，3.按来源，4.按所在城市）", notes = "已测")
    @GetMapping("/statisticalByShowType")
    public Result statisticalByShowType(@RequestParam(value = "type", defaultValue = "1", required = false) int ShowType) {
        List<StatisticsVo> statisticsVoList = null;
        statisticsVoList = customerService.statisticalCountByShowType(ShowType);
        return statisticsVoList == null ?
                Result.error(CodeMsg.ERROR) : Result.success(statisticsVoList);
    }

}
