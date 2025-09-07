package umc.study.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentPicture is a Querydsl query type for CommentPicture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentPicture extends EntityPathBase<CommentPicture> {

    private static final long serialVersionUID = 293328449L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentPicture commentPicture = new QCommentPicture("commentPicture");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    public final umc.study.domain.common.QComment comment;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCommentPicture(String variable) {
        this(CommentPicture.class, forVariable(variable), INITS);
    }

    public QCommentPicture(Path<? extends CommentPicture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentPicture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentPicture(PathMetadata metadata, PathInits inits) {
        this(CommentPicture.class, metadata, inits);
    }

    public QCommentPicture(Class<? extends CommentPicture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new umc.study.domain.common.QComment(forProperty("comment"), inits.get("comment")) : null;
    }

}

