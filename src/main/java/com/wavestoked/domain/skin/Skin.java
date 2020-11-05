package com.wavestoked.domain.skin;
import com.wavestoked.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;


@Entity
public class Skin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto generated
    private Long id;
//
//    private String skinName;
//
////    @OneToMany(mappedBy = "skin",cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ArticleBlock> articleBlocks = new ArrayList<>();

//    @Builder
//    public Skin(String skinName, List<ArticleBlock> articleBlocks) {
//        this.skinName = skinName;
//        if(articleBlocks != null){
////            this.articleBlocks = articleBlocks;
//        }
//    }

//    public void addArticleBlock(ArticleBlock articleBlock){
//        this.articleBlocks.add(articleBlock);
////        articleBlock.setSkin(this);
//    }
//
//    public List<ArticleBlock> getArticleBlocks() {
//        return this.articleBlocks;
//    }

}
