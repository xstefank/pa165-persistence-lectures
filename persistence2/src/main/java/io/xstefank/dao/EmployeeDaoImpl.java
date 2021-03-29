package io.xstefank.dao;

import io.xstefank.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Employee employee) {
        em.persist(employee);
    }

    @Override
    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee findByName(String name) {
        return null;
    }
}
