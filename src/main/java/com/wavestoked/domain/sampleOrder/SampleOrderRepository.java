package com.wavestoked.domain.sampleOrder;

import com.wavestoked.domain.sample.Sample;
import kr.tenth.selec7.order.domain.model.TblOrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*
public interface OrderListRepository extends JpaRepository<TblOrderInfo, Long> {

    List<TblOrderInfo> findBySiteCateSeqAndDeliveryStateOrderByRegdateDesc(Long siteCateSeq,int deliveryState);

    List<TblOrderInfo> findBySiteCateSeqAndDeliveryStateAndSeqNoInOrderByRegdateDesc(Long siteCateSeq,int deliveryState,List<Long> seqNo);

    //전체
    Page<TblOrderInfo> findBySiteCateSeqAndDeliveryStateInOrderByRegdateDesc(Pageable pageable, Long siteCateSeq,List<Integer> deliveryStateList);
//
//    //전체 cate type
//    Page<TblOrderInfo> findByResellSeqAndGroupSeqAndDelCheckInOrderByRegDateDesc(Pageable pageable,int agencySeq,Long groupSeq, List<Integer> delCheck);
//
//    //전체 cate type + 에이전트
//    Page<TblOrderInfo> findByResellSeqAndDesignerSeqAndGroupSeqAndDelCheckInOrderByRegDateDesc(Pageable pageable,int agencySeq,Long agentSeq,Long groupSeq, List<Integer> delCheck);
//
//    //전체 에이전트
//    Page<TblOrderInfo> findByResellSeqAndDesignerSeqAndDelCheckInOrderByRegDateDesc(Pageable pageable,int agencySeq,Long agentSeq, List<Integer> delCheck);

}

*/

public interface SampleOrderRepository extends JpaRepository<SampleOrder, Long> {

    List<SampleOrder> findBySiteCateSeqAndDeliveryStateOrderByRegdateDesc(Long siteCateSeq,int deliveryState);

    List<SampleOrder> findBySiteCateSeqAndDeliveryStateAndSeqNoInOrderByRegdateDesc(Long siteCateSeq,int deliveryState,List<Long> seqNo);

    //전체
    Page<SampleOrder> findBySiteCateSeqAndDeliveryStateInOrderByRegdateDesc(Pageable pageable, Long siteCateSeq,List<Integer> deliveryStateList);
/*
    //전체 cate type
    Page<TblOrderInfo> findByResellSeqAndGroupSeqAndDelCheckInOrderByRegDateDesc(Pageable pageable,int agencySeq,Long groupSeq, List<Integer> delCheck);

    //전체 cate type + 에이전트
    Page<TblOrderInfo> findByResellSeqAndDesignerSeqAndGroupSeqAndDelCheckInOrderByRegDateDesc(Pageable pageable,int agencySeq,Long agentSeq,Long groupSeq, List<Integer> delCheck);

    //전체 에이전트
    Page<TblOrderInfo> findByResellSeqAndDesignerSeqAndDelCheckInOrderByRegDateDesc(Pageable pageable,int agencySeq,Long agentSeq, List<Integer> delCheck);*/

}
