package io.xstefank.jqpl;

import io.xstefank.entity.Department;

public class DepartmentBudget {

    private final Department department;
    private final Long budget;

    public DepartmentBudget(Department department, Long budget) {
        this.department = department;
        this.budget = budget;
    }

    public Department getDepartment() {
        return department;
    }

    public Long getBudget() {
        return budget;
    }
}
