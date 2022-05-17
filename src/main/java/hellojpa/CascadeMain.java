package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CascadeMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            /**
             * 원래대로면 persist 메서드를 세번 호출해야 한다.
             * 영속성 전이 (CASCADE) 를 사용했기 때문에 child 도 모두 저장되었다.
             *
             * 주의사항
             * 소유자가 하나일때만 사용하는 것이 좋다. (EX: 게시판 첨부파일 등)
             * 라이프사이클이 유사할 때만 ALL 을 사용한다.
            **/
            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
