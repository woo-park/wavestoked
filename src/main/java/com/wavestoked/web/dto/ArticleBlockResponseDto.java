package com.wavestoked.web.dto;

import com.wavestoked.domain.articleblock.ArticleBlock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ArticleBlockResponseDto {
    private String articleString;
    private Long id;    // or int
    private int skinId;
    //    private LocalDateTime modifiedDate;
    private String author;

    public ArticleBlockResponseDto(ArticleBlock entity) {
        this.articleString = entity.getArticleString();
        this.id = entity.getId();
        this.skinId = entity.getSkinId();
//        this.modifiedDate = entity.getModifiedDate();
        this.author = entity.getAuthor();
    }
}
