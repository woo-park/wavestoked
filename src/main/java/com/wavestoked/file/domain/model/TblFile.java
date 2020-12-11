package com.wavestoked.file.domain.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class TblFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long seqNo;

    private long siteCateSeq;

    private long fileSize;

    private String fileNameExtension;

    @Column(length = 512, nullable = false)
    private String fileNameOri;

    @Column(length = 512, nullable = false)
    private String fileNameChange;

    @ColumnDefault("0") //default 0
    private int delCheck;

    //@NotNull
    @CreationTimestamp
    private LocalDateTime regdate;

    //@NotNull
    @UpdateTimestamp
    private LocalDateTime uptdate;


}
