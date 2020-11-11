//package com.wavestoked.web;
//
//import com.wavestoked.domain.article.ArticleRepository;
//import com.wavestoked.domain.articleblock.ArticleBlock;
//import com.wavestoked.domain.articleblock.ArticleBlockRepository;
//import com.wavestoked.domain.skin.SkinRepository;
//import com.wavestoked.web.dto.ArticleBlockSaveRequestDto;
//import com.wavestoked.web.dto.ArticleBlockUpdateRequestDto;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class SkinControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    @Autowired
//    private SkinRepository skinRepository;
//
//
//
//    @After
//    public void tearDown() throws Exception {
//        articleRepository.deleteAll(); // jpaRepository default methods
//    }
//
////    @Test
////    public void ArticleBlock_update() throws Exception {
////        // save one before so that we can update with it
////        ArticleBlock savedArticle = articleBlockRepository.save(
////                ArticleBlock.builder()
////                .author("woo")
////                .articleString("<div>pre update block</div>")
////                .skinId(3)
////                .build()
////        );
////        Long updateId = savedArticle.getId();
////
////        String expectedArticleString = "<div>post update block</div>";
////        String expectedAuthor = "gyan";
////
////        ArticleBlockUpdateRequestDto requestDto = ArticleBlockUpdateRequestDto.builder()
////                .articleString(expectedArticleString)
////                .author(expectedAuthor)
////                .build();
////
////        String url = "http://localhost:" + port + "/api/articleBlock/update/" + updateId;  // dont forget last slash
////
////        HttpEntity<ArticleBlockUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
////
////        //when
////        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);
////
////        //then
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isGreaterThan(0L);
////
////
//////        Optional<ArticleBlock> updatedBlock = articleBlockRepository.findById(updateId);
////        ArticleBlock updateBlock = articleBlockRepository.findById(updateId)
////                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + updateId));
////
////        System.out.println("updateBlock");
////        System.out.println(updateBlock.getAuthor());
////        assertThat(updateBlock.getAuthor()).isEqualTo(expectedAuthor);
////        assertThat(updateBlock.getArticleString()).isEqualTo(expectedArticleString);
////
//////        List<Posts> all = postsRepository.findAll();
//////        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//////        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//////    */
////    }
//
//    @Test
//    public void Skin_getter() throws Exception {
//        String author_1 = "woo";
//        String articleString_1 = "<div>article1 html</div>";
//        int skinId_1 = 3;
//        articleSaveRequestDto requestDto = ArticleBlockSaveRequestDto.builder().articleString(articleString_1).author(author_1).skinId(skinId_1).build();
//
//        String author_2 = "gyan";
//        String articleString_2 = "<div>article2 html</div>";
//        int skinId_2 = 3;
//        ArticleBlockSaveRequestDto requestDto_2 = ArticleBlockSaveRequestDto.builder().articleString(articleString_2).author(author_2).skinId(skinId_2).build();
//
//        String url = "http://localhost:" + port + "/api/articleBlock/save";
//        HttpEntity<ArticleBlockSaveRequestDto> requestEntity = new HttpEntity<>(requestDto);
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        String url_2 = "http://localhost:" + port + "/api/articleBlock/save";
//        HttpEntity<ArticleBlockSaveRequestDto> requestEntity_2 = new HttpEntity<>(requestDto);
//        ResponseEntity<Long> responseEntity_2 = restTemplate.postForEntity(url_2, requestDto_2, Long.class);
//        assertThat(responseEntity_2.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity_2.getBody()).isGreaterThan(0L);
//
//        // saving done
//
//
//
//        String getSkin_url = "http://localhost:" + port + "/api/skin/{skinId}";
//    }
//
//
////    @Test
////    public void ArticleBlockDto_getter() throws Exception {
////        String author = "woo";
////        String articleString = "<div>dto Getter</div>";
////        int skinId = 3;
////
////        ArticleBlockSaveRequestDto requestDto = ArticleBlockSaveRequestDto.builder().articleString(articleString).author(author).skinId(skinId).build();
////
////        String url = "http://localhost:" + port + "/api/articleBlock/save";
////        HttpEntity<ArticleBlockSaveRequestDto> requestEntity = new HttpEntity<>(requestDto);
////
//////      Saving to begin with
////        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//////        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isGreaterThan(0L);
////
////        List<ArticleBlock> all = articleBlockRepository.findAll();
////        assertThat(all.get(0).getAuthor()).isEqualTo(author);
////        assertThat(all.get(0).getArticleString()).isEqualTo(articleString);
////        assertThat(all.get(0).getSkinId()).isEqualTo(skinId);
////        System.out.println(all.get(0).getId());
////
////        // after finish testing save articleBlock api,
////        // testing fetching through id
////        Long testId = all.get(0).getId();
////        String url2 = "http://localhost:" + port + "/api/articleBlockDto/{id}";
////
////        Map<String, Long> urlVariables = new HashMap<>();
////        urlVariables.put("id", testId);
////        System.out.println("all[0] saved entity id: " + testId);
////
////        //  //asks for Object; not responseEntity Object
////        // ArticleBlock dtoObject = restTemplate.getForObject(url2, ArticleBlock.class, urlVariables);      // just a reminder that dto is an object -> not entity -> so we must use getForObject when getting dtos -> whereas getForEntity returns responseEntity Object with response headers and etc.
////        // System.out.println("dtoObject:" + dtoObject.getArticleString());
////
////        // !important
////        // service returns dto -> dto grabbed through responseEntity Object
////        ResponseEntity<ArticleBlock> responseEntity2 = restTemplate.getForEntity(url2, ArticleBlock.class, urlVariables);
////        System.out.println("responseEntity:" + responseEntity2);
////        System.out.println(responseEntity2.getBody().getArticleString());
////
////        assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.OK);
//////        assertThat(responseEntity2.getBody()).isGreaterThan(0L);  // doesn't work
////        assertThat(responseEntity2.getBody().getAuthor()).isEqualTo("woo");
////        assertThat(responseEntity2.getBody().getArticleString()).isEqualTo(articleString);
////        assertThat(responseEntity2.getBody().getSkinId()).isEqualTo(skinId);
////    }
//
//
////    @Test
////    public void ArticleBlock_getter() throws Exception {
////        String author = "woo";
////        String articleString = "<div>hi</div>";
////        int skinId = 3;
////
////        ArticleBlockSaveRequestDto requestDto = ArticleBlockSaveRequestDto.builder().articleString(articleString).author(author).skinId(skinId).build();
////
////        String url = "http://localhost:" + port + "/api/articleBlock/save";
//////        HttpEntity<ArticleBlockSaveRequestDto> requestEntity = new HttpEntity<>(requestDto);
////
////
////        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//////        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isGreaterThan(0L);
////
////        List<ArticleBlock> all = articleBlockRepository.findAll();
////        assertThat(all.get(0).getAuthor()).isEqualTo(author);
////        assertThat(all.get(0).getArticleString()).isEqualTo(articleString);
////        assertThat(all.get(0).getSkinId()).isEqualTo(skinId);
////        System.out.println(all.get(0).getId());
////
////
////        Long testId = all.get(0).getId();
////        String url2 = "http://localhost:" + port + "/api/articleBlock/{id}";
////
////        Map<String, Long> urlVariables = new HashMap<>();
////        urlVariables.put("id", testId);
////        System.out.println("obj id: " + testId);
////
////        // !important
////        // service returns Entity -> which is actually not great... better to return dtos
////        ResponseEntity<ArticleBlock> responseEntity2 = restTemplate.getForEntity(url2, ArticleBlock.class, urlVariables);
////
////        assertThat(responseEntity2.getStatusCode()).isEqualTo(HttpStatus.OK);
//////        assertThat(responseEntity2.getBody()).isGreaterThan(0L);  // doesn't work
////        assertThat(responseEntity2.getBody().getAuthor()).isEqualTo(author);
////        assertThat(responseEntity2.getBody().getArticleString()).isEqualTo(articleString);
////        assertThat(responseEntity2.getBody().getSkinId()).isEqualTo(skinId);
////    }
//
////    @Test
////    public void ArticleBlock_saved() throws Exception {
////        String author = "woo";
////        String articleString = "<div>hi</div>";
////        int skinId = 3;
////
////        ArticleBlockSaveRequestDto requestDto = ArticleBlockSaveRequestDto.builder().articleString(articleString).author(author).skinId(skinId).build();
////
////        String url = "http://localhost:" + port + "/api/articleBlock/save";
////
////        HttpEntity<ArticleBlockSaveRequestDto> requestEntity = new HttpEntity<>(requestDto);
////        System.out.println("debugging here");
////        System.out.println(requestEntity);
////
////
////        /*
////        !important
////
////            @Getter
////            @Setter
////            public class ArticleBlockSaveRequestDto {
////
////            없으면 httpMessageConverter missing error 나온다
////
////        */
////
////        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//////        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isGreaterThan(0L);
////
////        List<ArticleBlock> all = articleBlockRepository.findAll();
////        assertThat(all.get(0).getAuthor()).isEqualTo(author);
////        assertThat(all.get(0).getArticleString()).isEqualTo(articleString);
////        assertThat(all.get(0).getSkinId()).isEqualTo(skinId);
////
////
////
//////        HttpEntity<ArticleBlockSaveRequestDto> requestEntity = new HttpEntity<>(requestDto);
//////        System.out.println(requestEntity);
//////        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);
//////
//////        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//////            System.out.println("response received");
//////            System.out.println(responseEntity.getBody());
//////        } else {
//////            System.out.println("error occurred");
//////            System.out.println(responseEntity.getStatusCode());
//////        }
////
////
////        /*
////            @ResponseBody
////
////            아래에서 User(객체)를 리턴할 때는 기본적으로 JsonMessageConverter가 사용이되고, String타입을 이턴할 때는 StringMessageConverter가 사용이 된다. int도 마찬가지로 StringMessageConverter이다.
////
////            @RestController면 @ResponseBody는 생략해도 된다.
////
////                    MessageConverter를 타고 객체를 응답 본문으로 바꾼다.
////
////                    그냥 @Controller를 사용할 경우에는 @ResponseBody를 넣어야 MessageConverter가 적용이된다.
////
////            @Controller에서 @ResponseBody를 선언하지 않으면 BeanNameViewResolver에 의해서 ViewName에 해당하는 뷰를 찾으려고 시도한다.
////
////            https://ict-nroo.tistory.com/98
////        */
////    }
//
//
//
//}
