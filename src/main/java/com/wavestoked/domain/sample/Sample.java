package com.wavestoked.domain.sample;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sample {
    @Id
    @GeneratedValue
//    @Column(name = "TEST_ID")
    private long id;
}


