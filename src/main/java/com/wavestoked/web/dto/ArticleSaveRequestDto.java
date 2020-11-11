package com.wavestoked.web.dto;

import com.wavestoked.domain.article.Article;
import com.wavestoked.domain.articleblock.ArticleBlock;
import com.wavestoked.domain.skin.Skin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleSaveRequestDto {
    private String articleString;
    private Skin skin;
    private String author;
    //    private LocalDateTime modifiedDate;

    @Builder
    public ArticleSaveRequestDto(String articleString, String author, Skin skin) {
        this.articleString = articleString;
        this.author = author;
        this.skin = skin;
    }

    public Article toEntity() {
        return Article.builder()
                .articleString(articleString)
                .author(author)
                .skin(skin)
                .build();
    }
}
