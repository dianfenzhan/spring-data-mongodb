package com.myself.mongodb.service.impl;

import com.myself.mongodb.entity.MessageLogDO;
import com.myself.mongodb.service.IMessageLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

/**
 * 使用 mongodbTemplate操作mongo
 *
 * @author Jack Zhou
 */
@Service
@Slf4j
public class MessageLogServiceNewImpl implements IMessageLogService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(MessageLogDO messageLogDO) {
        messageLogDO.setCreateTime(new Date());
        messageLogDO.setUpdateTime(new Date());
        mongoTemplate.insert(messageLogDO);
        //mongoTemplate.save(messageLogDO);
    }

    @Override
    public void update(MessageLogDO messageLogDO) {
        //单数据修改
        mongoTemplate.save(messageLogDO);
        //另外一种方式修改
        Update update = new Update();
        update.set("type", 1);
        update.set("messageContext", "22");
        Query query = new Query(Criteria.where("type").is(2));
        //批量修改
        mongoTemplate.updateMulti(query, update, MessageLogDO.class);
        //修改一条
        mongoTemplate.updateFirst(query, update, MessageLogDO.class);
    }

    @Override
    public void delete(String id) {
        //删除主键
        MessageLogDO messageLogDO = mongoTemplate.findById(id, MessageLogDO.class);
        mongoTemplate.remove(messageLogDO);
        //删除查询到的数据
        Query query = new Query(Criteria.where("type").is(2));
        mongoTemplate.remove(query, MessageLogDO.class);
    }

    @Override
    public MessageLogDO findById(String id) {
        //查询主键
        MessageLogDO messageLogDO = mongoTemplate.findById(id, MessageLogDO.class);
        //查询其他的数据
        Query query = new Query(Criteria.where("type").is(2));
        MessageLogDO messageLogDO1 = mongoTemplate.findOne(query, MessageLogDO.class);
        return messageLogDO;
    }

    @Override
    public List<MessageLogDO> findAll() {
        //找到所有的
        List<MessageLogDO> list1 = mongoTemplate.findAll(MessageLogDO.class);
        //单条件查询
        Pattern pattern2 = compile("^.*8$", CASE_INSENSITIVE);
        Query query2 = new Query(Criteria.where("phone").regex(pattern2));
        //排序
        query2.with(Sort.by(Sort.Direction.ASC, "type"));
        //查询多个
        List<MessageLogDO> list2 = mongoTemplate.find(query2, MessageLogDO.class);
        //查询单个
        MessageLogDO messageLogDO2 = mongoTemplate.findOne(query2, MessageLogDO.class);
        //复杂条件查询
        Query query3 = new Query();
        //方法一--不推荐
       /* query3.addCriteria(Criteria.where("type").is(22));
        Pattern pattern3 = compile("^.*8$", CASE_INSENSITIVE);
        query3.addCriteria(Criteria.where("messageContext").regex(pattern3));*/
        //方法2-- 推荐
        Criteria criteria1 = Criteria.where("type").is(22).andOperator();
        Criteria criteria2 = Criteria.where("messageContext").is("jack").andOperator();
        Pattern pattern32 = compile("^.*8$", CASE_INSENSITIVE);
        Criteria criteria3 = Criteria.where("messageContext").regex(pattern32);
        //3 or (1 and 2)
        query3.addCriteria(criteria3.orOperator(criteria1.andOperator(criteria1)));
        //多排序
        Sort.Order order31 = new Sort.Order(Sort.Direction.DESC, "type");
        Sort.Order order32 = new Sort.Order(Sort.Direction.ASC, "messageContext");
        query3.with(Sort.by(order31, order32));
        List<MessageLogDO> list3 = mongoTemplate.find(query3, MessageLogDO.class);
        return null;
    }

    @Override
    public List<MessageLogDO> findByPage() {
        //查询条件
        Query query = new Query(Criteria.where("type").is(2));
        //分页
        PageRequest page = PageRequest.of(0, 10);
        query.with(page);
        //排序
        query.with(Sort.by(Sort.Direction.ASC, "type"));
        Long count = mongoTemplate.count(query, MessageLogDO.class);
        List<MessageLogDO> list = mongoTemplate.find(query, MessageLogDO.class);
        //组装分页模型
        return null;
    }

    @Override
    public List<MessageLogDO> findByName() {
        return null;
    }
}
