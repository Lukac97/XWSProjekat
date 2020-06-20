package user.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.userservice.domain.Person;
import user.userservice.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(
            PersonRepository personRepository
    ) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return this.personRepository.save(person);
    }

    public boolean existsById(Long consumerId) {
        if (!this.personRepository.existsById(consumerId)) {
            return false;
        }
        return true;
    }

    public Person findById(long id){
        return this.personRepository.findById(id);
    }

    public Person findByEmail(String email){
        return this.personRepository.findByEmail(email);
    }

    public List<Person> findAll(){
        return this.personRepository.findAll();
    }

    public void deleteById(long id){
        this.personRepository.deleteById(id);
    }

}
