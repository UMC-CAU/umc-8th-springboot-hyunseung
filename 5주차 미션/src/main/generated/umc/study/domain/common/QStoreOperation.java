package umc.study.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreOperation is a Querydsl query type for StoreOperation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreOperation extends EntityPathBase<StoreOperation> {

    private static final long serialVersionUID = -342022041L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreOperation storeOperation = new QStoreOperation("storeOperation");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> openTime = createTime("openTime", java.time.LocalTime.class);

    public final QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final EnumPath<java.time.DayOfWeek> week = createEnum("week", java.time.DayOfWeek.class);

    public QStoreOperation(String variable) {
        this(StoreOperation.class, forVariable(variable), INITS);
    }

    public QStoreOperation(Path<? extends StoreOperation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreOperation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreOperation(PathMetadata metadata, PathInits inits) {
        this(StoreOperation.class, metadata, inits);
    }

    public QStoreOperation(Class<? extends StoreOperation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

