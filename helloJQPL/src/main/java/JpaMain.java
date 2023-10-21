
import jqpl.Member;
import jqpl.MemberDTO;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("상진");
            member.setAge(10);
            em.persist(member);

            List<MemberDTO> result = em.createQuery("select new jqpl.MemberDTO(m.username, m.age) from Member m ", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = result.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO = " + memberDTO.getAge());

            /* Member result = em.createQuery("select m from Member m where m.username = ?1", Member.class)
                    .setParameter("username","member1")
                    .getSingleResult();*/



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
