package com.myself.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jack Zhou
 */
@Document(collection = "message_log")
@Data
public class MessageLogDO implements Serializable {

    @Id
    private String id;

    @Field(name = "message_context")
    private String messageContext;

    private Integer type;

    @Field(name = "create_time")
    private Date createTime;

    @Field(name="update_time")
    private Date updateTime;

    @Field(name="has_dog")
    private Boolean hasDog;
}
