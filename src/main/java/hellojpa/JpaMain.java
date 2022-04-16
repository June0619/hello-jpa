package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            /** Member Insert **/
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("memberB");
//            em.persist(member);
//
            /** Member Select One **/
            Member findMember = em.find(Member.class, 1L);

            /** Member Update **/
//            findMember.setName("Hello Jpa");

            /** Member Select List **/
            List<Member> memberList
                    = em.createQuery("select m from Member as m", Member.class).getResultList();

            tx.commit();

            for (Member one : memberList) {
                System.out.println("findMember.id = " + one.getId());
                System.out.println("findMember.name = " + one.getName());
            }

        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
