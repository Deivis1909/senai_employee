package com.futuroDev.futuroDev.model.transport;

import com.futuroDev.futuroDev.model.Employee;

public record EmployeeDto(Integer registration, String nome, String email, String fone, Double wage) {

    // construtor
    public EmployeeDto(Employee employee){

             //converte um model employee em um dto
            //pega todos as variaveis do model employee
        this(employee.getRegistration(),employee.getName(),employee.getEmail(),employee.getFone(),employee.getWage());

    }

}
