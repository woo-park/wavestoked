package com.wavestoked;

import com.wavestoked.domain.article.Article;
import com.wavestoked.domain.member.Member;
import com.wavestoked.domain.ord.Ord;
//import com.wavestoked.domain.ord.Order;
import com.wavestoked.domain.skin.Skin;
import com.wavestoked.domain.skin.SkinRepository;
import com.wavestoked.service.article.ArticleService;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@EnableJpaAuditing  // JPA Auditing 활성화
@SpringBootApplication
public class Application {

//    @Autowired
//    SkinRepository skinRepository;

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("wavestoked");

    public static void main(String[] args) {
        final SkinRepository skinRepository;

        SpringApplication.run(Application.class, args);
        testORM_양방향_리팩토링();
    }


    public static void testORM_양방향_리팩토링( ) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx1 = em.getTransaction();
        tx1.begin();


        System.out.println("hiii");
        Member member = Member.builder().city("ny").name("w").street("onelamp").zipcode("10019").build();
//        member.setCity("ny");
        Ord ord = new Ord();


        Skin skin = Skin.builder()
                .name("test em skin")
                .build();


        Article article = Article.builder()
                .author("test em author")
                .skin(skin)
                .build();

        System.out.println("emmmm");
        System.out.println(em);
//        em.persist(skin);
//        em.persist(article);

//        ord.setStatus("CANCEL");


//        member.getOrders().add(ord);
        ord.setMember(member);
        System.out.println("testing");
        System.out.println(ord);

        em.persist(member);
//        em.persist(ord);

        tx1.commit();

//
//        List<Ord> resultList =
//                em.createNamedQuery("Ord.findByStatus", Ord.class)
//                        .setParameter("status", "CANCEL")
//                        .getResultList();
//
//

        em.close();







    }




}


