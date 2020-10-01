package com.wavestoked.domain.posts;

import com.wavestoked.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.stream.Stream;


public interface PostsRepository extends JpaRepository<Posts, Long> {   //Entity class 와 Entity repository class는 같은 path
    // Posts is Entity class
    // since these two are a couple, they are stored in 'domain' package
    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.id DESC")
    Stream<Posts> findAllDesc();


}


