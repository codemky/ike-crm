package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.mq.MQSender;
import com.ike.pojo.Message;
import com.ike.pojo.User;
import com.ike.pojo.vo.MessageVo;
import com.ike.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @Author wgm
 * @Date 2019/10/9 11:26
 */
@RestController
@Api(description = "消息模块")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MQSender mqSender;

    @ApiOperation(value = "查询当前用户全部消息记录")
    @GetMapping("listAll")
    public Result listAll(User user,
                          @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if ( user == null ) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        Page<MessageVo> page = new Page<>(pageNum, pageSize);
        IPage<MessageVo> voPage = messageService.selAllByCurUserId(page, user.getId());
        return Result.success(voPage);
    }

    @ApiOperation(value = "查询当前用户未读消息")
    @GetMapping("listUnRead")
    public Result listUnRead(User user,
                             @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        if ( user == null ) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        Page<MessageVo> page = new Page<>(pageNum, pageSize);
        IPage<MessageVo> voPage = messageService.selUnReadCurUserId(page, user.getId());
        return Result.success(voPage);
    }

    @ApiOperation(value = "查询当前用户已读消息")
    @GetMapping("listIsRead")
    public Result listIsRead(User user,
                             @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        if ( user == null ) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        Page<MessageVo> page = new Page<>(pageNum, pageSize);
        IPage<MessageVo> voPage = messageService.selIsReadByCurUserId(page, user.getId());
        return Result.success(voPage);
    }

    @ApiOperation(value = "根据id删除记录")
    @DeleteMapping("delete")
    public Result delete(@ApiParam(name = "id", value = "信息id") @RequestParam Long id) {
        return messageService.deleteById(id) > 0
                ?  Result.success(null) : Result.error();
    }

    @ApiOperation(value = "根据id批量删除记录")
    @DeleteMapping("batchDelete")
    public Result batchDelete(@ApiParam(name = "ids", value = "信息id集合") @RequestBody List<Long> ids) {
        return messageService.batchDelete(ids) > 0
                ?  Result.success(null) : Result.error();
    }

    @ApiOperation(value = "根据id批量置为已读")
    @PutMapping("batchToRead")
    public Result batchToRead(@ApiParam(name = "ids", value = "信息id集合") @RequestBody List<Long> ids) {
        return messageService.batchToRead(ids) > 0
                ?  Result.success(null) : Result.error();
    }

    @ApiOperation(value = "根据id批量置为未读")
    @PutMapping("batchToUnRead")
    public Result batchToUnRead(@ApiParam(name = "ids", value = "信息id集合") @RequestBody List<Long> ids) {
        return messageService.batchToUnRead(ids) > 0
                ? Result.success(null) : Result.error();
    }

    @ApiOperation(value = "测试：发送内部消息,只需在完成业务调用MQSender.sendMsgMessage(msg)发送相应内部消息即可")
    @PostMapping("send")
    public void sendInnerMsg(){
        Message message = new Message();
        message.setFromId(1L);
        message.setToId(1L);
        message.setMsgTitle("消息队列测试标题");
        message.setMsgContent("消息队列测试内容");
        message.setReaded(((byte) 0));
        message.setSendTime(LocalDateTime.now());
        message.setReadTime(null);
       mqSender.sendMsgMessage(message);
    }
}
