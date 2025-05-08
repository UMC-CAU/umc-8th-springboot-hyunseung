package umc.study.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1569473925L;

    public static final QMember member = new QMember("member1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final BooleanPath alive = createBoolean("alive");

    public final DatePath<java.time.LocalDate> birthdate = createDate("birthdate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath detailAddress = createString("detailAddress");

    public final StringPath email = createString("email");

    public final ListPath<umc.study.domain.mapping.MemberFood, umc.study.domain.mapping.QMemberFood> foods = this.<umc.study.domain.mapping.MemberFood, umc.study.domain.mapping.QMemberFood>createList("foods", umc.study.domain.mapping.MemberFood.class, umc.study.domain.mapping.QMemberFood.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Ident, QIdent> idents = this.<Ident, QIdent>createList("idents", Ident.class, QIdent.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<umc.study.domain.enums.Gender> sex = createEnum("sex", umc.study.domain.enums.Gender.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

