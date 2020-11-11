package com.wavestoked.domain.article;

import com.wavestoked.domain.BaseTimeEntity;
import com.wavestoked.domain.skin.Skin;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private String articleString;

    @Column(nullable = true)
    private String author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="SKIN_ID")         // creates a SKIN FK here
    private Skin skin;

    @Builder
    public Article(String articleString, String author, Skin skin) {        // Skin 은 괜찮, 1개이니까
        this.articleString = articleString;
        this.author = author;
        this.skin = skin;
    }

    public void changeSkin(Skin skin) {
        this.skin = skin;
        skin.getArticles().add(this);       // 결과적으론 양쪽다 해줘야한다
    }

    public void updateSkin(Skin skin) {
        this.skin = skin;
        skin.getArticles().add(this);
    }

//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", articleString='" + articleString + '\'' +
//                ", author='" + author + '\'' +
//                ", skin=" + skin +
//                '}';
//    }




}
