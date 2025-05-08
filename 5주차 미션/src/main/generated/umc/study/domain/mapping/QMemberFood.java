package umc.study.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberFood is a Querydsl query type for MemberFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberFood extends EntityPathBase<MemberFood> {

    private static final long serialVersionUID = -1103382598L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberFood memberFood = new QMemberFood("memberFood");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final umc.study.domain.common.QFood food;

    public final umc.study.domain.common.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberFood(String variable) {
        this(MemberFood.class, forVariable(variable), INITS);
    }

    public QMemberFood(Path<? extends MemberFood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberFood(PathMetadata metadata, PathInits inits) {
        this(MemberFood.class, metadata, inits);
    }

    public QMemberFood(Class<? extends MemberFood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new umc.study.domain.common.QFood(forProperty("food")) : null;
        this.member = inits.isInitialized("member") ? new umc.study.domain.common.QMember(forProperty("member")) : null;
    }

}

