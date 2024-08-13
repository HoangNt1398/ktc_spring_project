package com.nthoang.ktech_springboot_project.controllers;

import com.nthoang.ktech_springboot_project.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("boards")
@RequiredArgsConstructor
public class BoardController {
    private final ArticleService articleService;

    @GetMapping("board")
    public String board(Model model) {
        model.addAttribute("boardList", articleService.readArticleAll());
        model.addAttribute("returnBoardType", "전체게시판");
        return "boards/board";
    }

    @GetMapping("자유게시판")
    public String freeBoard(Model model) {
        model.addAttribute("returnBoardType", "자유게시판");
        model.addAttribute("boardList", articleService.findAllByArticleType("자유게시판"));
        return "boards/board";
    }

    @GetMapping("개발게시판")
    public String developmentBoard(Model model) {
        model.addAttribute("returnBoardType", "개발게시판");
        model.addAttribute("boardList", articleService.findAllByArticleType("개발게시판"));
        return "boards/board";
    }

    @GetMapping("일상게시판")
    public String dailyBoard(Model model) {
        model.addAttribute("returnBoardType", "일상게시판");
        model.addAttribute("boardList", articleService.findAllByArticleType("일상게시판"));
        return "boards/board";
    }

    @GetMapping("사건사고게시판")
    public String incidentBoard(Model model) {
        model.addAttribute("returnBoardType", "사건사고게시판");
        model.addAttribute("boardList", articleService.findAllByArticleType("사건사고게시판"));
        return "boards/board";
    }
}
