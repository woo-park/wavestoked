package com.wavestoked.web;

import com.wavestoked.domain.articleblock.ArticleBlock;
import com.wavestoked.service.article.ArticleService;
import com.wavestoked.service.articleBlock.ArticleBlockService;
import com.wavestoked.web.dto.ArticleBlockResponseDto;
import com.wavestoked.web.dto.ArticleBlockSaveRequestDto;
import com.wavestoked.web.dto.ArticleBlockUpdateRequestDto;
import com.wavestoked.web.dto.ArticleSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RequiredArgsConstructor
@RestController
public class ArticleController {


    private final ArticleService articleService;

//    @PostMapping("/api/article/update/{id}")
//    public Long update(@RequestBody ArticleBlockUpdateRequestDto requestDto, @PathVariable Long id) {
//        return articleBlockService.update(requestDto, id);
//    }

//    @GetMapping("/api/articleBlock/{id}")
////    @RequestMapping(value = "/api/articleBlock/{id}", method=RequestMethod.GET)
//    public ArticleBlock findById(@PathVariable Long id) { // or @RequestParam("name") String name
//        return articleBlockService.findById(id);    // which fires query to DB -> get the entity -> returns dto
//
//
//        // return new ArticleBlockResponseDto(id, skinId);  // this is for saving
//    }
//
//
//
//    @GetMapping("/api/articleBlockDto/{id}")
//    public ArticleBlockResponseDto dtoFindById(@PathVariable Long id) { // or @RequestParam("name") String name
//        return articleBlockService.dtoFindById(id);    // which fires query to DB -> get the entity -> returns dto
//        // return new ArticleBlockResponseDto(id, skinId);  // this is for saving
//    }
//    @PostMapping("/api/article/testsave")
//    public @ResponseBody Long save(@RequestBody ArticleTestSaveRequestDto requestDto) {
//
//        return articleService.testsave(requestDto)
//    }

    @PostMapping("/api/article/save")
    public @ResponseBody Long save(@RequestBody ArticleSaveRequestDto requestDto) {
    // public Long save(@RequestBody ArticleBlockSaveRequestDto requestDto) {       // explained below

        System.out.println("hmmmm");
        System.out.println(requestDto);
    /*
    * @RestController 는 @ResponseBody 가 굳이 필요없다;
    * @Controller는 @ResponseBody를 넣어야
    * MessageConverter가 사용된다
    * 즉 Entity or Dto or Object가 return 될때 -> default JsonMessageConverter가 사용
    * String type return시 StringMessageConverter 사용
    * int type return시 StringMessageConverter 사용
    */
        return articleService.save(requestDto);
    }

    //pg 144
}


