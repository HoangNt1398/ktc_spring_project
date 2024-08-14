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
    // Tìm tất cả bài viết theo loại và sắp xếp theo ID giảm dần
    List<Article> findAllByArticleTypeOrderByIdDesc(String articleType);

    // Tìm tất cả bài viết theo nội dung chứa từ khóa
    List<Article> findAllByContentContainingIgnoreCase(String content);

    // Tìm tất cả bài viết theo tiêu đề chứa từ khóa
    List<Article> findAllByTitleContainingIgnoreCase(String title);

    // Tìm tất cả bài viết theo loại và nội dung chứa từ khóa
    List<Article> findAllByArticleTypeAndContentContainingIgnoreCase(String articleType, String content);

    // Tìm tất cả bài viết theo loại và tiêu đề chứa từ khóa
    List<Article> findAllByArticleTypeAndTitleContainingIgnoreCase(String articleType, String title);

    // Tìm tất cả bài viết theo ID giảm dần
    default List<Article> findAllOrderByIdDesc() {
        return findAll(Sort.by(Sort.Order.desc("id")));

    }
    @Query("SELECT a FROM Article a WHERE a.content LIKE %:searchTern%")
    List<Article> findAllByContent(String searchTern);

    @Query("SELECT a FROM Article a WHERE a.title LIKE %:searchTern%")
    List<Article> findAllByTitle(String searchTern);

    @Query("""
            select a from Article a where a.articleType = :articleType
            and a.content = %:searchTern%
            """)
    List<Article> findAllByArticleTypeAndContent
            (@Param("articleType") String articleType, @Param("searchTern") String searchTern);

    @Query("""
            select a from Article a where a.articleType = :articleType
            and a.title = %:searchTern%
            """)
    List<Article> findAllByArticleTypeAndTitle(
            @Param("articleType") String articleType,
            @Param("searchTern") String searchTern);

    List<Article> findAllByHashtags_Name(String hashtag);
}
