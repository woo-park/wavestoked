package com.wavestoked.domain.articleblock;

import com.wavestoked.domain.posts.Posts;
import com.wavestoked.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ArticleBlockRepository extends JpaRepository<ArticleBlock, Long>  {
//    @Query("SELECT p " +
//            "FROM ArticleBlock p " +
//            "ORDER BY p.id DESC")
//    Stream<ArticleBlock> findAllDesc();
//    Optional<ArticleBlock> findByAuthor(String id);

//    @Query("SELECT p " +
//            "FROM ArticleBlock")
//    List<ArticleBlock> findAllItems();

    /*
    * @Query("SELECT p " +
                "FROM Posts p " +
                "ORDER BY p.id DESC")
        Stream<Posts> findAllDesc();

    *
    *
    *
    * */
//    Optional<User> findByEmail(String email);
    Optional<ArticleBlock> findById(Long id);
}
