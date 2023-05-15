package com.futuroDev.futuroDev.controller;

import com.futuroDev.futuroDev.model.transport.EmployeeDto;
import com.futuroDev.futuroDev.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;


//anotacao para classe ser vista como um Comtroller


@RestController
//mapping vai pegar a requisição vinda do postman
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    //metodo construtor do controler
    // criando um objeto do empleyeeService atraves da injecao de dependecias
    // empleeService que faz tudo aconrtecer controla as regras de negocio
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;

    }

    //metodo pra receber requisiçoes http
    // recebe o corpo da requisicao como palametro
    //POSTMAPPING --> pega a requisicao vinda por POST e tipo de transporte EmployeeDto
    // funcao vai retornar uma funcao dto de Response
    @PostMapping
    public ResponseEntity<EmployeeDto> register(@RequestBody EmployeeDto body){

        // o que vai retornar como response
       EmployeeDto response = this.employeeService.register(body);
       return ResponseEntity.created(URI.create("/employee")).body(response);





    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> list(@PageableDefault(size=10,page=0,sort="name") Pageable pagination){

        Page<EmployeeDto> response = this.employeeService.list(pagination);


        //se a lista estiver vazia , nao tiver nenhum dado !na frente
        if(!response.hasContent()){

            return ResponseEntity.noContent().build();

        }
        //se nao tiver vazio retorna response
            return  ResponseEntity.ok().body(response);


    }

    // atualizando dado no banco de dados
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody EmployeeDto body){

        this.employeeService.update(body);
        return ResponseEntity.ok().build();


    }

    @DeleteMapping("/{registration}")
    public ResponseEntity<Void> deactivate(@PathVariable("registration") Integer registration) {
        this.employeeService.desactive(registration);
        return ResponseEntity.ok().build();
    }


}
