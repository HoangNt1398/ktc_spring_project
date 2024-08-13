package com.nthoang.ktech_springboot_project.services;

import com.nthoang.ktech_springboot_project.models.Article;
import com.nthoang.ktech_springboot_project.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public void createArticle(String title, String content, String password, String articleType) {
        Article article = new Article();
        article.setTitle(title);
        article.setPassword(password);
        article.setArticleType(articleType);
        article.setContent(content);
        articleRepository.save(article);
    }

    @Override
    public List<Article> readArticleAll() {
        return articleRepository.findAllOrderByIdDesc();
    }

    @Override
    public List<Article> findAllByArticleType(String articleType) {
        return articleRepository.findAllByArticleTypeOrderByIdDesc(articleType);
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void update(Long id, String title, String content, String articleType) {
        Article article = articleRepository.findById(id).orElseThrow();
        article.setTitle(title);
        article.setArticleType(articleType);
        article.setContent(content);
        articleRepository.save(article);
    }

    @Override
    public List<Article> findArticleAllByTag(String tag) {
        return articleRepository.findAllByContentContainingIgnoreCase(tag);
    }

    @Override
    public List<Article> searchAllByContent(String searchTern) {
        return articleRepository.findAllByContentContainingIgnoreCase(searchTern);
    }

    @Override
    public List<Article> searchAllByTitle(String searchTern) {
        return articleRepository.findAllByTitleContainingIgnoreCase(searchTern);
    }

    @Override
    public List<Article> searchAllByArticleTypeAndContent(String contentType, String searchTern) {
        return articleRepository.findAllByArticleTypeAndContentContainingIgnoreCase(contentType, searchTern);
    }

    @Override
    public List<Article> searchAllByArticleTypeAndTitle(String contentType, String searchTern) {
        return articleRepository.findAllByArticleTypeAndTitleContainingIgnoreCase(contentType, searchTern);
    }
}
