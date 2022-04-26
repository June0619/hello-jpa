package hellojpa;

import RoleType.RoleType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ColumnMapping {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member memberA = new Member();
            memberA.setName("A");
            memberA.setId(1L);
            memberA.setRoleType(RoleType.USER);


            Member memberB = new Member();
            memberB.setName("B");
            memberB.setId(2L);
            memberB.setRoleType(RoleType.ADMIN);

            Member memberC = new Member();
            memberC.setName("C");
            memberC.setId(3L);
            memberC.setRoleType(RoleType.GUEST);
            // GUEST ENUM 이 나중에 추가되는 경우
            // EnumType.ORDINAL 으로 설정되어 있으면 번호가 밀려서 큰 장애가 날 수 있음

            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);

            tx.commit();
        }
        catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }


}
