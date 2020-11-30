package com.wavestoked.domain.topic;

import com.wavestoked.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Topic")
@Table(name = "topic")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.INTEGER,
        name = "topic_type_id",
        columnDefinition = "TINYINT(1)"
)
public class Topic {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String owner;

    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;


}
