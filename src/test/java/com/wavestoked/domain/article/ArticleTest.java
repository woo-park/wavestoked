import com.wavestoked.domain.article.Article;
import com.wavestoked.domain.article.ArticleRepository;
import com.wavestoked.domain.skin.Skin;
import com.wavestoked.domain.skin.SkinRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;           // added manually

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleTest {
    @Autowired
    SkinRepository skinRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Before
    public void setUp() throws Exception {
        Skin skin = Skin.builder()
                .name("SkinA")
                .build();

//        teamRepository.save(team);

        Article article = Article.builder()
                .author("author1")
                .skin(skin)
                .build();

        articleRepository.save(article);
    }

    @Test
    public void testSaving() throws Exception {
        Article article = articleRepository.findAll().get(0);
        String author = article.getAuthor();
        assertThat(author).isEqualTo("author1");

        Skin skin = article.getSkin();
        assertThat(skin.getName()).isEqualTo("SkinA");

        List<Article> articles = skin.getArticles();
        for (Article a : articles) {
            assertThat(a.getAuthor()).startsWith("author");
        }

    }


}