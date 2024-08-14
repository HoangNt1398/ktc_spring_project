package com.nthoang.ktech_springboot_project.repository;

import com.nthoang.ktech_springboot_project.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByArticleTypeOrderByIdDesc(String articleType);

    List<Article> findAllByTitleContainingIgnoreCase(String title);
    List<Article> findAllByContentContainingIgnoreCase(String content);
    List<Article> findAllByArticleTypeAndTitleContainingIgnoreCase(String articleType, String title);
    List<Article> findAllByArticleTypeAndContentContainingIgnoreCase(String articleType, String content);

    // Tìm tất cả bài viết theo ID giảm dần
    default List<Article> findAllOrderByIdDesc() {
        return findAll(Sort.by(Sort.Order.desc("id")));

    }

    List<Article> findAllByHashtags_Name(String hashtag);
}
