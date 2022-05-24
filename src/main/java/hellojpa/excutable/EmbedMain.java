package hellojpa.excutable;

import hellojpa.Address;
import hellojpa.Member;
import hellojpa.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmbedMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setName("member_name");
            Address address = new Address("seoul", "street", "101010");
            member.setAddress(address);
            member.setPeriod(new Period(LocalDate.now(), LocalDate.of(2021, 12, 1)));

            em.persist(member);

            address.setCity("Busan");

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
