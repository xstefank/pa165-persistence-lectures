package io.xstefank;

import io.xstefank.entity.Employee;
import io.xstefank.enums.Gender;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf =  Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();

            em.getTransaction().begin();


//            Employee luke = em.find(Employee.class, 1L);

            Employee luke = new Employee("Luke", 20000, Gender.MALE, LocalDate.of(1990, Month.JANUARY, 1));
            em.persist(luke);



//            Employee leia = new Employee("Leia", 22000);
//            em.persist(leia);
//
//            List<Employee> employees = em.createQuery("select e from Employee e where e.salary > :salary", Employee.class)
//                .setParameter("salary", 21000)
//                .getResultList();
//            System.out.println(employees);

            luke = em.merge(luke);


            em.getTransaction().commit();



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
