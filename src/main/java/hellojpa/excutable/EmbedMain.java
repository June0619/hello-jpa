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
import java.util.List;
import java.util.Set;

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

            // 값 타입 컬렉션 예제
            // 값 타입 컬렉션들은 따로 persist 해주지 않아도 영속성 전이 + 고아 객체 제거가 기본으로 시행된다.
            member.getFavoriteFoods().add("chicken");
            member.getFavoriteFoods().add("pizza");
            member.getFavoriteFoods().add("rice");

            member.getAddressHistory().add(new Address("one", address.getStreet(), address.getZip()));
            member.getAddressHistory().add(new Address("two", address.getStreet(), address.getZip()));

            em.flush();
            em.clear();

            System.out.println("=================");
            //컬렉션 값타입들은 기본적으로 지연로딩이다.
            Member findMember = em.find(Member.class, member.getId());

            //값타입 컬렉션 수정
            //값타입 컬렉션은 아래 예시 처럼 간단한 내용에 관한 멀티 셀렉트박스
            //즉 변경내역을 추적하거나 저장할 필요도 없는 경우에만 사용해야 한다.
            findMember.getFavoriteFoods().remove("rice");
            findMember.getFavoriteFoods().add("noddle");

            //값타입 컬렉션에 변경사항이 발생하면 부모 엔티티와 관련된 데이터를 전부 다지우고
            //컬렉션에 마지막으로 남은 값들을 전부 insert 한다.
            //실무에서는 이렇게 히스토리를 남겨야 하는 경우 값타입 컬렉션보다 일대다 관계로 풀어내는것이 나음
            //EX) Address => AddressEntity
            findMember.getAddressHistory().remove(new Address("one", address.getStreet(), address.getZip()));
            findMember.getAddressHistory().add(new Address("three", address.getStreet(), address.getZip()));

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
