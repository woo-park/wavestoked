package com.wavestoked.service.posts;


import com.wavestoked.domain.posts.Posts;
import com.wavestoked.domain.posts.PostsRepository;
import com.wavestoked.web.dto.PostsResponseDto;
import com.wavestoked.web.dto.PostsSaveRequestDto;
import com.wavestoked.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();     // getId() lombok method
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;  // after updating, we return the updated column's id
    }

    public PostsResponseDto findById(Long id) {         // returns Dto from repository Db
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostsResponseDto(entity);        // adding a layer of abstraction to the found entity(Posts)
    }
    // spring 에서 bean을 주고 받는방법은 @Autowired, setter, constructor
}
