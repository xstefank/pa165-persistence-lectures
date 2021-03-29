package io.xstefank.service;

import io.xstefank.entity.Employee;

public interface EmployeeService {

    void create(Employee employee);

    Employee findById(Long id);
}
