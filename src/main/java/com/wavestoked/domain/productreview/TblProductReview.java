package com.wavestoked.domain.productreview;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@SequenceGenerator(
        name = "REVIEW_SEQ_GENERATOR",
        sequenceName = "reviewSeq", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1
)
public class TblProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQ_GENERATOR")
    @NotNull
    private Long seqNo;

    @Column
    private long userSeq;

    @NotNull
    private String title;

    @NotNull
    @Column(columnDefinition="LONGTEXT")
    private String content;

    @ColumnDefault("0") //default 0
    private int delCheck;

    //@NotNull
    @CreationTimestamp
    private LocalDateTime regDate;

    //@NotNull
    @UpdateTimestamp
    private LocalDateTime uptDate;
}
