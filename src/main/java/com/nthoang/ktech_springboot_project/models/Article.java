package com.nthoang.ktech_springboot_project.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Entity
@Data
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String title;
    private  String password;
    private String content;
    private  String  articleType;

    @OneToMany(mappedBy = "commentedArticle")
    @ToString.Exclude
    private List<Comment> commentList;

    @ManyToMany
    @JoinTable(
            name = "article_hashtag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private List<Hashtag> hashtags;
}
