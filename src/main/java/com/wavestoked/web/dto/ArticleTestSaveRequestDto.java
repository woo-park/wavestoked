//package com.wavestoked.web.dto;
//
//import com.wavestoked.domain.article.Article;
//import com.wavestoked.domain.skin.Skin;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class ArticleTestSaveRequestDto {
//
//
//    private String articleString;
//    private String author;
//    private int skinId;
//    private Skin skin;
//    //    private LocalDateTime modifiedDate;
//
//    @Builder
//    public ArticleTestSaveRequestDto(String articleString, String author, int skinId) {
//        this.articleString = articleString;
//        this.author = author;
//        this.skinId = skinId;
//        this.skin = new Skin();
//    }
//
//    public ArticleTest toEntity() {
//        return ArticleTest.builder()
//                .articleString(articleString)
//                .author(author)
//                .skin(skin)
//                .skinId(skinId)
//                .build();
//    }
//}
