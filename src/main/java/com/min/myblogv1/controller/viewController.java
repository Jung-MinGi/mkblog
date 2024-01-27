package com.min.myblogv1.controller;

import com.min.myblogv1.service.DataAccessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class viewController {
    private final DataAccessService service;
    @GetMapping("/")
    public String IndexHandler() {
        return "index";
    }
    @GetMapping("/image")//글 작성 페이지로 이동 핸들러
    public String summer(Model model) {
        List<String> tablesName = service.getTablesName();
        model.addAttribute("tables", tablesName);
        return "board";
    }
}
