package hellojpa.section1;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManager {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        javax.persistence.EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin(); // 트랜잭션 시작

        try {
            //객체를 생성한 상태(비영속)
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            System.out.println("=== Before ===");
            //객체를 저장한 상태(영속)
            em.persist(member);
            System.out.println("=== After ===");
            //INSERT SQL을 데이터베이스에 보내지 않는다
            tx.commit();
            //트랜잭션의 커밋이 발생하는 시점에 INSERT SQL을 보낸다.

            //회원 엔티티를 영속성 컨텍스트에서 분리(준영속 상태)
//            em.detach(member);
            //객체를 데이터베이스에서 삭제
//            em.remove(member);

            Member memberA = em.find(Member.class, 100L);
            // 두번째 조회부터는 1차 캐시에서 조회
            Member memberB = em.find(Member.class, 100L);

            boolean entityEquality = memberA == memberB; // True
            System.out.println("entityEquality = " + entityEquality);


        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
