package com.wavestoked.web;


import com.wavestoked.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// @ResponseBody 각 메소드에 선언했던걸 한번에 해결
@RestController                     //controller -> to -> JSON
public class HelloController {
    @GetMapping("/hello")  //
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        // var String name 에 저장
        return new HelloResponseDto(name, amount);
    }
}

