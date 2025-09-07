package umc.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.common.Comment;
import umc.study.domain.common.Member;
import umc.study.domain.common.Store;
import umc.study.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {
    private final CommentRepository commentRepository;

    @Transactional
    public Comment save(Member member, Store store, byte point, String content) {
        return commentRepository.saveAndFlush(Comment.builder()
                .member(member)
                .store(store)
                .point(point)
                .content(content)
                .commentPictures(List.of()).build());
    }

    public Comment findById(long id) {
        return commentRepository.findById(id).orElse(null);
    }
}