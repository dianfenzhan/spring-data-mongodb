package com.myself.mongodb.dao;

import com.myself.mongodb.entity.MessageLogDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @author Jack Zhou
 */
public interface MessageLogRepository extends MongoRepository<MessageLogDO, String>, QuerydslPredicateExecutor<MessageLogDO> {

    /**
     * jpa 的按名字查询
     * @param type
     * @return
     */
    List<MessageLogDO> findByType(Integer type);
}
