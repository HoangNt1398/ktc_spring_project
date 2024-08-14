package com.nthoang.ktech_springboot_project.controllers;

import com.nthoang.ktech_springboot_project.models.Article;
import com.nthoang.ktech_springboot_project.services.ArticleService;
import com.nthoang.ktech_springboot_project.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("new-article")
    public String create() {
        return "articles/new-article";
    }

    @PostMapping("create")
    public String create(@RequestParam("title") String title,
                         @RequestParam("content") String content,
                         @RequestParam("password") String password,
                         @RequestParam("articleType") String articleType,
                         Model model) {
        createModel(model);
        articleService.createArticle(title, content, password, articleType);
        return "redirect:/boards/board";
    }

    @PostMapping("{id}")
    public String postArticleView(@PathVariable("id") Long id) {
        return String.format("redirect:/articles/%d", id);
    }

    @GetMapping("{id}")
    public String articleView(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findById(id);
        List<Article> articleList = articleService.readArticleAll();
        int currentId = articleList.indexOf(article);
        Article nextArticle = null;
        Article beforeArticle = null;
        if (currentId < articleList.size() - 1) {
            nextArticle = articleList.get(currentId + 1);
        }
        if (currentId > 0) {
            beforeArticle = articleList.get(currentId - 1);
        }
        model.addAttribute("nextBoard", nextArticle);
        model.addAttribute("beforeBoard", beforeArticle);
        model.addAttribute("article", article);
        model.addAttribute("commentList", commentService.getCommentList(id));
        model.addAttribute("hashtags", article.getHashtags());
        return "articles/article-view";
    }


    @GetMapping("hashtag/{name}")
    public String articlesByHashtag(@PathVariable("name") String name, Model model) {
        List<Article> articles = articleService.findAllByHashtag(name);
        model.addAttribute("articleList", articles);
        model.addAttribute("hashtag", name);
        return "articles/article-hashtag";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id,
                         @RequestParam("password") String password,
                         Model model) {
        Article currentArticle = articleService.findById(id);
        if (currentArticle != null && currentArticle.getPassword().equals(password)) {
            articleService.deleteById(id);
        }
        createModel(model);
        return "redirect:/boards/board";
    }

    @RequestMapping("{id}/update")
    public String update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("article", articleService.findById(id));
        return "articles/update-view";
    }

    @PostMapping("{id}/update")
    public String update(Model model,
                         @PathVariable("id") Long id,
                         @RequestParam("title") String title,
                         @RequestParam("content") String content,
                         @RequestParam("password") String password,
                         @RequestParam("articleType") String articleType) {
        Article currentArticle = articleService.findById(id);

        if (currentArticle != null && currentArticle.getPassword().equals(password)) {
            articleService.update(id, title, content, articleType);
        }
        createModel(model);
        return "redirect:/boards/board";
    }


    @PostMapping("article-search")
    public String searchArticles(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam("searchType") String searchType,
            @RequestParam(value = "articleType", required = false) String articleType,
            Model model) {

        if ("title".equalsIgnoreCase(searchType)) {
            if (articleType != null && !articleType.isEmpty()) {
                model.addAttribute("articles", articleService.searchByTitleAndArticleType(searchTerm, articleType));
            } else {
                model.addAttribute("articles", articleService.searchByTitle(searchTerm));
            }
        } else if ("content".equalsIgnoreCase(searchType)) {
            if (articleType != null && !articleType.isEmpty()) {
                model.addAttribute("articles", articleService.searchByContentAndArticleType(searchTerm, articleType));
            } else {
                model.addAttribute("articles", articleService.searchByContent(searchTerm));
            }
        } else {
            // Handle invalid searchType
            model.addAttribute("error", "Invalid search type");
        }

        model.addAttribute("returnBoardType", articleType != null ? articleType : "Tất cả");
        return "articles/article-search-view";
    }

    private void createModel(Model model) {
        model.addAttribute("boardList", articleService.readArticleAll());
        model.addAttribute("returnBoardType", "전체 게시판");
    }
}
