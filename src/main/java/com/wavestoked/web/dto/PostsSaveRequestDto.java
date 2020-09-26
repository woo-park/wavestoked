package com.wavestoked.web.dto;


import com.wavestoked.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    // Entity클라스를 변경하는건 너무 큰 변경
    // 비즈니스 logic 들이 Entity class 기준으로 동작

    // Request 와 Response 용 Dto 는 View Layer을 위한 class라 정말 자주 변경이 필요 합니다.
    // View Layer 와 DB Layer의 역할 분리를 철저히 할것
    // 꼭 Entity 클라스와 Controller에서 쓸 Dto 는 분리할것

}
