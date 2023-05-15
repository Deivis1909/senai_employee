package com.futuroDev.futuroDev.model;


import com.futuroDev.futuroDev.model.transport.EmployeeDto;
import jakarta.persistence.*;
import lombok.*;


// equalsandHashCode vai comparar os objetos e tabelas apenas pelo " id"
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    // colunm unique nao vai ter matriculas(registration) repetidas
    //nullable == not null , nao pode ser nulo
    @Column(unique = true,nullable = false)
    private Integer registration;
    private String email;

    private String fone;

    private Double wage;

    private Boolean active;

    // constructor
    // recebe como paramento employeeDTO Que transporta os dados
    public Employee(EmployeeDto employeeDto){

        // variavel  da classe local registration recebe como parametro registration do emploeeDTO de transporte
        this.registration = employeeDto.registration();
        this.name = employeeDto.nome();
        this.email = employeeDto.email();
        this.fone = employeeDto.fone();
        this.wage = employeeDto.wage();

        this.active = true;




    }



}
