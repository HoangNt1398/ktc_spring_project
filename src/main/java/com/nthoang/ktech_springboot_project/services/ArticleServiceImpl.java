package com.nthoang.ktech_springboot_project.services;

import com.nthoang.ktech_springboot_project.models.Article;
import com.nthoang.ktech_springboot_project.models.Hashtag;
import com.nthoang.ktech_springboot_project.repository.ArticleRepository;
import com.nthoang.ktech_springboot_project.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final HashtagRepository hashtagRepository;

    @Override
    public void createArticle(String title, String content, String password, String articleType) {
        Article article = new Article();
        article.setTitle(title);
        article.setPassword(password);
        article.setArticleType(articleType);
        article.setContent(content);

        // Extract hashtags and save them
        List<Hashtag> hashtags = extractHashtags(content);
        article.setHashtags(hashtags);

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



    public List<Article> searchByTitle(String title) {
        return articleRepository.findAllByTitleContainingIgnoreCase(title);
    }

    public List<Article> searchByContent(String content) {
        return articleRepository.findAllByContentContainingIgnoreCase(content);
    }

    public List<Article> searchByTitleAndArticleType(String title, String articleType) {
        return articleRepository.findAllByArticleTypeAndTitleContainingIgnoreCase(articleType, title);
    }

    public List<Article> searchByContentAndArticleType(String content, String articleType) {
        return articleRepository.findAllByArticleTypeAndContentContainingIgnoreCase(articleType, content);
    }

    private List<Hashtag> extractHashtags(String content) {
        Set<String> hashtagNames = extractHashtagNames(content);
        List<Hashtag> hashtags = new ArrayList<>();

        for (String name : hashtagNames) {
            Hashtag hashtag = hashtagRepository.findByName(name).stream().findFirst().orElseGet(() -> {
                Hashtag newHashtag = new Hashtag();
                newHashtag.setName(name);
                return hashtagRepository.save(newHashtag);
            });
            hashtags.add(hashtag);
        }
        return hashtags;
    }

    private Set<String> extractHashtagNames(String content) {
        return Set.of(content.split("\\s+"))
                .stream()
                .filter(word -> word.startsWith("#"))
                .map(word -> word.replaceAll("[^\\w#]", ""))
                .collect(Collectors.toSet());
    }

    public List<Article> findAllByHashtag(String hashtag) {
        return articleRepository.findAllByHashtags_Name(hashtag);
    }


}
