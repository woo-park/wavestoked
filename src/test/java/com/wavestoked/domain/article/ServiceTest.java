//package com.wavestoked.domain.article;
//
//import com.wavestoked.domain.article.Article;
//import com.wavestoked.domain.skin.Skin;
//import com.wavestoked.domain.skin.SkinRepository;
//import com.wavestoked.service.skin.SkinService;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;           // added manually
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ServiceTest {
//
//    @Autowired
//    private SkinRepository skinRepository;
//
//    @Autowired
//    private SkinService skinService;
//
//    @After
//    public void cleanAll() {
//        skinRepository.deleteAll();
//    }
//
//    @Before
//    public void setup() {
//        List<Skin> skins = new ArrayList<>();
//
//        for(int i=0;i<10;i++){
//            List<Article> articles = new ArrayList<>();
//            articles.add(Article.builder().author("test개발자").articleString("test").build());
//
//            Skin skin = Skin.builder()
//                    .name("스킨"+i)
//                    .articles(articles)
//                    .build();
//
//            skin.addArticle(Article.builder().skin(skin).author("개발자" + i).articleString("say something interesting" + i).build());
//            skins.add(skin);
//        }
//
//        skinRepository.saveAll(skins);
//    }
//
//    @Test
//    public void Academy여러개를_조회시_Subject가_N1_쿼리가발생한다() throws Exception {
//        //given
//        List<String> articleAuthors = skinService.findAllArticleAuthor();
//
//        //then
//        assertThat(articleAuthors.size()).isEqualTo(10);
//    }
//}