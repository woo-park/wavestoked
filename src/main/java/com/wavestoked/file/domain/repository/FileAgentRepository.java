//package com.wavestoked.file.domain.repository;
//
//import kr.tenth.selec7.file.domain.model.TblFileAgent;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface FileAgentRepository extends JpaRepository<TblFileAgent, Long> {
//
//    List<TblFileAgent> findBySeqNoInAndDelCheckOrderByRegdateDesc(List<Long> seqNo, int del_check);
//
//    TblFileAgent findBySeqNoAndDelCheck(Long seqNo, int del_check);
//
//    @Query(value = "select * from tbl_file_agency AS b where b.del_check = 0 AND b.seq_no =:seqNo " , nativeQuery = true)
//    List<TblFileAgent> findBySeqNoAndDelCheckAndSiteCateSeq(Long seqNo);
//
//    @Query(value = "select COALESCE(Max(seq_no),0) as id from tbl_file_agent " , nativeQuery = true)
//    Long getMaxMenuId();
//
//}
//
//
