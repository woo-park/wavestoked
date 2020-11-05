package com.wavestoked.service.articleBlock;


import com.wavestoked.domain.articleblock.ArticleBlock;
import com.wavestoked.domain.articleblock.ArticleBlockRepository;
import com.wavestoked.domain.posts.Posts;
import com.wavestoked.web.dto.ArticleBlockResponseDto;
import com.wavestoked.web.dto.ArticleBlockSaveRequestDto;
import com.wavestoked.web.dto.ArticleBlockUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleBlockService {
    private final ArticleBlockRepository articleBlockRepository;
    @Transactional
    public Long update(ArticleBlockUpdateRequestDto requestDto, Long id) {
        ArticleBlock articleBlock = articleBlockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        articleBlock.update(requestDto.getAuthor(), requestDto.getArticleString()); // void function so it doesn't return anything
        return id;
    }

    @Transactional
    public Long save(ArticleBlockSaveRequestDto requestDto) {
        return articleBlockRepository.save(requestDto.toEntity()).getId();
        // 1. tells requestDto to convert to entity
        // 2. tells extended jpaRepository to save the converted entity
        // 3. returns the entity's id by using getId() lombok method
    }
//
//    public Long findById(long id) {
//
//        Posts entity = ArticleBlockRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 아티클 블락이 없습니다. id=" + id));
//        return new ArticleBlockResponseDto(entity);
//    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)  // hm not found in the book
    public ArticleBlock findById(Long id) {         // returns Dto from repository Db
        System.out.println("HERE");
        ArticleBlock entity = articleBlockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아티클 블락이 없습니다. id=" + id));
        System.out.println("ENTit");
        System.out.println(entity.getAuthor());
        System.out.println(entity);
//        return new PostsResponseDto(entity);
        return entity;        // adding a layer of abstraction to the found entity(Posts)
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)  // hm not found in the book
    public ArticleBlockResponseDto dtoFindById(Long id) {         // returns Dto from repository Db
        System.out.println("HERE");
        ArticleBlock entity = articleBlockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아티클 블락이 없습니다. id=" + id));
        System.out.println("ENTit");
        System.out.println(entity.getAuthor());
        System.out.println(entity);
        return new ArticleBlockResponseDto(entity);
//        return entity;        // adding a layer of abstraction to the found entity(Posts)
    }




}
