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
    List<Article> findArticleAllByTag(String tag);
    List<Article> searchAllByContent(String searchTern);
    List<Article> searchAllByTitle(String searchTern);
    List<Article> searchAllByArticleTypeAndContent(String contentType, String searchTern);
    List<Article> searchAllByArticleTypeAndTitle(String contentType, String searchTern);

    List<Article> searchArticles(String searchType, String searchTerm, String articleType);

    List<Article> findAllByHashtag(String name);
}
