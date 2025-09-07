package umc.study.service;

import umc.study.domain.common.Comment;
import umc.study.domain.common.Member;
import umc.study.domain.common.Store;

public interface ReviewService {
    Comment save(Member member, Store store, byte point, String content);
    Comment findById(long id);
}
