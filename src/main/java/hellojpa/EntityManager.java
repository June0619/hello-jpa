package hellojpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManager {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        javax.persistence.EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //객체를 생성한 상태(비영속)
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //객체를 저장한 상태(영속)
            em.persist(member);
            //회원 엔티티를 영속성 컨텍스트에서 분리(준영속 상태)
//            em.detach(member);
            //객체를 데이터베이스에서 삭제
//            em.remove(member);


        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
