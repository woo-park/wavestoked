package com.wavestoked.domain.articleblock;


import com.wavestoked.domain.posts.PostsRepository;
import com.wavestoked.domain.posts.Posts;

import com.wavestoked.web.dto.ArticleBlockSaveRequestDto;
import org.junit.After;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleBlockRepositoryTest {

    @Autowired
    ArticleBlockRepository articleBlockRepository;

    @After                      // empties out memory from h2 db
    public void cleanup() {
        articleBlockRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String author = "woo";
        String articleString = "<div>hi</div>";
        int skinId = 3;

        articleBlockRepository.save(ArticleBlockSaveRequestDto.builder()
                .articleString(articleString)
                .author(author)
                .skinId(skinId)
                .build().toEntity()
        );

        List<ArticleBlock> articlesList = articleBlockRepository.findAll();

        ArticleBlock all = articlesList.get(0);
        assertThat(all.getAuthor()).isEqualTo(author);
        assertThat(all.getArticleString()).isEqualTo(articleString);
        assertThat(all.getSkinId()).isEqualTo(skinId);
    }
}
