package com.wavestoked.domain.article;

import com.wavestoked.domain.skin.Skin;
import com.wavestoked.domain.skin.SkinRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SkinTest {
    @Autowired
    SkinRepository skinRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        Skin skin = Skin.builder()
                .name("Skin2")
                .build();
        // 여기서 중요한것은 skin에 따로 article1 & article2를 추가하지않았다는것이다
        // 자동으로 getArticles을 통해 read할수있는것을 시연해보겠다

        // 어떻게?

        // owner 객채인 Article에 직접 .skin(skin) 을 빌드함으로써

        Article article = Article.builder()
                .articleString("<h1>test101</h1>")
                .author("woo")
                .skin(skin)
                .build();

        Article article2 = Article.builder()
                .articleString("<h1>test102</h1>")
                .author("hoo")
                .skin(skin)
                .build();

        skinRepository.save(skin);

        entityManager.clear();  //영속성 컨텍스트 제거
    }

    @Test
    public void owner객채만_저장해도_연관객체_저장여부_확인() {
       Skin skin = skinRepository.findById(1L).get();   //searches for id = 1 type Long
       for(Article article: skin.getArticles()) {
           assertThat(article.getArticleString()).startsWith("<h1>");
       }

    }

    /*
    *
    * 1.
    * Article entity에
    *   skin.getArticles().add(this); //양방향 위해 추가함!
    *  안해도 test pass 되네?
    * 즉 owner entity / owner repository / owner Table 에만 .save해도
    * 알아서 연관 객체 entity / Table 은 업데이트 된다는거네
    *
    * 2.
    * 테스트 해볼것은...
    * 반대로 연관 객체에 save를 했을시에
    * owner테이블에 어떻게 나올지를 테스트 해봐야겠네
    *
    * 3.
    * 지웠을때
    * orphanRemoval = true로
    * 연관객채가 자동으로 삭제 되는지
    * */


}
