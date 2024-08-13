package com.nthoang.ktech_springboot_project.services;

import com.nthoang.ktech_springboot_project.models.Comment;
import java.util.List;

public interface CommentService {
    void createComment(Long articleId, String content, String password);
    List<Comment> getCommentList(Long articleId);
    void deleteCommentById(Long id);
    Comment readComment(Long id);
}
