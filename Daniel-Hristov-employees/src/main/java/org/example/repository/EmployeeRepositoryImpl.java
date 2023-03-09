package org.example.repository;

import org.example.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{

    private List<Employee> data = new ArrayList<>();

    @Override
    public void save(Employee employee) {
        this.data.add(employee);
    }

    @Override
    public void saveAll(Collection<Employee> employees) {
        this.data.addAll(employees);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return Collections.unmodifiableList(this.data);
    }
}
