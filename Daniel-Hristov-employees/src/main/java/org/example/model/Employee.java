package org.example.model;

import java.time.LocalDate;

public class Employee {

    private int empId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Employee(int empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.empId = empId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getEmpId() {
        return empId;
    }

    public Employee setEmpId(int empId) {
        this.empId = empId;
        return this;
    }

    public int getProjectId() {
        return projectId;
    }

    public Employee setProjectId(int projectId) {
        this.projectId = projectId;
        return this;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public Employee setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Employee setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }
}
