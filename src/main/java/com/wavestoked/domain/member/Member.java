//package com.wavestoked.domain.member;
//
//
//import com.wavestoked.domain.order.Order;
////import com.wavestoked.domain.team.Team;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Data
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//public class Member {
//    @Id @GeneratedValue
//    @Column(name = "MEMBER_ID")
////    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ID_GENERATOR")
//    @NotNull
//    private Long id;
//
//    private String name;
//
//    private String city;
//    private String street;
//    private String zipcode;
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<com.wavestoked.domain.order.Order> orders = new ArrayList<Order>();
//
////    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    @JoinColumn(name="TEAM_ID")
////    private Team team;
//
//}
//
////
////public void testing() {
////
////
////    Team team1 = new Team('blah', 'blah');
////
////    Member member1 = new Member('blah', 'blah');
////
////    Member member2 = new Member('blah', 'blah');
////
////    member1.setTeam(team1);
////    member2.setTeam(team1);
////
////    /*
////       toString의 recursion을 주의하자 -> json api중에 막는방법이 있다
////
////     * 하지만 team getMembers하면 null로 나온다 그래서
////
////     setTeam을 override해야하는데
////     setTeam(team) {
////        if(team.getMembers() != null) {
////            team.getMembers() // remove -> this.member
////
////        }
////        그다음에
////        this.team = team;
////        team.getMembers().add(this) // this member
////     }
////
////     이렇게하면 member에서도 team -> set read 둘다되고, team 에서는 get/ read만 된다
////     *
////     */
////    /*
////    * member 가 owner of this relationship
////    * member가 set 와 get을 더 많이하기 때문에
////    * member가 set을 통해 수시로 team을 바꿀수있기때문에
////    *
////    * 그래서 member에서 joinColumn() Team의 id
////    *
////    * Team쪽에서는 mappedBy()
////    *
////    *
////    * */
////
////
////}