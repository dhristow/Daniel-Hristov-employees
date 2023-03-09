package org.example.service;

import org.example.model.Employee;
import org.example.model.Pair;

import java.util.List;

public interface EmployeeService {

    void addEmployeeData(List<Employee> employees);

    List<Pair> allPairsWorkingTogether();

    void removePair(Pair pair);
}
