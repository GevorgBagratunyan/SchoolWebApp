package com.gevbagratunyan.school.service;


import com.gevbagratunyan.school.entity.models.Employee;

import java.time.LocalDate;
import java.time.Period;

public class SalaryManager {

    public double vacationSalary(Employee employee, int vacationDays){
        double taskedSalary = calcSalary(employee,vacationDays);
        double vacationSalary = (taskedSalary*80)/100;
        return vacationSalary;
    }

    //One month full working days equals 22
    public  double calcSalary(Employee employee, int workDays){
        double fullSalary = employee.getEmployeeBanking().getSalary();
        double salary = (fullSalary*workDays)/22;
        double taskedSalary;
        Period diff = Period.between(employee.getBirthDate(), LocalDate.now());
        int age = diff.getYears();

        if(salary>150000){
            taskedSalary = (salary*75)/100;
        } else {
            taskedSalary = (salary*80)/100;
        }

        if(age>25){
            taskedSalary = (taskedSalary*97.5)/100;
        }

        return taskedSalary;
    }
}
