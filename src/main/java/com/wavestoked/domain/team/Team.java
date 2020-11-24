package com.wavestoked.domain.team;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@NoArgsConstructor
@SequenceGenerator(
        name = "TEAM_ID_GENERATOR",
        sequenceName = "teamId", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1
)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_ID_GENERATOR")
    @NotNull
    private Long id;

    private String name;

}
