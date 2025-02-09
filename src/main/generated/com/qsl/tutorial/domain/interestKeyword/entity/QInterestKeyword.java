package com.qsl.tutorial.domain.interestKeyword.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInterestKeyword is a Querydsl query type for InterestKeyword
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestKeyword extends EntityPathBase<InterestKeyword> {

    private static final long serialVersionUID = 1639914734L;

    public static final QInterestKeyword interestKeyword = new QInterestKeyword("interestKeyword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QInterestKeyword(String variable) {
        super(InterestKeyword.class, forVariable(variable));
    }

    public QInterestKeyword(Path<? extends InterestKeyword> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInterestKeyword(PathMetadata metadata) {
        super(InterestKeyword.class, metadata);
    }

}

