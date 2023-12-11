package com.hedgehog.client.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    @GetMapping("/board")
    public String getBoard() {

        return "client/content/board/board";
    }
    @GetMapping("/questionInfo")
    public String questionInfo() {
        return "/client/content/board/questionInfo";
    }
    @GetMapping("/reviewInfo")
    public String reviewInfo() {
        return "/client/content/board/reviewInfo";
    }
    @GetMapping("/noticeInfo")
    public String noticeInfo() {
        return "/client/content/board/noticeInfo";
    }


}
