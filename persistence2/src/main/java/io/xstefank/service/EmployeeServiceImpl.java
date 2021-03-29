package io.xstefank.service;

import io.xstefank.dao.EmployeeDao;
import io.xstefank.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional
    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }
}
