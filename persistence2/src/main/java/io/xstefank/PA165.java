package io.xstefank;

import io.xstefank.entity.Department;
import io.xstefank.entity.Employee;
import io.xstefank.enums.Gender;
import io.xstefank.jqpl.DepartmentBudget;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Component
public class PA165 {

//    @Autowired
//    EmployeeService employeeService;

    @PersistenceUnit
    private EntityManagerFactory emf;
    
    public void start() {
        initDB();

        selectWithCriteriaAPI();

//        Employee e = new Employee("Luke Skywalker", 200, Gender.MALE, LocalDate.of(1990, 1, 1));
//        employeeService.create(e);


        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

//        Employee martin = new Employee("Martin", 20000, Gender.MALE, LocalDate.of(1993, 1, 1));
//        Department teaching = new Department("Teaching");
//
//
////        teaching.getEmployees().add(martin);
//        martin.setDepartment(teaching);
//
//        em.persist(martin);
////        em.persist(teaching);

//        Department jedi = findDepartmentByName(em, "Jedi");
//        jedi.getEmployees().size();

//        List<Object[]> departmentBudget = em
//            .createQuery("select new d, sum(e.salary) from Department d join d.employees e group by d", Object[].class)
//            .getResultList();

//        System.out.println(((Department) departmentBudget.get(0)[0]).getName());


//        List<DepartmentBudget> departmentBudget = em
//            .createQuery("select new io.xstefank.jqpl.DepartmentBudget(d, sum(e.salary)) from Department d join d.employees e group by d", DepartmentBudget.class)
//            .getResultList();
//
//        System.out.println(departmentBudget.get(0).getDepartment().getName());


        em.getTransaction().commit();
        em.close();

//        System.out.println(jedi.getEmployees());
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
}
