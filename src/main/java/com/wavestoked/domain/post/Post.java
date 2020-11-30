package com.wavestoked.domain.post;

import com.wavestoked.domain.board.Board;
import com.wavestoked.domain.topic.Topic;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Post")
//@Table(name = "post")
@DiscriminatorValue("1")
public class Post extends Topic {
    private String content;

}
