
import jqpl.Member;
import jqpl.MemberDTO;
import jqpl.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try {

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("상진");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("상진1");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("상진2");
            member3.setTeam(teamB);
            em.persist(member3);

           em.flush();
           em.clear();

           String query ="select t From Team t join fetch t.member";

           List<Team> result = em.createQuery(query, Team.class)
                           .getResultList();

           for (Team team : result){
               System.out.println("team = " + team.getName() + " | members =" + team.getMember().size());
           }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

        emf.close();

    }
}
