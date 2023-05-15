package com.futuroDev.futuroDev.service;


import com.futuroDev.futuroDev.model.Employee;
import com.futuroDev.futuroDev.model.transport.EmployeeDto;
import com.futuroDev.futuroDev.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {



    //injeção de dependencias de outras classes

    private EmployeeRepository employeeRepository;


    //construtor recebe a INTERFACE EMPLOYEE REPOSITORY e seta ela na variavel local employeeRepository
    public EmployeeService(EmployeeRepository employeeRepository){

        this.employeeRepository = employeeRepository;

    }

    // metodo que vai cadastrar / registrar no banco de dados
    // metodo que vai retornar um employeeDto para salvar no banco
    // annotation @Transational quando transita dado para o BANCO DE DADOS

    @Transactional
    public EmployeeDto register(EmployeeDto employeeDto){

        // camada model employee recebe um EmployeeDto de parametro
        // e ja vai criar tudo que esta no construtor do model employee
        Employee employee = new Employee(employeeDto);

        this.employeeRepository.save(employee);

        return employeeDto;


    }

    //LISTA PAGINADA
    //funcao tipo Page PageAble, que faz uma pesquisa  com filtro o especificado no fron end do banco, pesquisa filtrada
    public Page<EmployeeDto> list(Pageable pagination){

        //map faz um map do employeeRepository e gera um novo objeto pagina que contemm o filtro map de empleyees
        // "FINDBYACTIVETRUE --- vai filtra e pegar so os que forem active true

        return this.employeeRepository.findAllByActiveTrue(pagination).map(EmployeeDto::new);


    }


    //atualizando registro , verifica se ja existe , se sim atualiza
    // nao atualizar ou alterar dados identificadores
    @Transactional
    public void update(EmployeeDto body) {
        Employee employee = this.employeeRepository.findByRegistration(body.registration());
        if (body.nome() != null) {
            employee.setName(body.nome());
        }

        this.employeeRepository.save(employee);
    }

    //desactive deativa , o mesmo que deletar , pq nao vai ficar mais ativo (false)
    @Transactional
    public void desactive(Integer registration){

        Employee employee =  this.employeeRepository.findByRegistration(registration);
        employee.setActive(false);
        this.employeeRepository.save(employee);


    }
}
