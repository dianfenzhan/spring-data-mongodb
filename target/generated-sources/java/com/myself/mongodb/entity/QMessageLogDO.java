package com.myself.mongodb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessageLogDO is a Querydsl query type for MessageLogDO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMessageLogDO extends EntityPathBase<MessageLogDO> {

    private static final long serialVersionUID = 1659524462L;

    public static final QMessageLogDO messageLogDO = new QMessageLogDO("messageLogDO");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final BooleanPath hasDog = createBoolean("hasDog");

    public final StringPath id = createString("id");

    public final StringPath messageContext = createString("messageContext");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QMessageLogDO(String variable) {
        super(MessageLogDO.class, forVariable(variable));
    }

    public QMessageLogDO(Path<? extends MessageLogDO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessageLogDO(PathMetadata metadata) {
        super(MessageLogDO.class, metadata);
    }

}

