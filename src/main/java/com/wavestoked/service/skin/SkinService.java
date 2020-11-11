//package com.wavestoked.service.skin;
//
//import com.wavestoked.domain.article.Article;
//import com.wavestoked.domain.articleblock.ArticleBlock;
//import com.wavestoked.domain.skin.Skin;
//import com.wavestoked.domain.skin.SkinRepository;
//import com.wavestoked.web.dto.ArticleBlockSaveRequestDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Slf4j
//@Service
//public class SkinService {
//    private final SkinRepository skinRepository;
//
//    @Transactional(readOnly = true)
//    public List<Article> findArticlesById(Long id) {
//        return skinRepository.getArticles();
//    }
//
////    public Skin findById(Long id) {         // returns Dto from repository Db
////         entity = articleBlockRepository.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException("해당 아티클 블락이 없습니다. id=" + id));
////        System.out.println("ENTit");
////        System.out.println(entity.getAuthor());
////        System.out.println(entity);
//////        return new PostsResponseDto(entity);
////        return entity;        // adding a layer of abstraction to the found entity(Posts)
//
//
//
//
////    @Transactional
////    public Long save(SkinSaveDto requestDto) {
////        return skinRepository.save(requestDto.toEntity()).getId();
////        // 1. tells requestDto to convert to entity
////        // 2. tells extended jpaRepository to save the converted entity
////        // 3. returns the entity's id by using getId() lombok method
////    }
//
////    @Transactional(readOnly = true)
////    public List<String> findAllArticleAuthor(){
////        return extractArticleAuthor(skinRepository.findAll());
////    }
////
////    /**
////     * Lazy Load를 수행하기 위해 메소드를 별도로 생성
////     */
////    private List<String> extractArticleAuthor(List<Skin> skins){
//////        log.info(">>>>>>>>[모든 과목을 추출한다]<<<<<<<<<");
//////        log.info("Skins Size : {}", skins.size());
////        System.out.println("Skins Size : {}");
////        System.out.println(skins.size());
////
////        List<String> articles = skins.stream().map(a -> a.toString()).collect(Collectors.toList());
////        System.out.println(articles);
////        /*
////       [Skin{id=1, name='스킨0', articles=[]}, Skin{id=2, name='스킨1', articles=[]}, Skin{id=3, name='스킨2', articles=[]},
////       Skin{id=4, name='스킨3', articles=[]}, Skin{id=5, name='스킨4', articles=[]}, Skin{id=6, name='스킨5', articles=[]},
////       Skin{id=7, name='스킨6', articles=[]}, Skin{id=8, name='스킨7', articles=[]}, Skin{id=9, name='스킨8', articles=[]},
////       Skin{id=10, name='스킨9', articles=[]}]
////        * */
////
////        return skins.stream()
////                .map(skin -> skin.getArticles().get(0).getAuthor())
////                .collect(Collectors.toList());
////    }
//
//}
