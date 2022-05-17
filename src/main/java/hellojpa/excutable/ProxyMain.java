package hellojpa.excutable;

import hellojpa.Member;
import hellojpa.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setName("test member");

            em.persist(member);

            em.flush();
            em.clear();

            // 만일 프록시 객체 사용 전에 find 메서드로 member 엔티티를 한번 호출하면
            // getReference 메서드를 호출해도 프록시 객체가 아닌 엔티티 객체체 사용한다.
//            Member findMember = em.find(Member.class, member.getId());
            Member findMemberProxy = em.getReference(Member.class, member.getId());

            // 프록시 객체가 발생한다.
            // class hellojpa.Member$HibernateProxy$rYfPlJl6
            System.out.println("before findMember.getClass() = " + findMemberProxy.getClass());
            System.out.println("findMember.getId() = " + findMemberProxy.getId());
            // getReference 메서드로 findMember 를 호출했을 때는 이 시점부터 쿼리가 나간다.
            System.out.println("findMember.getName() = " + findMemberProxy.getName());

            // 프록시 객체가 초기화 되더라도 교체되는 것이 아니고 타겟에만 값이 채워지는 것이다.
            System.out.println("after findMember.getClass() = " + findMemberProxy.getClass());

            // 프록시 객체는 바뀌는게 아니므로 타입 체크에 주의해야 한다.
            // 반드시 instanceof 로 체크할 것
            System.out.println(findMemberProxy.getClass() == member.getClass());
            System.out.println(findMemberProxy instanceof Member);


            //JPA 에서는 객체가 프록시 객체이던 엔티티 객체이던 같은 데이터 값에 대한 Equal 비교는 항상 True 를 반환한다.
            //엔티티 객체를 먼저 호출됐다면, 뒤에 프록시 객체를 호출할 경우라도 엔티티 객체가 호출되고
            //프록시 객체가 먼저 호출됐다면, 뒤에 엔티티 객체를 호출할 경우라도 프록시 객체가 호출된다.
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("[findMember == findMemberProxy] : " + (findMember == findMemberProxy));
            System.out.println("findMember = " + findMember.getClass());
            System.out.println("findMemberProxy = " + findMemberProxy.getClass());

             // == Error Code ==
             Member member2 = new Member();
             member2.setName("member2");
             em.persist(member2);

             em.flush();
             em.clear();

             Member memberProxy2 = em.getReference(Member.class, member2.getId());
            /*
             영속성 컨텍스트를 비운 후 프록시 객체에 접근하면 예외가 발생한다.
             실무에서 자주 마주치게 되는 예외상황이다.
             em.detach(memberProxy2);
             em.clear();
            */

            System.out.println("[AFTER] isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(memberProxy2));
            String member2Name = memberProxy2.getName();
            System.out.println("[BEFORE] isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(memberProxy2));


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void printMember(Member member) {
        String memberName = member.getName();
        System.out.println("memberName = " + memberName);
    }

    private static void printMemberAndTeam(Member member) {
        String memberName = member.getName();
        System.out.println("memberName = " + memberName);

        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());
    }


}
