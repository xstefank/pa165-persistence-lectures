package io.xstefank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//            Employee luke = new Employee("Luke", 20000);
//            em.persist(luke);

//            Employee leia = new Employee("Leia", 22000);
//            em.persist(leia);

//            Employee employee = em.find(Employee.class, 1l);
//            em.createQuery("delete from Employee e where e.id = 2").executeUpdate();

//            Session session = em.unwrap(Session.class);

//            List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();

//            List<Employee> employees = em.
//                createQuery("select e from Employee e where e.salary > :salary", Employee.class)
//                .setParameter("salary", 21000)
//                .getResultList();



            Employee luke = new Employee("Luke", 20000, Gender.MALE, LocalDate.of(1990, Month.JANUARY, 1));

            System.out.println("========== Is managed? " + em.contains(luke));

            em.persist(luke);
            em.flush();

            System.out.println("========== Is managed? " + em.contains(luke));

            em.getTransaction().commit();

            System.out.println("========== Starting new tx");

            em.getTransaction().begin();

            em.detach(luke);
            luke.setSalary(30000);
            System.out.println("========== Is managed? " + em.contains(luke));

            luke = em.merge(luke);

            System.out.println("========== Is managed? " + em.contains(luke));

            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }

    }
}
