package io.xstefank.dao;

import io.xstefank.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    void create(Employee employee);

    Employee findById(Long id);

    List<Employee> findAll();

    Employee findByName(String name);
}
