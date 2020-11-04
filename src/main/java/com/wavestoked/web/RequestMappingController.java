package com.wavestoked.web;

import com.wavestoked.config.auth.LoginUser;
import com.wavestoked.config.auth.dto.SessionUser;
import com.wavestoked.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Controller
public class RequestMappingController {
//    private final PostsService postsService;
//    @RequestMapping(value = "/request/{id}", method= RequestMethod.GET) //pg 160
//    public ModelAndView request(@PathVariable int id, ModelAndView mav) {
//        mav.setViewName("request");
//        mav.addObject("id", id);
//        mav.addObject("check", id >= 0);
//        return mav;
//    }

    @GetMapping("/request/{id}")
    public String request(@PathVariable int id, Model mav) {
        mav.addAttribute("id", id);
        mav.addAttribute("check", true);

        return "request";
    }
}
