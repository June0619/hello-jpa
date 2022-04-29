package hellojpa.section1;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Flush {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        javax.persistence.EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Flush 실행 조건
        //1. em.flush()
        //2. tx.commit()
        //3. JPQL 쿼리 실행

        Member member1 = new Member(200L, "new Member");
        em.persist(member1);
        // DB에 쿼리가 직접 나가지만 1차 캐시가 비워지는 것은 아님
        em.flush();

        System.out.println("===================");

        Member member2 = new Member(201L, "new Member");
        em.persist(member2);

        System.out.println("===================");

        Member member3 = new Member(202L, "new Member");
        Member member4 = new Member(203L, "new Member");
        em.persist(member3);
        em.persist(member4);

        // 쿼리가 실행 되기 전에 member3 과 member4 를 저장하는 쿼리 실행
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        List<Member> resultList = query.getResultList();

        System.out.println("resultList = " + resultList);

        tx.commit();
        em.close();
        emf.close();
    }






}
