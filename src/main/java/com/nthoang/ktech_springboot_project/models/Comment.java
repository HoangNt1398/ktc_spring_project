package com.nthoang.ktech_springboot_project.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String content;
    String password;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "article_id")
    private  Article commentedArticle;
}

