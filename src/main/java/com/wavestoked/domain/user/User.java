package com.wavestoked.domain.user;

import com.wavestoked.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
//@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;     //not sure how picture is a string

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)       //empty false
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) { //using the private field variables
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }                       // now you have .save and .etc methods provided by lombok

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {    // interesting
        return this.role.getKey();  // getKey method is provided by lombok
    }

}
