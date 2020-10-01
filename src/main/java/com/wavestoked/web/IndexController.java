package com.wavestoked.web;

import com.wavestoked.config.auth.dto.SessionUser;
import com.wavestoked.domain.user.User;
import com.wavestoked.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

}



/*
*
    HttpSession's setAttribute("Key", Value)
        "Key"를 사용하여 객체를 세션에 바인딩한다.
        Value는 값으로 들어올 자료형을 예측할 수 없기에 Object형으로 업캐스팅하여 모두 받아낸다.
    HttpSession's getAttribute("Key")
        "Key"로 바인딩된 객체를 돌려주고, "Key"로 바인딩된 객체가 없다면 null를 돌려준다.
        Value는 세션을 저장할 때 Object형으로 업캐스팅을 했으므로, 가져올 땐 원래대로 다운캐스팅 해야 한다.
    HttpServletRequest's getSession(true)
        이미 세션이 있다면 그 세션을 돌려주고, 세션이 없으면 새로운 세션을 생성한다.
        request.getSession()로 쓸 수 있다.
    HttpServletRequest's getSession(false)
        이미 세션이 있다면 그 세션을 돌려주고, 세션이 없으면 null을 돌려준다.

*
* */