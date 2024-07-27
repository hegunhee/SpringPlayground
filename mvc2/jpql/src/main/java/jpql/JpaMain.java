package jpql;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("teamA");

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);

            member.changeTeam(team);

            String query = "select " +
                    "case when m.age <= 10 then '학생요금' " +
                    "     when m.age >= 60 than '경로요금' " +
                    "     else '일반요금 " +
                    "from Member m";
            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            System.out.println("result.size() = " + result.size());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);


    }
}
