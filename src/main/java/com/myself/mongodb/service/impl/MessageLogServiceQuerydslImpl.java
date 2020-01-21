package com.myself.mongodb.service.impl;

import com.google.common.collect.Lists;
import com.myself.mongodb.dao.MessageLogRepository;
import com.myself.mongodb.entity.MessageLogDO;
import com.myself.mongodb.entity.QMessageLogDO;
import com.myself.mongodb.service.IMessageLogService;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用QueryDsl操作数据
 *
 * @author Jack Zhou
 */
@Service
public class MessageLogServiceQuerydslImpl implements IMessageLogService {

    @Autowired
    private MessageLogRepository messageLogRepository;

    @Override
    public void add(MessageLogDO messageLogDO) {

    }

    @Override
    public void update(MessageLogDO messageLogDO) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public MessageLogDO findById(String id) {
        QMessageLogDO qMessageLogDO = QMessageLogDO.messageLogDO;
        Predicate predicate = qMessageLogDO.id.eq("1");
        MessageLogDO messageLogDO = messageLogRepository.findOne(predicate).orElse(null);
        return null;
    }

    @Override
    public List<MessageLogDO> findAll() {
        QMessageLogDO qMessageLogDO = QMessageLogDO.messageLogDO;
        Predicate predicate1 = qMessageLogDO.id.eq("1").and(qMessageLogDO.messageContext.matches(""));
        Predicate predicate2 = qMessageLogDO.type.eq(2);
        Sort sort = Sort.by(Sort.Direction.ASC, "type");
        Iterable<MessageLogDO> iterable = messageLogRepository.findAll(((BooleanExpression) predicate1).or(predicate2), sort);
        List<MessageLogDO> list = Lists.newArrayList(iterable);
        return list;
    }

    @Override
    public List<MessageLogDO> findByPage() {
        QMessageLogDO qMessageLogDO = QMessageLogDO.messageLogDO;
        Predicate predicate1 = qMessageLogDO.id.eq("1").and(qMessageLogDO.messageContext.like("aa"));
        Predicate predicate2 = qMessageLogDO.type.eq(2);
        Sort sort = Sort.by(Sort.Direction.ASC, "type");
        PageRequest page = PageRequest.of(0, 10, sort);
        messageLogRepository.findAll(((BooleanExpression) predicate1).or(predicate2), page);
        return null;
    }

    @Override
    public List<MessageLogDO> findByName() {
        QMessageLogDO qMessageLogDO = QMessageLogDO.messageLogDO;
        //模糊匹配
        Predicate predicate = qMessageLogDO.messageContext.matches("rose1");
        Iterable<MessageLogDO> iterable = messageLogRepository.findAll(predicate);
        List<MessageLogDO> list = Lists.newArrayList(iterable);
        return list;
    }
}
