package com.wavestoked.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRepository extends JpaRepository<Posts, Long> {   //Entity class 와 Entity repository class는 같은 path
    // Posts is Entity class
    // since these two are a couple, they are stored in 'domain' package
}


