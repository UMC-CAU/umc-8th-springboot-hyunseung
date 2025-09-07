package umc.study.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMissionMember is a Querydsl query type for MissionMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMissionMember extends EntityPathBase<MissionMember> {

    private static final long serialVersionUID = -1622664604L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMissionMember missionMember = new QMissionMember("missionMember");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> clearTime = createDateTime("clearTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final umc.study.domain.common.QMember member;

    public final umc.study.domain.common.QMission mission;

    public final DateTimePath<java.time.LocalDateTime> reqTime = createDateTime("reqTime", java.time.LocalDateTime.class);

    public final EnumPath<umc.study.domain.enums.MissionStatus> status = createEnum("status", umc.study.domain.enums.MissionStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMissionMember(String variable) {
        this(MissionMember.class, forVariable(variable), INITS);
    }

    public QMissionMember(Path<? extends MissionMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMissionMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMissionMember(PathMetadata metadata, PathInits inits) {
        this(MissionMember.class, metadata, inits);
    }

    public QMissionMember(Class<? extends MissionMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new umc.study.domain.common.QMember(forProperty("member")) : null;
        this.mission = inits.isInitialized("mission") ? new umc.study.domain.common.QMission(forProperty("mission"), inits.get("mission")) : null;
    }

}

