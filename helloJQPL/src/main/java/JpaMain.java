
import jqpl.Member;

import javax.persistence.*;

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

            TypedQuery<Member> query = em.createQuery("select m from Member m ", Member.class);

            /* Member result = em.createQuery("select m from Member m where m.username = ?1", Member.class)
                    .setParameter("username","member1")
                    .getSingleResult();*/

            Member result = query.getSingleResult();
            System.out.println("result ="+result);


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
