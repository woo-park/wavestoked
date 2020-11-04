package com.wavestoked.domain.articleblock;


import com.wavestoked.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class ArticleBlock extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto generated
    private Long id;

//    @Column(nullable = false)

    private String articleString;
    private int skinId;
    private String author;


    @Builder
    public ArticleBlock(String articleString, int skinId, String author) {
        this.articleString = articleString;
        this.skinId = skinId;
        this.author = author;
    }




}
