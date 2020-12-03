package com.wavestoked.domain.sampleOrder;


import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@Builder
public class SampleOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seqNo;

    //PGNumber
    @NotNull
    private String pgNumber;

    //상점 번호
    @NotNull
    @ColumnDefault("0") //default 0
    private Long siteCateSeq;

    //총 금액
    @NotNull
    @ColumnDefault("0") //default 0
    private Long totalPrice;

    //상품 제목
    @NotNull
    private String title;

    //결제 방식
    @NotNull
    private String pgCode;

    //PayLetter 토큰
    private String pgToken;

    //유저번호
    @NotNull
    private Long userSeq;

    //받는분
    @Column(length = 100)
    private String recvName;

    //이메일
    @Column(length = 100)
    private String email;

    //주소
    @Column(length = 512)
    private String address1;

    //상세주소
    @Column(length = 512)
    private String detailAddress1;

    //받는분 전화번호
    @Column(length = 20)
    private String recvPhone;

    //우편번호
    @Column(length = 6)
    private String postcode;

    //배송 요청 사항
    @Column(length = 512)
    private String memo;

    //주문자이름
    @Column(length = 100)
    private String reqName;

    //주문자 전화번호
    @Column(length = 20)
    private String reqPhone;


    //배송 상태
    //0 : 결제 전
    //1 : 입금 대기
    //2 : 결제 완료
    //3 : 배송 준비
    //4 : 배송 완료
    //5 : 배송 지연
    //6 : 구매 확정
    //7 : 반품 요청
    //8 : 반품 완료
    //9 : 환불 완료
    @NotNull
    @ColumnDefault("0")
    private Integer deliveryState;

    //배송비
    private Long deliveryFee;

    //추가 배송비
    private Long extraDeliveryFee;

    //삭제
    @NotNull
    @ColumnDefault("0") //default 0
    private int delCheck;

    //PayLetter 현금 영수증 정보
    private String cash_receipt;

    @Column(length=100)
    private String productImgUrl;

    //PayLetter TID
    @Column(length=40)
    private String tid;

    //PayLetter CID
    @Column(length=40)
    private String cid;

    //카드정보
    @Column(length = 50)
    private String payInfo;

    //택배사 이름
    @Column(length = 20)
    private String expressCompany;

    //송장번호
    @Column(length = 20)
    private String expressNumber;


    @Column(columnDefinition="MEDIUMTEXT")
    private String orderAdminMemo;


    @Column(length = 255)
    private String userAgent;

    @Column(length = 40)
    private String userIP;


    @CreationTimestamp
    private LocalDateTime regdate;

    @UpdateTimestamp
    private LocalDateTime uptdate;
}
