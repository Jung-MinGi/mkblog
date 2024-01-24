package com.min.myblogv1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class viewController {
    @GetMapping("/")
    public String IndexHandler() {
        return "index";
    }
    @GetMapping("/image")//글 작성 페이지로 이동 핸들러
    public String summer(Model model) {
//        List<String> list = service.getTables();
//        log.info("show tables={}", list);
//        model.addAttribute("tables", list);
        return "board";
    }
}
