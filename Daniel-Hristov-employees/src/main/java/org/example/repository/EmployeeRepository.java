package org.example.repository;

import org.example.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepository {

    void save(Employee employee);

    void saveAll(Collection<Employee> employees);

    List<Employee> getAllEmployees();
}
