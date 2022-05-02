package hellojpa.section2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Connection {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //Member Table의 TEAM_ID 컬럼에 값이 누락된다.
            //연관관계의 주인이 Member 이기 때문
            //Team 객체의 mapped by 컬럼은 읽기 전용이다.
//            Member member = new Member();
//            member.setMemberName("name1");
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("teamName1");
//            team.getMembers().add(member);
//            em.persist(team);

            // 아래 코드엔 정상적으로 연관관계가 INSERT 된다.
            Team team = new Team();
            team.setName("teamName1");
            em.persist(team);

            Member member = Member.builder()
                    .name("memberName1")
                    .team(team)
                    .build();

            em.persist(member);

            //Team 객체에 member를 넣어주지 않아도 JPA 상에서는 아무런 문제가 없어보인다.
            //하지만 1차캐쉬의 값을 사용하기 위해서는 양방향 객체 모두 매핑해주는게 맞다.
            //디자인 패턴상으로도 옳다. - 추후 테스트 코드 작성 시 필요
            //양쪽을 다 매핑하는것을 잊어버릴 수도 있기 때문에 연관관계 편의 메서드를 작성해야 함
//            team.getMembers().add(member);
//            em.flush();
//            em.close();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("members.toString() = " + members.toString());

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }


    }


}
