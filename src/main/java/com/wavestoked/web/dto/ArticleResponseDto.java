package com.wavestoked.web.dto;

import com.wavestoked.domain.article.Article;
import com.wavestoked.domain.articleblock.ArticleBlock;
import com.wavestoked.domain.skin.Skin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDto {
    private String articleString;
    private Long id;    // or int
    private Skin skin;
    //    private LocalDateTime modifiedDate;

    public ArticleResponseDto(Article entity) {
        this.articleString = entity.getArticleString();
        this.id = entity.getId();
        this.skin = entity.getSkin();
    }
}
