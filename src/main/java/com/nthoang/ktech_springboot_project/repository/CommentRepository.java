package com.nthoang.ktech_springboot_project.repository;

import com.nthoang.ktech_springboot_project.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Tìm tất cả bình luận theo ID bài viết
    List<Comment> findAllByCommentedArticleId(Long articleId);
}
