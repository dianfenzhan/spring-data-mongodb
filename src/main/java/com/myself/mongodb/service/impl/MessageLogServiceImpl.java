package com.myself.mongodb.service.impl;

import com.myself.mongodb.dao.MessageLogRepository;
import com.myself.mongodb.entity.MessageLogDO;
import com.myself.mongodb.service.IMessageLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用spring data mongo的接口
 *
 * @author Jack Zhou
 */
@Service
@Slf4j
public class MessageLogServiceImpl implements IMessageLogService {

    @Autowired
    private MessageLogRepository messageLogRepository;


    @Override
    public void add(MessageLogDO messageLogDO) {
        messageLogDO.setId(UUID.randomUUID().toString());
        messageLogDO.setCreateTime(new Date());
        messageLogDO.setUpdateTime(new Date());
        messageLogRepository.insert(messageLogDO);
    }

    @Override
    public void update(MessageLogDO messageLogDO) {
        messageLogRepository.save(messageLogDO);
    }

    @Override
    public void delete(String id) {
        messageLogRepository.deleteById(id);
    }

    @Override
    public MessageLogDO findById(String id) {
        return messageLogRepository.findById(id).orElse(null);
    }

    @Override
    public List<MessageLogDO> findAll() {
        //所有的
        List<MessageLogDO> listAll1 = messageLogRepository.findAll();
        //带条件的
        MessageLogDO messageLogDO2 = new MessageLogDO();
        messageLogDO2.setId("64b403b3-c4d1-409a-813f-511e1070fc4c");
        Example<MessageLogDO> example2 = Example.of(messageLogDO2);
        List<MessageLogDO> listAll2 = messageLogRepository.findAll(example2);
        //复杂条件的
        MessageLogDO messageLogDO3 = new MessageLogDO();
        messageLogDO3.setMessageContext("ROse123");
        messageLogDO3.setType(221);
        ExampleMatcher matcher = ExampleMatcher.matching()
                //以此开头并且忽略大小写
                .withMatcher("messageContext", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase())
                //包含
                //.withMatcher("messageContext", ExampleMatcher.GenericPropertyMatchers.contains())
                //以此结尾
                //.withMatcher("note",ExampleMatcher.GenericPropertyMatchers.endsWith())
                //忽略属性不管type是什么值都不加入查询条件
                .withIgnorePaths("type");
        Example<MessageLogDO> example3 = Example.of(messageLogDO3, matcher);
        Long count = messageLogRepository.count(example3);
        List<MessageLogDO> listAll3 = messageLogRepository.findAll(example3);
        return null;
    }

    @Override
    public List<MessageLogDO> findByPage() {
        int page = 0;
        int size = 10;
        //排序
        //查询条件
        MessageLogDO messageLogDO2 = new MessageLogDO();
        messageLogDO2.setMessageContext("rose");
        Example<MessageLogDO> example2 = Example.of(messageLogDO2);
        //分页参数
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("type")));
        Page<MessageLogDO> pageObject = messageLogRepository.findAll(pageRequest);
        int totalPage = pageObject.getTotalPages();
        Long count = pageObject.getTotalElements();
        List<MessageLogDO> list = pageObject.getContent();
        return list;
    }

    @Override
    public List<MessageLogDO> findByName() {
        return messageLogRepository.findByType(2);
    }
}
