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

    //3. 영속성 전이: CASCADE
//    특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속성 상태로 만들고 싶을 때
//    예 : 부모 엔티티를 저장할 때 자식 엔티티도 함께 저장
//    영속성 전이는 연관관계를 매핑하는 것과는 아무 관련이 없음
//    엔티티를 영속화할 때 연관된 엔티티도 함께 영속화하는 편리함을 제공할 뿐
    //cascade 옵션 : Article를 저장할 때 Skin도 같이 저장하고 싶다.
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="SKIN_ID")         // creates a SKIN FK here
    private Skin skin;

    @Builder
    public Article(String articleString, String author, Skin skin) {        // Skin 은 괜찮, 1개이니까
        this.articleString = articleString;
        this.author = author;
        this.skin = skin;
//        skin.getArticles().add(this); //양방향 위해 추가함! ... 꼭해야하나
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
