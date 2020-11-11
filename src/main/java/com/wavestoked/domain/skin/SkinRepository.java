package com.wavestoked.domain.skin;

import com.wavestoked.domain.article.Article;
import com.wavestoked.domain.articleblock.ArticleBlock;
import com.wavestoked.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkinRepository extends JpaRepository<Skin ,Long> {

//    Optional<User> findByEmail(String email);
//    Optional<ArticleBlock> findById(Long id);
//    Optional<Article> findArticlesById(Long id);
//
//    List<Article> getArticles();
}
