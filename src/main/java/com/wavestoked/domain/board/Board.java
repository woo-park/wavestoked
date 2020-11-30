package com.wavestoked.domain.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Board")
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    private String name;


}
