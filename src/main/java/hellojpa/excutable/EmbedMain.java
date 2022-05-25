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

            // 같은 값타입 인스턴스를 공유하면 안된다.
            // 자바에서는 객체타입의 공유 참조를 막을 방법이 없다.
            // 따라서 값 타입은 불변객체로 설계해야 한다.
//            Member member2 = new Member();
//            member2.setName("member2");
//            member2.setAddress(address);
//            em.persist(member2);
            // Setter 를 제외해버린다.
//            member2.getAddress().setCity("Busan");

            // 그렇다면 값을 변경하고 싶으면 어떻게 할까?
            // 귀찮더라도 값타입 객체를 새로 생성하는 것이 맞다.
            Address newAddress = new Address("new_city", address.getStreet(), address.getZip());
            member.setAddress(newAddress);

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
