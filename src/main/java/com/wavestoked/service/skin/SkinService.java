//package com.wavestoked.service.skin;
//
//import com.wavestoked.domain.skin.Skin;
//import com.wavestoked.domain.skin.SkinRepository;
//import com.wavestoked.web.dto.ArticleBlockSaveRequestDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Service
//public class SkinService {
//    private final SkinRepository skinRepository;
//
////    @Transactional
////    public Long save(SkinSaveDto requestDto) {
////        return skinRepository.save(requestDto.toEntity()).getId();
////        // 1. tells requestDto to convert to entity
////        // 2. tells extended jpaRepository to save the converted entity
////        // 3. returns the entity's id by using getId() lombok method
////    }
//
//    @Transactional(readOnly = true)
//    public List<String> findAllArticleString(){
//        return extractArticleString(skinRepository.findAll());
//    }
//
//    /**
//     * Lazy Load를 수행하기 위해 메소드를 별도로 생성
//     */
//    private List<String> extractArticleString(List<Skin> skins){
////        log.info(">>>>>>>>[모든 과목을 추출한다]<<<<<<<<<");
////        log.info("Skins Size : {}", skins.size());
//        System.out.println("Skins Size : {}");
//        System.out.println(skins.size());
//
//        return skins.stream()
//                .map(a -> a.getArticleBlocks().get(0).getArticleString())
//                .collect(Collectors.toList());
//    }
//
//}
