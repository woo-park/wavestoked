//package com.wavestoked.file.domain.repository;
//
//import kr.tenth.selec7.file.domain.model.TblFileAgency;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface FileAgencyRepository extends JpaRepository<TblFileAgency, Long> {
//
//    List<TblFileAgency> findBySeqNoInAndDelCheckOrderByRegdateDesc(List<Long> seqNo, int del_check);
//
//    TblFileAgency findBySeqNoAndDelCheck(Long seqNo, int del_check);
//
//    List<TblFileAgency> findBySeqNoAndSiteCateSeqAndDelCheck(Long seqNo,Long siteCateSeq, int del_check);
//
//    List<TblFileAgency> findBySiteCateSeqAndDelCheckOrderByRegdateDesc(Long siteCateSeq, int del_check);
//
//    @Query(value = "select * from tbl_file_agency AS b where b.del_check = 0 AND b.seq_no =:seqNo " , nativeQuery = true)
//    List<TblFileAgency> findBySeqNoAndDelCheckAndSiteCateSeq(Long seqNo);
//
//    @Query(value = "select COALESCE(Max(seq_no),0) as id from tbl_file_agency " , nativeQuery = true)
//    Long getMaxMenuId();
//
//}
//
//
