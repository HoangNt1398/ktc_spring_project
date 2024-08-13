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
        return "articles/article-view";
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

    @PostMapping("article-tag")
    public String searchByTag(@RequestParam("tag") String tag, Model model) {
        final String TAG = String.format("#%s", tag);
        model.addAttribute("articleList", articleService.findArticleAllByTag(TAG));
        model.addAttribute("tag", TAG);
        return "articles/article-tag";
    }

    @PostMapping("article-search")
    public String search(@RequestParam("searchTern") String searchTern,
                         @RequestParam("searchType") String searchType,
                         @RequestParam("articleType") String articleType,
                         Model model) {
        List<Article> articleList;
        if (articleType.equals("전체게시판")) {
            articleList = searchType.equals("content")
                    ? articleService.searchAllByContent(searchTern)
                    : articleService.searchAllByTitle(searchTern);
        } else {
            articleList = searchType.equals("content")
                    ? articleService.searchAllByArticleTypeAndContent(articleType, searchTern)
                    : articleService.searchAllByArticleTypeAndTitle(articleType, searchTern);
        }
        model.addAttribute("articleList", articleList);
        model.addAttribute("searchTern", searchTern);
        return "articles/article-search-view";
    }

    private void createModel(Model model) {
        model.addAttribute("boardList", articleService.readArticleAll());
        model.addAttribute("returnBoardType", "전체 게시판");
    }
}
