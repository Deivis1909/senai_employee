package com.futuroDev.futuroDev.repository;

import com.futuroDev.futuroDev.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


//NOS PARAMETROS QUAL O OBJETO QUE VAI SER PASSADO PRA O BANCO E O TIPO DO IDENTIFICADOR DELE (ID)
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //funcao para fazer update no banco
    // com essa nomeclatura "findBy " ele chama a a funcao pro update de forma automatica

    public Employee findByRegistration(@Param("registration")Integer registration);

        // funcao pronta de page find pega corta so que for Active true
        // o que for Active false fica deletado
    public Page<Employee> findAllByActiveTrue(Pageable pagination);


}
