package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

//            Movie movie = new Movie();
//
//            movie.setName("movie");
//            movie.setPrice(10000);
//            movie.setDirector("Man");
//            movie.setActor("girl");
//
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie);

            Member member = new Member();
            member.setName("Tes");
            member.setCreatedBy("ME");
            member.setCreatedAt(LocalDateTime.now());
            member.setModifiedBy("YOU");
            member.setModifiedAt(LocalDateTime.now());

            em.persist(member);

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
