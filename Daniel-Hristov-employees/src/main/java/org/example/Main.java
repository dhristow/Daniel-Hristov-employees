package org.example;

import org.example.frame.MyFrame;
import org.example.model.Employee;
import org.example.model.Pair;
import org.example.repository.EmployeeRepository;
import org.example.repository.EmployeeRepositoryImpl;
import org.example.repository.PairRepository;
import org.example.repository.PairRepositoryImpl;
import org.example.service.EmployeeService;
import org.example.service.EmployeeServiceImpl;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        JFileChooser fc = new JFileChooser();
        int response = fc.showOpenDialog(null);
        String path = "";

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fc.getSelectedFile().getAbsolutePath());
            path = file.getAbsolutePath();
        }

        //String path = "src/main/resources/bds.csv";
        String line = "";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = pattern.format(calendar.getTime());
        List<Employee> employeeList = new ArrayList<>();

        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        PairRepository pairRepository = new PairRepositoryImpl();
        EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository, pairRepository);

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";\\s*");

                if (data[3].equals("NULL")) {
                    data[3] = date;
                }

                int employeeId = Integer.parseInt(data[0]);
                int projectId = Integer.parseInt(data[1]);

                Date startDate = pattern.parse(data[2]);
                LocalDate dateFrom = startDate.toInstant().atZone(ZoneOffset.systemDefault()).toLocalDate();

                Date endDate = pattern.parse(data[3]);
                LocalDate dateTo = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                Employee employee = new Employee(employeeId, projectId, dateFrom, dateTo);
                employeeList.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        employeeService.addEmployeeData(employeeList);
        employeeService.allPairsWorkingTogether();

        while (pairRepository.size() > 0) {
            Pair pair = pairRepository.getAllPairs().get(0);
            int firstEmployee = pair.getFirstEmpId();
            int secondEmployee = pair.getSecondEmpId();
            int duration = pair.getDuration();
            int projectId = pair.getProjectId();
            System.out.println("First Employee ID: " + firstEmployee
                    + ", second Employee ID: " + secondEmployee
                    + ", project ID: " + projectId
                    + ", days worked: " + duration);
            pairRepository.remove(pair);
        }
    }
}