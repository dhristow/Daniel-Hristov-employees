package org.example.service;

import org.example.model.Employee;
import org.example.model.Pair;
import org.example.repository.EmployeeRepository;
import org.example.repository.PairRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final PairRepository pairRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PairRepository pairRepository) {
        this.employeeRepository = employeeRepository;
        this.pairRepository = pairRepository;
    }

    @Override
    public void addEmployeeData(List<Employee> employees) {
        this.employeeRepository.saveAll(employees);
    }

    @Override
    public List<Pair> allPairsWorkingTogether() {
        List<Employee> allEmployees = this.employeeRepository.getAllEmployees();
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < allEmployees.size() - 1; i++) {
            for (int j = i + 1; j < allEmployees.size(); j++) {
                Employee firstEmployee = allEmployees.get(i);
                Employee secondEmployee = allEmployees.get(j);

                boolean workedTogether = (firstEmployee.getDateFrom().isBefore(secondEmployee.getDateTo())
                        || firstEmployee.getDateFrom().isEqual(secondEmployee.getDateTo()))
                        && (firstEmployee.getDateTo().isAfter(secondEmployee.getDateFrom())
                        || firstEmployee.getDateTo().isEqual(secondEmployee.getDateFrom()));

                //poopravqne
                if (firstEmployee.getProjectId() == secondEmployee.getProjectId() && workedTogether)
                {
                    long durationInDays = 0;
                    LocalDate startDate;
                    LocalDate endDate;

                    if (!firstEmployee.getDateFrom().isBefore(secondEmployee.getDateFrom())) {
                        startDate = firstEmployee.getDateFrom();
                    } else {
                        startDate = secondEmployee.getDateFrom();
                    }

                    if (firstEmployee.getDateTo().isBefore(secondEmployee.getDateTo())) {
                        endDate = firstEmployee.getDateTo();
                    } else {
                        endDate = secondEmployee.getDateTo();
                    }

                    durationInDays = Math.abs(startDate.until(endDate, ChronoUnit.DAYS));
                    if (durationInDays > 0) {
                        boolean pairExist = false;
                        for (Pair pair:
                             pairRepository.getAllPairs()) {

                            boolean doesPairExist = ( (pair.getFirstEmpId() == firstEmployee.getEmpId()
                                    && pair.getSecondEmpId() == secondEmployee.getEmpId() )
                                    || ( pair.getFirstEmpId() == secondEmployee.getEmpId()
                                    && pair.getSecondEmpId() == firstEmployee.getEmpId()) );
                            //boolean pairWithProjectAlready = (pair.getProjectId() != firstEmployee.getProjectId() || pair.getProjectId() != secondEmployee.getProjectId());
                            // && pairWithProjectAlready
                            if (doesPairExist) {
                                pair.setDuration((int) durationInDays);
                                pairExist = true;
                            }
                        }
                        if (!pairExist) {
                            Pair newPair = new Pair(firstEmployee.getEmpId(), secondEmployee.getEmpId(), (int)durationInDays, firstEmployee.getProjectId());
                            pairs.add(newPair);
                        }
                    }
                }
            }
        }

        pairRepository.saveAll(pairs);

        return pairRepository.getAllPairs();
    }

    public void removePair(Pair pair) {
        this.pairRepository.remove(pair);
    }
}
