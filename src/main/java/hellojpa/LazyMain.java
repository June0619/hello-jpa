package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class LazyMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("LAZY Team");
            em.persist(team);

            Group group = new Group();
            group.setName("EAGER Group");
            em.persist(group);

            Member member = new Member();
            member.setName("LAZY member");
            member.setTeam(team);
            member.setGroup(group);
            em.persist(member);

            em.flush();
            em.clear();

            //LAZY 지연로딩을 통해 Team 객체는 프록시로 가져오게 된다.
            //EAGER 로딩을 통해 Group 객체는 바로 가져온다.
            /**
             * 실무에서는 즉시로딩(EAGER) 는 거의 사용하면 안된다.
             * 1. 즉시로딩은 예상치 못한 SQL 이 대량으로 발생한다.
             * 2. 즉시로딩은 JPQL 에서 N+1 문제를 야기한다.
             * **/

            /*
             JPQL 사용 시 쿼리가 두번 나감
             JPQL 은 기본적으로 쿼리가 그대로 나가기 때문에 Member 객체만 조회한다.
             즉 프록시 객체를 가져온 후 즉시로딩에 맞는 객체를 한번 더 조회한다.
             List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                                .getResultList();
            */

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("team = " + findMember.getTeam().getClass());
            System.out.println("group = " + findMember.getGroup().getClass());
            System.out.println("====================================");
            // Team 프록시 객체의 데이터를 건드리는 순간 쿼리가 발생한다.
            String teamName = findMember.getTeam().getName();


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
