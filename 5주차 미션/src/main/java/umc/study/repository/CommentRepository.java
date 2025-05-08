package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.common.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}