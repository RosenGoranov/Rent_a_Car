package com.example.Rent_a_Car.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "employees")
public class Employee extends BasePerson {
    private BigDecimal salary;

    private LocalDate hireData;

    private boolean isLeft;



    public Employee() {
        super();
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Employee setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public LocalDate getHireData() {
        return hireData;
    }

    public Employee setHireData(LocalDate hireData) {
        this.hireData = hireData;
        return this;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public Employee setLeft(boolean left) {
        isLeft = left;
        return this;
    }


}
