package com.min.myblogv1.controller;

import com.min.myblogv1.domain.FindTextParamDTO;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.service.DataAccessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

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
    public String summer(HttpServletRequest request,Model model) {
        List<String> tablesName = service.getTablesName();
            HttpSession session = request.getSession(false);
            session.setAttribute("login","wjdalsrl1234");
        model.addAttribute("tables", tablesName);
        return "board";
    }

    @GetMapping("/view/{category}/{title}")//작성된 글 보여주는 핸들러
    public String showText(Model model, @ModelAttribute FindTextParamDTO findTextParamDTO){
//            , @PathVariable(value = "category") String category
//            , @PathVariable("title") String title) {
        //contents을 content.html에 보여주기
        log.info("category={} , title={}", findTextParamDTO.toString());
        WriteForm text = service.findTextByTitle(findTextParamDTO);
        model.addAttribute("text", text);
        return "view";
    }
}
