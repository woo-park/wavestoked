package com.wavestoked.domain.announcement;

import com.wavestoked.domain.topic.Topic;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Announcement")
//@Table(name = "announcement")
@DiscriminatorValue("2")
public class Announcement extends Topic {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VALID_UNTIL")
    private Date validUntil;
    private String content;

}
