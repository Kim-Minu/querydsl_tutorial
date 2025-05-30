package com.qsl.tutorial.domain.interest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInterest is a Querydsl query type for Interest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterest extends EntityPathBase<Interest> {

    private static final long serialVersionUID = -1121754180L;

    public static final QInterest interest = new QInterest("interest");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QInterest(String variable) {
        super(Interest.class, forVariable(variable));
    }

    public QInterest(Path<? extends Interest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInterest(PathMetadata metadata) {
        super(Interest.class, metadata);
    }

}

