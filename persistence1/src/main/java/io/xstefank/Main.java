package io.xstefank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();

            em.getTransaction().begin();

            Employee luke = new Employee("Luke", null, Gender.MALE, LocalDate.of(2099, Month.JANUARY, 1));

            em.persist(luke);


            em.flush();
            em.clear();
            em.setFlushMode(FlushModeType.COMMIT);
//            Session session = em.unwrap(Session.class);
//            Employee luke = em.find(Employee.class, 1L);
//
//            em.remove(luke);

//            Employee luke = new Employee("Luke", 20000);
//            em.persist(luke);

//            Employee leia = new Employee("Leia", 22000);
//            em.persist(leia);

//            List<Employee> employees = em.createQuery("select e from Employee e where e.salary > :salary", Employee.class)
//                .setParameter("salary", 21000)
//                .getResultList();
//            System.out.println(employees);

            em.getTransaction().commit();

//            em.getTransaction().begin();
//
//            Employee luke = new Employee("Luke", 20000);
//            em.persist(luke);
//
//            em.getTransaction().commit();
//            em.close();
//
//            em = emf.createEntityManager();
//            em.getTransaction().begin();
//
//            Employee employee = em.find(Employee.class, 1L);
//            System.out.println(employee);
//
//            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }

            if (emf != null) {
                emf.close();
            }
        }
    }
}
