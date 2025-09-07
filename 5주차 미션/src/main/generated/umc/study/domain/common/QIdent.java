package umc.study.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdent is a Querydsl query type for Ident
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIdent extends EntityPathBase<Ident> {

    private static final long serialVersionUID = 1885303279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIdent ident = new QIdent("ident");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final EnumPath<umc.study.domain.enums.SocialType> type = createEnum("type", umc.study.domain.enums.SocialType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QIdent(String variable) {
        this(Ident.class, forVariable(variable), INITS);
    }

    public QIdent(Path<? extends Ident> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIdent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIdent(PathMetadata metadata, PathInits inits) {
        this(Ident.class, metadata, inits);
    }

    public QIdent(Class<? extends Ident> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

