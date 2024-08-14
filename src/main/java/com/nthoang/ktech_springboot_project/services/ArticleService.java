package com.nthoang.ktech_springboot_project.services;

import com.nthoang.ktech_springboot_project.models.Article;
import java.util.List;

public interface ArticleService {
    void createArticle(String title, String content, String password, String articleType);
    List<Article> readArticleAll();
    List<Article> findAllByArticleType(String articleType);
    Article findById(Long id);
    void deleteById(Long id);
    void update(Long id, String title, String content, String articleType);
    List<Article> searchByTitle(String title);
    List<Article> searchByContent(String content);
    List<Article> searchByTitleAndArticleType(String title, String articleType);
    List<Article> searchByContentAndArticleType(String content, String articleType);

    List<Article> findAllByHashtag(String name);
}
