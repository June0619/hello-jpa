package hellojpa.excutable;

import hellojpa.Child;
import hellojpa.Parent;

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

            /**
             * Parent 객체에 설정한 orphanRemoval 속성으로 인해
             * 연관관계가 제거되면 Delete 쿼리가 발생한다.
             *
             * 주의사항
             * 영속성 전이와 마찬가지로 참조하는 곳이 하나일때만 사용해야 함
             *
             * CASCADE.REMOVE 처럼 사용한다.
             * **/

            Parent findParent = em.find(Parent.class, parent.getId());
            // 연관관계가 제거되거나 부모가 제거되는 경우 고아객체가 모두 삭제 됨
//            findParent.getChildList().remove(0);
            em.remove(parent);

            /**
             * 영속성 전이와 고아객체 관리를 모두 사용하면 얻는 이점
             * - 부모 엔티티를 통해 자식 엔티티의 생명 주기를 관리할 수 있음
             * -> DDD (Domain Driven Design) 의 Aggregate Root 구현에 유용
             * -> 쉽게 말해 불필요한 Repository , DAO 가 생략될 수 있음
             * **/

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
