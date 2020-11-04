package com.wavestoked.web.dto;

import com.wavestoked.domain.articleblock.ArticleBlock;
import com.wavestoked.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleBlockSaveRequestDto {
    private String articleString;
    private int skinId;
    private String author;
    //    private LocalDateTime modifiedDate;

    @Builder
    public ArticleBlockSaveRequestDto(String articleString, int skinId, String author) {
        this.articleString = articleString;
//        this.id = id;
        this.skinId = skinId;
        this.author = author;
    }

    public ArticleBlock toEntity() {
        return ArticleBlock.builder()
                .articleString(articleString)
                .skinId(skinId)
                .author(author)
                .build();
    }
}
