package com.nthoang.ktech_springboot_project.services;

import com.nthoang.ktech_springboot_project.models.Comment;
import com.nthoang.ktech_springboot_project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ArticleService articleService; // Để lấy Article

    @Override
    public void createComment(Long articleId, String content, String password) {
        Comment comment = new Comment();
        comment.setPassword(password);
        comment.setCommentedArticle(articleService.findById(articleId));
        comment.setContent(content);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentList(Long articleId) {
        return commentRepository.findAllByCommentedArticleId(articleId);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment readComment(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }
}
