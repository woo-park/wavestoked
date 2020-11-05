package com.wavestoked.domain.skin;
import com.wavestoked.domain.BaseTimeEntity;
import com.wavestoked.domain.article.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Skin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto generated
    private Long id;

    private String name;

    @OneToMany(mappedBy = "skin")            // FK선언 하나면 양방향 관계를 맺을 수 있다.
    private List<Article> articles = new ArrayList<Article>();

    @Builder
    public Skin(String name) {  //여기 그냥 articles param으로 넣었다가 에러 팡!
        this.name = name;
        // 이거도 하지말기
//        if(articles != null) {
//            this.articles = articles;
//        }
    }

    public void addArticle(Article article) {
        System.out.println("adding article");
        System.out.println(article);

        this.articles.add(article);
//        article.updateSkin(this);
    }

    @Override
    public String toString() {
        return "Skin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articles=" + articles +
                '}';
    }

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



    /*
    *
    *   주인은 mappedBy 속성 사용 하지 않고, @ JoinColumn

        주인이 아니면 mappedBy 속성으로 주인을 지정한다.
    *
    * */
}
