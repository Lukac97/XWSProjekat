package user.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import user.userservice.domain.Administrator;
import user.userservice.domain.Agent;
import user.userservice.domain.Customer;
import user.userservice.service.AdministratorService;
import user.userservice.service.AgentService;
import user.userservice.service.CustomerService;
import user.userservice.service.PersonService;

import javax.xml.ws.Response;

@RestController
public class UserController {
    private AdministratorService administratorService;
    private AgentService agentService;
    private CustomerService customerService;
    private PersonService personService;

    @Autowired
    public UserController (
            AdministratorService administratorService,
            AgentService agentService,
            CustomerService customerService,
            PersonService personService
    ) {
        this.administratorService = administratorService;
        this.agentService = agentService;
        this.customerService = customerService;
        this.personService = personService;
    }

    @GetMapping("/type/{id}")
    public String getUserType(@PathVariable("id") long id){
        if((Long) id == null){
            return "NULL";
        }
        if(personService.findById(id) == null){
            return "DOESNT_EXIST";
        }
        if(personService.findById(id) instanceof Customer){
            return "CUSTOMER";
        }else if(personService.findById(id) instanceof Agent){
            return "AGENT";
        }else if(personService.findById(id) instanceof Administrator){
            return "ADMINISTRATOR";
        }else{
            return "UNEXPECTED";
        }
    }
}
