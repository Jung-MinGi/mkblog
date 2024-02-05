package com.min.myblogv1.controller;

import com.min.myblogv1.domain.FindTextParam;
import com.min.myblogv1.domain.GlobalConst;
import com.min.myblogv1.domain.LoginFormDTO;
import com.min.myblogv1.domain.WriteForm;
import com.min.myblogv1.service.DataAccessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class viewController {
    private final DataAccessService service;

    @GetMapping("/")
    public String homeHandler(HttpServletRequest request) {
        request.getSession();
        return "index";
    }

    @GetMapping("/image")//글 작성 페이지로 이동 핸들러
    public String summer(HttpServletRequest request, Model model) {
        List<String> tablesName = service.getTablesName();
        model.addAttribute("tables", tablesName);
        return "board";
    }

    @GetMapping("/view/{category}/{id}")//작성된 글 보여주는 핸들러
    public String showText2(Model model,@Validated @ModelAttribute FindTextParam findTextParam,BindingResult bindingResult) {
        WriteForm text = null;
        if (findTextParam.getId() == 0) {
            text = service.findTextLatest(findTextParam.getCategory());
        } else text = service.findTextById(findTextParam);

        model.addAttribute("text", text);
        log.info("writeform ={}", findTextParam);
        return "view";
    }

    @GetMapping("/update/{category}/{id}")//글 수정페이지로 이동하는 핸들러
    public String updateForm(@PathVariable("id") int id
            , @PathVariable("category") String category,
                             Model model,
                             @SessionAttribute(name = GlobalConst.LOGIN_USER, required = false) String username) {
        //refactor필요@@@@@@@@@@⬇️
        if (!username.equals("wjdalsrl")) {
            return "redirect:/view/{category}/{id}";
        }
//        if(username == null){
//            bindingResult.addError(new ObjectError("username","권한이 없습니다"));
//            return "redirect:/view/"+category+"/"+id;
//        }
        //refactor필요@@@@@@@@@@⬆️
        List<String> list = service.getTablesName();
        FindTextParam dto = new FindTextParam();
        dto.setId(id);
        dto.setCategory(category);
        WriteForm text = service.findTextById(dto);
        model.addAttribute("tables", list);
        model.addAttribute("contents", text);
        return "update";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(required = false) String redirectURL) {
        model.addAttribute("url", redirectURL);
        model.addAttribute("loginFormDTO", new LoginFormDTO());
        return "login";

    }

    @PostMapping("/login")
    public String loginPagePost(HttpServletRequest request
            , @Validated @ModelAttribute LoginFormDTO dto, BindingResult bindingResult, Model model
            , @RequestParam(required = false) String redirectURL) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("member", new LoginFormDTO());
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute(GlobalConst.LOGIN_USER, dto.getUsername());
        if (redirectURL == null) return "index";
        return "redirect:" + redirectURL;
    }
}
