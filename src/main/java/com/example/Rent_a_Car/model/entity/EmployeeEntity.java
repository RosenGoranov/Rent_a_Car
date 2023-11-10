package com.example.Rent_a_Car.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "employees")
public class EmployeeEntity extends BasePerson {
    private BigDecimal salary;

    private LocalDate hireData;

    private boolean isLeft;



    public EmployeeEntity() {
        super();
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public EmployeeEntity setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public LocalDate getHireData() {
        return hireData;
    }

    public EmployeeEntity setHireData(LocalDate hireData) {
        this.hireData = hireData;
        return this;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public EmployeeEntity setLeft(boolean left) {
        isLeft = left;
        return this;
    }


}
