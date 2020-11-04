package com.wavestoked.web;

import com.wavestoked.domain.articleblock.ArticleBlock;
import com.wavestoked.service.articleBlock.ArticleBlockService;
import com.wavestoked.web.dto.ArticleBlockResponseDto;
import com.wavestoked.web.dto.ArticleBlockSaveRequestDto;
import com.wavestoked.web.dto.ArticleBlockUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ArticleBlockController {
    private final ArticleBlockService articleBlockService;

    @PostMapping("/api/articleBlock/update/{id}")
    public ArticleBlockResponseDto update(@RequestBody ArticleBlockUpdateRequestDto requestDto, @PathVariable Long id) {
        return articleBlockService.update(requestDto, id);
    }

    @GetMapping("/api/articleBlock/{id}")
//    @RequestMapping(value = "/api/articleBlock/{id}", method=RequestMethod.GET)
    public ArticleBlock findById(@PathVariable Long id) { // or @RequestParam("name") String name
        return articleBlockService.findById(id);    // which fires query to DB -> get the entity -> returns dto


        // return new ArticleBlockResponseDto(id, skinId);  // this is for saving
    }

    @GetMapping("/api/articleBlockDto/{id}")
    public ArticleBlockResponseDto dtoFindById(@PathVariable Long id) { // or @RequestParam("name") String name
        return articleBlockService.dtoFindById(id);    // which fires query to DB -> get the entity -> returns dto
        // return new ArticleBlockResponseDto(id, skinId);  // this is for saving
    }

    @PostMapping("/api/articleBlock/save")
    public @ResponseBody Long save(@RequestBody ArticleBlockSaveRequestDto requestDto) {
    // public Long save(@RequestBody ArticleBlockSaveRequestDto requestDto) {       // explained below

    /*
    * @RestController 는 @ResponseBody 가 굳이 필요없다;
    * @Controller는 @ResponseBody를 넣어야
    * MessageConverter가 사용된다
    * 즉 Entity or Dto or Object가 return 될때 -> default JsonMessageConverter가 사용
    * String type return시 StringMessageConverter 사용
    * int type return시 StringMessageConverter 사용
    */
        return articleBlockService.save(requestDto);
    }

    //pg 144
}


