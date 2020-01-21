package com.myself.mongodb.controller;

import com.myself.mongodb.entity.MessageLogDO;
import com.myself.mongodb.service.IMessageLogService;
import com.myself.mongodb.service.impl.MessageLogServiceQuerydslImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jack Zhou
 */
@RestController
@RequestMapping("/message")
@Api(tags = "Message Log")
public class MongoMessageLogController {

    @Resource(type= MessageLogServiceQuerydslImpl.class)
    private IMessageLogService messageLogService;

    @PostMapping
    @ApiOperation(value = "create a message log and put into the mongodb")
    public void createMongoLog(@RequestBody MessageLogDO messageLogDO) {
        messageLogService.add(messageLogDO);
    }

    @PutMapping
    @ApiOperation(value = "update message log")
    public void updateMongoLog(@RequestBody MessageLogDO messageLogDO) {
        messageLogService.update(messageLogDO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete a record")
    public void deletebyId(@PathVariable String id) {
        messageLogService.delete(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find a record by id")
    public MessageLogDO findById(@PathVariable String id) {
        MessageLogDO messageLogDO = messageLogService.findById(id);
        return messageLogDO;
    }

    @GetMapping("/findall")
    public List<MessageLogDO> findList(){
        return messageLogService.findAll();
    }

    @GetMapping("/findpage")
    public List<MessageLogDO> findPage(){
        return messageLogService.findByPage();
    }

    @GetMapping("/findname")
    public List<MessageLogDO> findName(){
        return messageLogService.findByName();
    }

}
