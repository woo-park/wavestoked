//package com.wavestoked.file.domain.repository;
//
//import kr.tenth.selec7.file.domain.model.TblFile;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface FileRepository extends JpaRepository<TblFile, Long> {
//
//    List<TblFile> findBySeqNoInAndDelCheckOrderByRegdate(List<Long> seqNo, int del_check);
//
//    List<TblFile> findBySeqNoInAndDelCheckOrderByRegdateDesc(List<Long> seqNo, int del_check);
//
//    TblFile findBySeqNoAndDelCheck(Long seqNo, int del_check);
//
//    List<TblFile> findBySeqNoAndSiteCateSeqAndDelCheck(Long seqNo,Long siteCateSeq, int del_check);
//
//    List<TblFile> findBySiteCateSeqAndDelCheckOrderByRegdateDesc(Long siteCateSeq, int del_check);
//
//    @Query(value = "select * from tbl_file AS b where b.del_check = 0 AND b.seq_no =:seqNo AND b.site_cate_seq =:siteCateseq  " , nativeQuery = true)
//    List<TblFile> findBySeqNoAndDelCheckAndSiteCateSeq(Long seqNo,Long siteCateseq);
//
//    @Query(value = "select COALESCE(Max(seq_no),0) as id from tbl_file " , nativeQuery = true)
//    Long getMaxMenuId();
//
//}
//
//
