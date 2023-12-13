package com.hedgehog.client.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardMainController {
    @GetMapping("/board")
    public String boardMain() {

        return "redirect:/board/noticeList";
    }
}
