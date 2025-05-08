package umc.study.domain.common;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.mapping.CommentPicture;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(callSuper = true)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false)
    private byte point;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ToString.Exclude
    @OneToMany(mappedBy = "comment")
    private List<CommentPicture> commentPictures;
}