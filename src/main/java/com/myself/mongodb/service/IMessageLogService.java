package com.myself.mongodb.service;

import com.myself.mongodb.entity.MessageLogDO;

import java.util.List;


/**
 * @author Jack Zhou
 */
public interface IMessageLogService {
    /**
     * add
     * @param messageLogDO
     */
    void add(MessageLogDO messageLogDO);

    /**
     * update
     * @param messageLogDO
     */
    void update(MessageLogDO messageLogDO);

    /**
     * delete by iod
     * @param id
     */
    void delete(String id);

    /**
     * query by id
     * @param id
     * @return
     */
    MessageLogDO findById(String id);

    /**
     * 查询
     * 1.所有的
     * 2.简单查询
     * 3.复杂查询
     * 5.分页查询 带条件，排序
     * @return
     */
    List<MessageLogDO> findAll();

    /**
     * 分页查询
     * @return
     */
    List<MessageLogDO> findByPage();

    /**
     * spring data jpa的按名字查询
     * @return
     */
    List<MessageLogDO> findByName();

}

