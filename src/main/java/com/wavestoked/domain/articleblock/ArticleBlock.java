package com.wavestoked.domain.articleblock;


import com.wavestoked.domain.BaseTimeEntity;
import com.wavestoked.domain.skin.Skin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ArticleBlock extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto generated
    private Long id;

    private String articleString;
    private int skinId;
    private String author;

//    @Column(nullable = true)
//    private Skin skin;  //추가하면 application에러나네

    @Builder
    public ArticleBlock(String articleString, int skinId, String author) {
        this.articleString = articleString;
        this.skinId = skinId;
        this.author = author;
    }

    public void update(String author, String articleString) {
        this.author = author;
        this.articleString = articleString;
    }



}
