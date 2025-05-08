package umc.study.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointExchange is a Querydsl query type for PointExchange
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointExchange extends EntityPathBase<PointExchange> {

    private static final long serialVersionUID = -360703790L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointExchange pointExchange = new QPointExchange("pointExchange");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> cmtTime = createDateTime("cmtTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> reqTime = createDateTime("reqTime", java.time.LocalDateTime.class);

    public final EnumPath<umc.study.domain.enums.PointExchangeStatus> status = createEnum("status", umc.study.domain.enums.PointExchangeStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPointExchange(String variable) {
        this(PointExchange.class, forVariable(variable), INITS);
    }

    public QPointExchange(Path<? extends PointExchange> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointExchange(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointExchange(PathMetadata metadata, PathInits inits) {
        this(PointExchange.class, metadata, inits);
    }

    public QPointExchange(Class<? extends PointExchange> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

