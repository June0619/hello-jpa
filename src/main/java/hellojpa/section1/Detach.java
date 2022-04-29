package hellojpa.section1;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Detach {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        javax.persistence.EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = em.find(Member.class, 1L);
        member.setName("updatedName");

        // 준영속 상태(영속성에서 제외된 상태)
        em.detach(member);

        // 엔티티 매니저 안의 영속성 컨텍스트 통째로 날아감
        // 1차 캐쉬 자체가 비워짐
        // em.close();

        tx.commit();
        em.close();
        emf.close();
    }


}
