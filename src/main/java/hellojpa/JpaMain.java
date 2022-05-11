package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Movie movie = new Movie();

            movie.setName("movie");
            movie.setPrice(10000);
            movie.setDirector("Man");
            movie.setActor("girl");

            em.persist(movie);

            em.flush();
            em.clear();

            // TABLE PER CLASS 전략일 경우
            // 부모 타입으로 조회 시 UNION 을 걸어 전체 테이블을 다 조회한다.
            Item item = em.find(Item.class, movie.getId());
            System.out.println("findMovie = " + item);

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
