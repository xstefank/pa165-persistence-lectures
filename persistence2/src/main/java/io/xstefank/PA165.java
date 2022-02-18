package io.xstefank;

import io.xstefank.entity.Department;
import io.xstefank.entity.Employee;
import io.xstefank.enums.Gender;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

@Component
public class PA165 {

    @PersistenceUnit
    private EntityManagerFactory emf;
    
    public void start() {
        initDB();



    }

    private void initDB() {
        Employee luke = new Employee("Luke Skywalker", 20000, Gender.MALE, LocalDate.of(1990, 1, 20));
        Employee leia = new Employee("Leia Organa", 22000, Gender.FEMALE, LocalDate.of(1990, 1, 20));
        Employee han = new Employee("Han Solo", 19000, Gender.MALE, LocalDate.of(1987, 1, 25));
        Employee chewie = new Employee("Chewbacca", 20000, Gender.MALE, LocalDate.of(1590, 1, 20));

        Department jedi = new Department("Jedi");
        Department piloting = new Department("Piloting");
        Department engineering = new Department("Engineering");

        jedi.addEmployee(luke);
        jedi.addEmployee(leia);
        piloting.addEmployee(han);
        engineering.addEmployee(chewie);

        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            em.persist(luke);
            em.persist(leia);
            em.persist(han);
            em.persist(chewie);

            em.persist(jedi);
            em.persist(engineering);
            em.persist(piloting);

            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    private Department findDepartmentByName(EntityManager em, String departmentName) {
        return em.createQuery("select d from Department d join fetch d.employees where d.name = :name", Department.class)
            .setParameter("name", departmentName)
            .getSingleResult();
    }

    private void selectWithCriteriaAPI() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> e = query.from(Employee.class);

        query.select(e).where(cb.equal(e.get("name"), "Luke Skywalker"));

        System.out.println(em.createQuery(query).getResultList());

        em.getTransaction().commit();
        em.close();
    }
}
