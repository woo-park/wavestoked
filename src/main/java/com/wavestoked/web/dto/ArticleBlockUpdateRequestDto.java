package com.wavestoked.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor          // allows constructor without parameters
public class ArticleBlockUpdateRequestDto {
    //    private Long id;
    private int skinId;
    private String articleString;
    private String author;


    @Builder
    public ArticleBlockUpdateRequestDto(int skinId,String articleString, String author) {
        this.skinId = skinId;
        this.articleString = articleString;
        this.author = author;
    }

}
