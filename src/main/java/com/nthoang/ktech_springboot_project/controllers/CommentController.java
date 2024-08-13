package com.nthoang.ktech_springboot_project.controllers;

import com.nthoang.ktech_springboot_project.models.Comment;
import com.nthoang.ktech_springboot_project.services.CommentService;
import com.nthoang.ktech_springboot_project.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    @RequestMapping("{articleId}/comment")
    public String create(@RequestParam("password") String password,
                         @RequestParam("content") String content,
                         @PathVariable("articleId") Long articleId,
                         Model model) {
        commentService.createComment(articleId, content, password);
        model.addAttribute("article", articleService.findById(articleId));
        model.addAttribute("commentList", commentService.getCommentList(articleId));
        return "/articles/article-view";
    }

    @PostMapping("{articleId}/{id}/delete")
    public String delete(@RequestParam("password") String password,
                         @PathVariable("id") Long id,
                         @PathVariable("articleId") Long articleId,
                         Model model) {
        Comment comment = commentService.readComment(id);
        if (comment != null && comment.getPassword().equals(password)) {
            commentService.deleteCommentById(id);
        }
        model.addAttribute("article", articleService.findById(articleId));
        model.addAttribute("commentList", commentService.getCommentList(articleId));
        return "/articles/article-view";
    }
}
